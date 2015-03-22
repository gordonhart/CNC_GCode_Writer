public class Letter { // Letter object, within Alphabet

	public String name; // name of character
	public String objFilename; // name of file with OBJ of character
	public boolean isLowerCase;

	public Letter(String n) {
		name = n;
		
		if(Character.isLowerCase(n.charAt(0))){
			isLowerCase = true;
		}
		else{
			isLowerCase = false;
		}

		if (!n.equals(":") && !n.equals(".") && !n.equals("/") && !n.equals("*")) // must account for letters that cannot be filenames
			objFilename = n + ".obj";
		else if (n.equals(":"))
			objFilename = "_colon.obj";
		else if (n.equals("."))
			objFilename = "_period.obj";
		else if (n.equals("/"))
			objFilename = "_slash.obj";
		else if(n.equals("*"))
			objFilename = "_star.obj";
		else
			objFilename = "null.obj";
	}
}