import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class QueueManager {

	public static ArrayList<Character> queue = new ArrayList<Character>();

	public static void add(char c){
		queue.add(c);
	}
	
	public static void removeFirst(){
		queue.remove(0);

		Runnable changeText = new Runnable() {
			public void run() {
				VisualPanel.inputText.setText(VisualPanel.inputText.getText().substring(1));
			}
		};

		SwingUtilities.invokeLater(changeText); //last letter has been sent out, remove from input box
	}

	public static void removeLast(){
		queue.remove(queue.size()-1);
	}

	public static void sendOut(){
		if(queue.size() > 0 && SerialConnector.isConnected){ //change first char into letter and send to WriteManger
			Letter temp = new Letter("" + queue.get(0));
			WriteManager.writeLetter(temp);

			VisualPanel.outputText.setText(VisualPanel.outputText.getText() + queue.get(0)); //add first letter to end of output display

			QueueManager.removeFirst(); //get rid of first entry, because it has been sent and is no longer in the queue
		}
	}
}