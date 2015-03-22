import java.io.IOException;
import jssc.*; //jssc resource: http://www.codeproject.com/Tips/801262/Sending-and-receiving-strings-from-COM-port-via-jS

public class SerialConnector {

	public static SerialPort serialPort = new SerialPort("/dev/tty.usbserial-DA00866A"); //shapeoko in MTL
//	public static SerialPort serialPort = new SerialPort("/dev/tty.usbserial-DA00XKGQ"); //shapeoko in VA
	public static boolean isConnected = false;

	public static void connect(){
		try{
			serialPort.openPort();

			serialPort.setParams(SerialPort.BAUDRATE_115200,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_XONXOFF_IN | SerialPort.FLOWCONTROL_XONXOFF_OUT);

			serialPort.addEventListener(new PortListener(), SerialPort.MASK_RXCHAR);

			isConnected = true;
			
			sendGCode("G91\nG21\n");
			sendGCode("G91\nG0X5Y5Z5\nG0X-5Y-5Z-5\n"); //startup moves
		}
		catch (SerialPortException ex) {
			System.out.println("There are an error on writing string to port т: " + ex);
		}	
	}

	public static void disconnect(){
		try{
			sendGCode("G0Z10\n"); //move up off of page
			sendGCode("$md\n"); //kill motors
			
			serialPort.closePort();
			isConnected = false;
		}
		catch (SerialPortException ex) {
			System.out.println("There are an error on writing string to port т: " + ex);
		}
	}

	public static void listPorts(){
		String[] portNames = SerialPortList.getPortNames();

		if (portNames.length == 0) {
			//	System.out.println("There are no serial-ports :( You can use an emulator, such as VSPE, to create a virtual serial port.");
			System.out.println("Press Enter to exit...");
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		for (int i = 0; i < portNames.length; i++){
			System.out.println(portNames[i]);
		} 
	}

	public static void sendGCode(String gCode){
		if(isConnected){
			try{
				serialPort.writeString(gCode);
				if(VisualPanel.sentGCode.getText().length() > 500){
					VisualPanel.sentGCode.setText(
							VisualPanel.sentGCode.getText().substring(
									VisualPanel.sentGCode.getText().length() - 500, VisualPanel.sentGCode.getText().length()-1) + "\n" + gCode);
				}
				else{
					VisualPanel.sentGCode.setText(VisualPanel.sentGCode.getText() + gCode);
				}
			}
			catch (SerialPortException ex) {
				System.out.println("Error in receiving string from COM-port: " + ex);
			}

			VisualPanel.scrollPane.setScrollPosition(0, VisualPanel.sentGCode.getText().length()); //set gCode viewer to bottom
	//		System.out.println("specified point: " + VisualPanel.sentGCode.getText().length()); //test prints
	//		System.out.println("Actual point: " + VisualPanel.scrollPane.getScrollPosition().toString());
		} 
	}

	private static class PortListener implements SerialPortEventListener {

		public void serialEvent(SerialPortEvent event) {
			if(event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					String receivedData = serialPort.readString(event.getEventValue());
					//System.out.println("Received response: " + receivedData);
					VisualPanel.tinyGOutputField.setText(receivedData);
				//	VisualPanel.sentGCode.setText(VisualPanel.sentGCode.getText() + receivedData); //tinyG output into sentGCode field
				}
				catch (SerialPortException ex) {
					System.out.println("Error in receiving string from COM-port: " + ex);
				}
			}
		}
	}
}