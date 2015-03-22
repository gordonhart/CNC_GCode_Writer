import java.io.*;
import java.text.DecimalFormat;

public class Obj_to_gCode{ // Obj to gCode conversion utility

	public Alphabet alphabet = new Alphabet();
	public final int X = 0;
	public final int Y = 1;
	public final int Z = 2;
	public final String NEWLINE = "\n";

	public String font = "Sans";
	public double zDraw = -1.0;
	public double zMove = 1.0;
	public double feedrate = 1270.0;
	public double kerning = 1.0;
	public double height = 4.0;
	public double width = 3.0;
	public double shearAngle = 15.0;
	public double letHeight  = 4.0;
	public double letWidth = 3.0;

	public BufferedReader br;
	public BufferedReader letterStatsReader;
	public static DecimalFormat shortForm = new DecimalFormat("##.####");

	public boolean italics = false;

	public Obj_to_gCode(double h, double w, double k, double zD, double zM, double fr, double sA, String f) {
		height = h;
		width = w;
		kerning = k;
		zDraw = zD;
		zMove = zM;
		feedrate = fr;
		shearAngle = sA;
		font = f;

		//import font width, height settings
		try {
			letterStatsReader = new BufferedReader(new FileReader("Alphabet/Letters_obj_" + font + "/fontSettings.txt"));
			letHeight = Double.parseDouble(letterStatsReader.readLine());
			letWidth = Double.parseDouble(letterStatsReader.readLine());
			letterStatsReader.close();
		} catch (Exception e) {
			Driver.abortReason = "Exception in Obj_to_gCode Initializer (" + e + ")";
		}
	}

	public String getGCode(Letter l, int zPathShape) {
		double[][] letter_verts = this.readFromObj(l.objFilename);
		if(zPathShape == 0){ //zVert shape
			return this.toGCodeVert(letter_verts);
		}
		else{ //zSine shape
			return this.toGCodeSine(letter_verts);
		}
	}

	public double[][] readFromObj(String filename) { // read points into array from .obj

		String current = "";
		String[] points = new String[4];

		double[][] obj;

		int numPoints = 0;

		try {		
			// test for number of vertices
			br = new BufferedReader(new FileReader("Alphabet/Letters_obj_" + font + "/" + filename));
			current = br.readLine();

			while (current != null) {
				if (current.charAt(0) == 'v') {
					numPoints++;
				}
				if (current.charAt(0) == 'o') {
					br.mark(100000);
				}
				current = br.readLine();
			}

			br.reset(); // reset to last marker

			obj = new double[numPoints][3]; // define array of points as size (numPoints, 3)

			for (int i = 0; i < numPoints; i++) {
				current = br.readLine();
				points = current.split(" ");

				if (current.charAt(0) == 'v') {
					obj[i][Y] = (-height/letHeight) * Double.parseDouble(points[3]); // set y, flip direction, adjust for height and width letter is defined by

					if (this.italics)
						obj[i][X] = (width/letWidth)*((Double.parseDouble(points[1]) + ((4.0/letHeight)*obj[i][Y] * Math.tan(shearAngle*(Math.PI/180.0))))); // add italics offset, shear angle adjusted to radians
					else
						obj[i][X] = (width/letWidth)*Double.parseDouble(points[1]); 

					if (Double.parseDouble(points[2]) < 0.0) // set to zDraw if value is negative, otherwise set to xMove
						obj[i][Z] = zDraw;
					else
						obj[i][Z] = zMove;

					//  System.out.println(i + " X: " + obj[i][X] + "\tY: " + obj[i][Y] + "\tZ: " + obj[i][Z]); //test print output
				}
			}
			br.close();
			return obj;
		} catch (Exception e) {
			Driver.abortReason = "Exception in Obj_to_gCode.readFromObj (" + e + ")";
			//	System.out.println(e);
		}
		return null;
	}

	public String toGCodeSine(double[][] vertices) { // export gCode with modified sine paths

		String gCode = "G91\nG1 F" + feedrate + NEWLINE; // set feedrate and initial position
		double stepSize = 0.0;
		double xLength = 0.0;
		double yLength = 0.0;
		double totLength = 0.0;
		double power = 0.0;
		double z = 0.0;

		for (int i = 1; i < vertices.length; i++) {

			//jump between segments in middle of letter
			if(vertices[i][Z] == zMove && i > 1 && i != vertices.length-1){
				//System.out.println("jump");

				if(i+1 < vertices.length){
					xLength = vertices[i+1][X] - vertices[i][X]; //length between two next points in x, y direction
					yLength = vertices[i+1][Y] - vertices[i][Y];
					totLength = 5.0*Math.sqrt(Math.pow(vertices[i+1][X] - vertices[i][X], 2.0) + Math.pow(vertices[i+1][Y] - vertices[i][Y], 2.0)); //five steps per mm
					stepSize = Math.PI/(totLength); //scale number of steps based on length between segments
					power = (Math.log((-zDraw)/(-zDraw+zMove))/Math.log(Math.sin(stepSize))); //math to ensure first step brings back to z = 0 (for t=1)

					for(double t = 0; t < totLength; t++){ //time parameter to define points of sqrt(sin(t/sinSteps))
						gCode += "X" + shortForm.format(xLength*(stepSize/Math.PI));
						gCode += " Y" + shortForm.format(yLength*(stepSize/Math.PI));
						
						if(t+1 > totLength) //NaN checker
							z = (-zDraw + zMove)*(0.0 - Math.pow(Math.sin(t*stepSize), power)); 
						else
							z = (-zDraw + zMove)*(Math.pow(Math.sin((t+1)*stepSize), power) - Math.pow(Math.sin(t*stepSize), power)); 
						
					//	z = (-zDraw + zMove)*(Math.pow(Math.sin((t+1)*stepSize), power) - Math.pow(Math.sin(t*stepSize), power)); //check for Nan 
						gCode += " Z" + shortForm.format(z) + NEWLINE; //if number is too small to compute, just add zero
					}
					i += 2;
				}
			}

			//end of letter slope
			else if(i == vertices.length-1 && i > 1){ //last segment, make arch to start of next letter, also make sure it isn't the first vertex (in the case of spaces)
			//	System.out.println("end of letter");

				xLength = (width+kerning) - vertices[i][X]; //length between two next points in x, y direction
				yLength = - vertices[i][Y];
				totLength = 5.0*Math.sqrt(Math.pow((width+kerning) - vertices[i][X], 2.0) + Math.pow(-vertices[i][Y], 2.0)); //five steps per mm
				stepSize = Math.PI/(totLength); //scale number of steps based on length between segments
				power = (Math.log((-zDraw)/(-zDraw+zMove))/Math.log(Math.sin(stepSize/2.0))); //math to ensure first step brings back to z = 0 (for t=1)

				for(double t = 0; t < totLength; t++){ //only half arch, in order to end at zMove height
					gCode += "X" + shortForm.format((xLength*(stepSize/Math.PI)));
					gCode += " Y" + shortForm.format((yLength*(stepSize/Math.PI)));
					z = (-zDraw + zMove)*(Math.pow(Math.sin((t+1)*stepSize/2.0), power) - Math.pow(Math.sin(t*stepSize/2.0), power)); //check that z does not compute out to NaN
					gCode += " Z" + shortForm.format(z) + NEWLINE; //usually a longer travel distance, so it needs a steeper curve
				}
				i += 1;
			}

			//beginning of letter slope
			else if(i == 1 && i != vertices.length-1 && vertices[i][Z] == zMove){ //make arch at beginning of letter to first draw segment, if letter has travel before start, and if letter is not a space
			//	System.out.println("beginning of letter");

				xLength = vertices[i][X] - vertices[i-1][X]; //length between two next points in x, y direction
				yLength = vertices[i][Y] - vertices[i-1][Y];
				totLength = 5.0*Math.sqrt(Math.pow(vertices[i][X] - vertices[i-1][X], 2.0) + Math.pow(vertices[i][Y] - vertices[i-1][Y], 2.0));
				stepSize = Math.PI/(totLength); //scale number of steps based on length between segments
				power = (Math.log((-zDraw)/(-zDraw+zMove))/Math.log(Math.cos((totLength-1.0)*stepSize/2.0))); //math to ensure last step brings back to z = 0

				for(double t = 0; t < totLength; t++){ //time parameter to define points of sqrt(sin(t/sinSteps))
					gCode += "X" + shortForm.format((xLength*(stepSize/Math.PI)));
					gCode += " Y" + shortForm.format((yLength*(stepSize/Math.PI)));
					if(t+1 > totLength) //NaN checker
						z = (-zDraw + zMove)*(0.0 - Math.pow(Math.cos(t*stepSize/2.0), power));
					else
						z = (-zDraw + zMove)*(Math.pow(Math.cos((t+1)*stepSize/2.0), power) - Math.pow(Math.cos(t*stepSize/2.0), power)); //check for Nan
					gCode += " Z" + shortForm.format(z) + NEWLINE;
				}
				i += 1;
			} 

			//regular draw
			else{ //zDraw height, proceed normally
				gCode += "X" + shortForm.format((vertices[i][X] - vertices[i-1][X])); // must adjust width, height because all letters are created with default w = 3, h = 4
				gCode += " Y" + shortForm.format((vertices[i][Y] - vertices[i-1][Y]));
				gCode += " Z" + shortForm.format(((zMove - zDraw)/2)*(vertices[i][Z] - vertices[i-1][Z])) + NEWLINE;
				//gCode += " Z" + shortForm.format(((zMove - zDraw)/2)*(vertices[i][Z] - vertices[i-1][Z])) + NEWLINE; // zDraw or zMove
			}
		}

		//gCode += "X" + shortForm.format((width - vertices[vertices.length-1][X]) + kerning); // go to start of next letter
		//gCode += " Y" + shortForm.format(0.0 - vertices[vertices.length-1][Y]) + NEWLINE; // move down to y zero level
		//gCode += " Z" + zMove + NEWLINE;

		//gCode += "X" + (width + kerning) + " Y0.0 Z" + zMove + NEWLINE; // go to start of new letter
		//gCode += "G92 X0 Y0" + NEWLINE; // reset axes to 0

		return gCode;
	}

	public String toGCodeVert(double[][] vertices) { // export gCode with vertical paths

		String gCode = "G91\nG1 F" + feedrate + NEWLINE; // set feedrate and initial position

		for (int i = 1; i < vertices.length; i++) {
			gCode += "X" + shortForm.format((vertices[i][X] - vertices[i-1][X])); // must adjust width, height because all letters are created withdefault w =3, h = 4
			gCode += "Y" + shortForm.format((vertices[i][Y] - vertices[i-1][Y]));
			gCode += "Z" + shortForm.format(((zMove - zDraw)/2)*(vertices[i][Z] - vertices[i-1][Z])) + NEWLINE; // zDraw or zMove
		}

		gCode += "X" + shortForm.format((width - vertices[vertices.length-1][X]) + kerning); // go to start of next letter
		gCode += " Y" + shortForm.format(0.0 - vertices[vertices.length-1][Y]) + NEWLINE; // move down to y zero level

		return gCode;
	}
}