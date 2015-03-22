import java.io.BufferedReader;
import java.io.FileReader;

public class FontManager {
	
	private static String[] fontList = {""};
	
	public static String[] getFonts(){
		String fonts = "";
		String temp = "";
		
		try {
			BufferedReader fontReader = new BufferedReader(new FileReader("Alphabet/fontList.txt"));
			
			temp = fontReader.readLine();
			
			while(temp != null){
				fonts += temp + "\n";
				temp = fontReader.readLine();
			}
			fontList = fonts.split("\n");
			
			fontReader.close();
		} 
		catch (Exception e) {
			Driver.abortReason = "Exception in fontManager.getFonts (" + e + ")";
		}
		
		return fontList;
	}
	
	public static int getFontIndex(String fontName){
        
		int index = 0;
		
		for(int i = 0; i < fontList.length; i++){
			if(fontList[i].equals(fontName))
				index = i;
		}

		return index;
	}
}