import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser{

	private JDialog fileDialog;
	private JFileChooser chooser;
	private FileNameExtensionFilter textFilter;
	private FileNameExtensionFilter settingsFilter;
	private FileNameExtensionFilter toolpathFilter;
	private String fileName = "Default";
	private String filePath = "";
	private int ReturnVal;
	//private String directory = "/Users/gordeezycrunch/Documents/Graphics/Writer/Writer_Project/";

	public void fileDialog(String name, int chooseOrSave, String defaultField) { //chooseOrSave = 0 CHOOSE FILE; chooseOrSave = 1 SAVE FILE;
		//File startDirectory = new File(directory);
		File startDirectory = new File("");
		fileName = "Default"; //reset fields
		filePath = "";

		chooser = new JFileChooser(startDirectory);
		textFilter = new FileNameExtensionFilter("Text File (*.txt)", "txt"); //filter file types
		settingsFilter = new FileNameExtensionFilter("Settings File (*.wst)", "wst");
		toolpathFilter = new FileNameExtensionFilter("Toolpath File (*.tap)", "tap");

		if(defaultField == "settings")
			chooser.setFileFilter(settingsFilter);
		else if(defaultField == "test") //toolpath default
			chooser.setFileFilter(toolpathFilter);
		else
			chooser.setFileFilter(textFilter);

		chooser.setSelectedFile(new File(defaultField)); //initialize chooser with text in field

		fileDialog = new JDialog();
		fileDialog.setTitle(name);
		fileDialog.getContentPane().add(chooser);
		fileDialog.pack();
		fileDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fileDialog.setLocationRelativeTo(null);
		fileDialog.setVisible(true);

		if(chooseOrSave == 0)//choose file
			ReturnVal = chooser.showOpenDialog(null);
		else //chooseOrSave = 1; save file
			ReturnVal = chooser.showSaveDialog(null);

		if(ReturnVal == JFileChooser.APPROVE_OPTION) { //wait for the "open" or "save" button to be clicked
			filePath += chooser.getSelectedFile().getAbsolutePath();
			fileName = chooser.getSelectedFile().getName();
		}

		fileDialog.dispose(); //close
	}

	public String getFilePath(){ //getter methods
		return filePath;
	}

	public String getFileName(){
		return fileName;
	}
}