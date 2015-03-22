import java.util.*;
import java.io.*;

public class WriteManager{

	public static Scanner sc = new java.util.Scanner(System.in);
	public static final String NEWLINE = "\n";

	// define default settings
	public static double height = 4.0;
	public static double width = 3.0;
	public static double kerning = 1.0;
	public static double zDraw = -1.0;
	public static double zMove = 1.0;
	public static double feedrate = 5000.0; // mm/min
	public static double pageWidth = 160.0;
	public static double pageHeight = 200.0;
	public static double lineSpacing = 2.0;
	public static double shearAngle = 15.0;
	public static double scRatio = 0.7;
	public static String font = "Sans";

	public static double xDistance = 0.0;
	public static int numLines = 0;

	// objects from other files
	public static Alphabet alphabet = new Alphabet();
	public static Obj_to_gCode converterUC;
	public static Obj_to_gCode converterLC;

	public static void writeLetter(Letter a){

		String output = "";

		updateSettings();
		converterUC = new Obj_to_gCode(height, width, kerning, zDraw, zMove,feedrate, shearAngle, font); //initialize converters for upper, lowercase letters
		converterLC = new Obj_to_gCode(height * scRatio, width * scRatio,kerning * scRatio, zDraw, zMove, feedrate, shearAngle, font);	

		if(a.isLowerCase){
			if(xDistance > pageWidth) newLine();//make a new line if above max width
			xDistance += (scRatio*(width + kerning));
			
			output += converterLC.getGCode(a, Driver.zPath);
		}
		else{
			if(xDistance > pageWidth) newLine();
			xDistance += width + kerning;
			
			output += converterUC.getGCode(a, Driver.zPath);
		}
	//	System.out.println(xDistance); //test print

		SerialConnector.sendGCode(output);
	}

	public static void newLine(){ //move to a new line
		SerialConnector.sendGCode("G0 Z" + zMove + "\nX-" + Obj_to_gCode.shortForm.format(xDistance) + " Y-" + Obj_to_gCode.shortForm.format(lineSpacing + height) + "\nG0 Z-" + zMove+ "\n");
		xDistance = 0.0; //zero distance
	}

	public static void readSettings(String filename) {

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			// converter characteristics
			height = Double.parseDouble(br.readLine());
			width = Double.parseDouble(br.readLine());
			kerning = Double.parseDouble(br.readLine());
			zDraw = Double.parseDouble(br.readLine());
			zMove = Double.parseDouble(br.readLine());
			feedrate = Double.parseDouble(br.readLine());
			shearAngle = Double.parseDouble(br.readLine());
			font = br.readLine();

			// writer characteristics
			pageWidth = Double.parseDouble(br.readLine());
			pageHeight = Double.parseDouble(br.readLine());
			lineSpacing = Double.parseDouble(br.readLine());
			scRatio = Double.parseDouble(br.readLine());

			br.close();
		} catch (Exception e) {
			Driver.abortReason = "Exception in Writer.readSettings (" + e + ")";
		}
	}

	public static void updateSettings(){

		height = Double.parseDouble(VisualPanel.letHeightField.getText());
		width = Double.parseDouble(VisualPanel.letWidthField.getText());
		kerning = Double.parseDouble(VisualPanel.kerningField.getText());
		feedrate = Double.parseDouble(VisualPanel.feedrateField.getText());
		pageHeight = Double.parseDouble(VisualPanel.pHeightField.getText());
		pageWidth = Double.parseDouble(VisualPanel.pWidthField.getText());
		lineSpacing = Double.parseDouble(VisualPanel.lineSpacingField.getText());
		zMove = Double.parseDouble(VisualPanel.zMoveField.getText());
		zDraw = Double.parseDouble(VisualPanel.zDrawField.getText());
		shearAngle = Double.parseDouble(VisualPanel.saField.getText());
		scRatio = Double.parseDouble(VisualPanel.scrField.getText());
		font = "" + VisualPanel.fontBox.getSelectedItem().toString();
		Driver.zPath = VisualPanel.zPathBox.getSelectedIndex();

	}
}
