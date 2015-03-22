import java.awt.Dimension;
import javax.swing.*;

public class Driver extends JFrame {

	private static final long serialVersionUID = 1L;
	public static String settingsFilename = "";
	public static String abortReason = "";
	public static int zPath = 0; //0 = zSinePath, 1 = zVertPath
	public static VisualPanel p;

	public Driver() { // initializer
        p = new VisualPanel(FontManager.getFonts());
		add(p);
		
		setTitle("CNC Typewriter");
		setMinimumSize(new Dimension(1350, 700));
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
	}

	public static void main(String args[]) {
		new Driver();
	}
}