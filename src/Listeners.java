import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Listeners {

	public FileChooser chooseFile = new FileChooser();

	private final int openFile = 0;
	private final int saveFile = 1;

	public class gCodeListener implements KeyListener{
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER && SerialConnector.isConnected) { //only send new line GCode if it's connected
				SerialConnector.sendGCode(VisualPanel.gCode.getText() + "\n");
				VisualPanel.gCode.setText("");
			}
		}

		//unused but necessary fields for KeyListeners
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {}
	}

	public class move implements KeyListener{

		public boolean shiftIsPressed = false;

		public void keyReleased(KeyEvent e) {

			//test for shift modifiers first
			if (e.getKeyCode() == KeyEvent.VK_UP && shiftIsPressed && SerialConnector.isConnected) { //only send new line GCode if it's connected
				SerialConnector.sendGCode("G0Z" + VisualPanel.zIncrement.getText() + "\n");
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN && shiftIsPressed && SerialConnector.isConnected) { //only send new line GCode if it's connected
				SerialConnector.sendGCode("G0Z-" + VisualPanel.zIncrement.getText() + "\n");
			}


			else if (e.getKeyCode() == KeyEvent.VK_UP && SerialConnector.isConnected) { //only send new line GCode if it's connected
				SerialConnector.sendGCode("G0Y" + VisualPanel.xyIncrement.getText() + "\n");
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN && SerialConnector.isConnected) { //only send new line GCode if it's connected
				SerialConnector.sendGCode("G0Y-" + VisualPanel.xyIncrement.getText() + "\n");
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT && SerialConnector.isConnected) { //only send new line GCode if it's connected
				SerialConnector.sendGCode("G0X" + VisualPanel.xyIncrement.getText() + "\n");
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT && SerialConnector.isConnected) { //only send new line GCode if it's connected
				SerialConnector.sendGCode("G0X-" + VisualPanel.xyIncrement.getText() + "\n");
			}
			else if (e.getKeyCode() == KeyEvent.VK_SHIFT) { //only send new line GCode if it's connected
				shiftIsPressed = false; //turn shift off
			}
		}

		//unused but necessary fields for KeyListeners
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SHIFT){ //turn shift modifier on
				shiftIsPressed = true;
			}
		}
	}

	public class connectListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(SerialConnector.isConnected){ //disconnect if connected
				SerialConnector.disconnect();
				VisualPanel.btnConnect.setText("Connect");
				VisualPanel.lblNotConnected.setText("Not Connected");

				//reset fields
				VisualPanel.inputText.setText("");
				VisualPanel.tinyGOutputField.setText("");
				VisualPanel.outputText.setText("");
				//VisualPanel.sentGCode.setText("");
			}
			else {//connect if disconnected
				SerialConnector.connect();
				if(SerialConnector.isConnected){
					VisualPanel.btnConnect.setText("Disconnect");
					VisualPanel.lblNotConnected.setText("Connected to " + SerialConnector.serialPort.getPortName());
				}	
			}
		}
	}

	public class zPathListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(VisualPanel.zPathBox.getSelectedIndex() == 0) //zSine is selected, set picture
				VisualPanel.zPathImage.setIcon(new ImageIcon(VisualPanel.class.getResource("/icons/zVertMove.png")));
			else
				VisualPanel.zPathImage.setIcon(new ImageIcon(VisualPanel.class.getResource("/icons/zSineMove.png")));
		}
	}

	public class returnListener implements KeyListener{
		public void keyReleased(KeyEvent e) { 
			if (e.getKeyCode() == KeyEvent.VK_ENTER && SerialConnector.isConnected) { //only send new line GCode if it's connected
				for(int i = 0; i < VisualPanel.inputText.getText().length(); i++){
					Letter temp = new Letter("" + VisualPanel.inputText.getText().charAt(i));
					WriteManager.writeLetter(temp);
				}

				//	SerialConnector.sendGCode(VisualPanel.inputText.getText()); //send whatever letter(s) are left in the input box

				String display = VisualPanel.outputText.getText() + VisualPanel.inputText.getText()+ "\n";
				VisualPanel.outputText.setText(display); //add the letter and a new line to the display 

				//WriteManager.writeLetter(VisualPanel.inputText.getText().charAt(0));
				//QueueManager.sendOut();
				WriteManager.newLine(); //new line when return is pressed
				VisualPanel.inputText.setText("");
			}
		}

		//unused but necessary fields for KeyListener
		public void keyPressed(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}

	public class textListener implements DocumentListener {
		public void insertUpdate(DocumentEvent e) { //add inserted character to QueueManager
			QueueManager.add(VisualPanel.inputText.getText().charAt(VisualPanel.inputText.getText().length() -1)); 
		}

		public void removeUpdate(DocumentEvent e) { //remove deleted character from QueueManager
			QueueManager.removeLast();
		}

		//unused but necessary field for DocumentListener
		public void changedUpdate(DocumentEvent e){}
	}

	public class tinyGListener implements DocumentListener{
		public void insertUpdate(DocumentEvent e) {
			QueueManager.sendOut();
			VisualPanel.scrollPane.setScrollPosition(0, VisualPanel.sentGCode.getText().length()); //set gCode viewer to bottom
		}			
		//unused but necessary fields for DocumentListener
		public void changedUpdate(DocumentEvent e){}
		public void removeUpdate(DocumentEvent e){}
	}

	//settings listeners
	public class saveSettingsListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			chooseFile.fileDialog("Save Settings", saveFile, "settings");
			String settingsSaveFile = chooseFile.getFilePath();

			if(!settingsSaveFile.endsWith(".wst")){
				settingsSaveFile += ".wst"; //add file extension if text output file doesn't already have it
			}

			try{
				BufferedWriter bw = new BufferedWriter(new FileWriter(settingsSaveFile));

				bw.write(VisualPanel.letHeightField.getText() + "\n");
				bw.write(VisualPanel.letWidthField.getText() + "\n");
				bw.write(VisualPanel.kerningField.getText() + "\n");
				bw.write(VisualPanel.zDrawField.getText() + "\n");
				bw.write(VisualPanel.zMoveField.getText() + "\n");
				bw.write(VisualPanel.feedrateField.getText() + "\n");
				bw.write(VisualPanel.saField.getText() + "\n");
				bw.write(VisualPanel.fontBox.getSelectedItem().toString() + "\n");
				bw.write(VisualPanel.pWidthField.getText() + "\n");
				bw.write(VisualPanel.pHeightField.getText() + "\n");
				bw.write(VisualPanel.lineSpacingField.getText() + "\n");
				bw.write(VisualPanel.scrField.getText() + "\n");

				bw.write("\n//KEY:\nfont height (double) [mm]\n"
						+ "font width (double) [mm]\n"
						+ "kerning (double) [mm]\n"
						+ "zDraw (double) [mm]\n"
						+ "zMove (double) [mm]\n"
						+ "federate (double) [mm/min]\n"
						+ "shear angle for italics (double) (degrees)\n"
						+ "font family (String)\n"
						+ "page width (double) [mm]\n"
						+ "page height (double) [mm]\n"
						+ "line spacing (double) [mm]\n"
						+ "small caps ratio (double) [mm]"); //add key at end

				bw.close();
			}
			catch (Exception exception) { // catch any/all possible exceptions
				Driver.abortReason = "Exception in Listeners.saveSettingsListener\n" + exception;
			}
		}
	}
	public class settingsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			chooseFile.fileDialog("Import Settings File", openFile, "settings"); //popup fileChooser dialog, 0 = open file

			if(chooseFile.getFilePath() != ""){ //don't import any settings if file window is closed before selection
				Driver.settingsFilename = chooseFile.getFilePath();
				WriteManager.readSettings(Driver.settingsFilename);

				VisualPanel.settingsLabel.setText(chooseFile.getFileName());
				VisualPanel.letHeightField.setText("" + WriteManager.height);
				VisualPanel.letWidthField.setText("" + WriteManager.width);
				VisualPanel.kerningField.setText("" + WriteManager.kerning);
				VisualPanel.feedrateField.setText("" + WriteManager.feedrate);
				VisualPanel.pHeightField.setText("" + WriteManager.pageHeight);
				VisualPanel.pWidthField.setText("" + WriteManager.pageWidth);
				VisualPanel.lineSpacingField.setText("" + WriteManager.lineSpacing);
				VisualPanel.zMoveField.setText("" + WriteManager.zMove);
				VisualPanel.zDrawField.setText("" + WriteManager.zDraw);
				VisualPanel.saField.setText("" + WriteManager.shearAngle);
				VisualPanel.scrField.setText("" + WriteManager.scRatio);
				VisualPanel.fontBox.setSelectedIndex(FontManager.getFontIndex(WriteManager.font));
			}
		}
	}

	//jogging listeners
	public class posXListener implements ActionListener{
		public void actionPerformed(ActionEvent e){ //all moves in relative coordinates
			SerialConnector.sendGCode("G0X" + VisualPanel.xyIncrement.getText() + "\n");
		}
	}
	public class negXListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SerialConnector.sendGCode("G0X-" + VisualPanel.xyIncrement.getText() + "\n");
		}
	}
	public class posYListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SerialConnector.sendGCode("G0Y" + VisualPanel.xyIncrement.getText() + "\n");
		}
	}
	public class negYListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SerialConnector.sendGCode("G0Y-" + VisualPanel.xyIncrement.getText() + "\n");
		}
	}
	public class posZListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SerialConnector.sendGCode("G0Z" + VisualPanel.zIncrement.getText() + "\n");
		}
	}
	public class negZListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SerialConnector.sendGCode("G0Z-" + VisualPanel.zIncrement.getText() + "\n");
		}
	}
	/*	public class xyHomeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SerialConnector.sendGCode("G28.3 X0Y0\n");
		}
	}
	public class zHomeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SerialConnector.sendGCode("G28.3 Z0\n");
		}
	} */

} 
