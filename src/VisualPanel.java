import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.ScrollPane;
import java.awt.FlowLayout;

public class VisualPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTextField letHeightField;
	public static JTextField letWidthField;
	public static JTextField kerningField;
	public static JTextField saField;
	public static JTextField scrField;
	public static JTextField lineSpacingField;
	public static JTextField zMoveField;
	public static JTextField pHeightField;
	public static JTextField pWidthField;
	public static JTextField feedrateField;
	public static JTextField zDrawField;
	public static JLabel settingsLabel;
	public static JLabel textLabel;
	public static JLabel exportFileMessage;
	public static JLabel zPathLabel;
	public static JLabel zPathImage;
	
	@SuppressWarnings("rawtypes")
	public static JComboBox/*<String>*/ fontBox;
	@SuppressWarnings("rawtypes")
	public static JComboBox/*<String>*/ zPathBox;

	public static JButton btnConnect;

	public static JButton exportButton;
	public static JButton settingsInputSet;
	public static JButton textInputSet;
	public static JEditorPane editorPane;

	public static String[] zPathList = {"Vertical Drop (More Reliable)", "Modified Sine (Smoother)"};

	public Listeners listen = new Listeners();
	public static JTextField inputText;
	public static JTextField tinyGOutputField;
	public static JTextPane outputText;
	public static JLabel lblTinygOutput;
	public static JLabel lblNotConnected;
	public static JTextPane sentGCode;
	public static JTextField gCode;
	public static ScrollPane scrollPane;

	//joggers
	public static JTextField zIncrement;
	public static JTextField xyIncrement;
	public static JButton btnx;
	public static JButton btnx_1;
	public static JButton btny;
	public static JButton btny_1;
	public static JButton btnz;
	public static JButton btnz_1;
	public static JButton btnZHome;
	public static JButton btnXYHome;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VisualPanel(String[] fontList) {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBackground(SystemColor.window);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{158, 76, 0, 147, 47, 0, 0};
		gbl_panel_3.rowHeights = new int[]{41, 0, 0, 0, 0, 0, 0, 0, 35, -81, 26, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.SOUTH;
		gbc_label.gridwidth = 6;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_3.add(label, gbc_label);
		label.setIcon(new ImageIcon(VisualPanel.class.getResource("/icons/visualBlock.png")));

		JLabel lblLetterHeight = new JLabel("Letter Height [mm]:");
		lblLetterHeight.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblLetterHeight.setForeground(Color.DARK_GRAY);
		lblLetterHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblLetterHeight = new GridBagConstraints();
		gbc_lblLetterHeight.anchor = GridBagConstraints.EAST;
		gbc_lblLetterHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblLetterHeight.gridx = 0;
		gbc_lblLetterHeight.gridy = 1;
		panel_3.add(lblLetterHeight, gbc_lblLetterHeight);

		letHeightField = new JTextField("" + WriteManager.height);
		letHeightField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		letHeightField.setForeground(Color.BLUE);
		GridBagConstraints gbc_letHeightField = new GridBagConstraints();
		gbc_letHeightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_letHeightField.insets = new Insets(0, 0, 5, 5);
		gbc_letHeightField.gridx = 1;
		gbc_letHeightField.gridy = 1;
		panel_3.add(letHeightField, gbc_letHeightField);
		letHeightField.setColumns(4);

		JLabel signatureText_2 = new JLabel("zDraw Depth [mm]:");
		signatureText_2.setFont(new Font("Eurostile", Font.PLAIN, 14));
		signatureText_2.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_signatureText_2 = new GridBagConstraints();
		gbc_signatureText_2.anchor = GridBagConstraints.EAST;
		gbc_signatureText_2.insets = new Insets(0, 0, 5, 5);
		gbc_signatureText_2.gridx = 3;
		gbc_signatureText_2.gridy = 1;
		panel_3.add(signatureText_2, gbc_signatureText_2);

		zDrawField = new JTextField("" + WriteManager.zDraw);
		zDrawField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		zDrawField.setForeground(Color.BLUE);
		GridBagConstraints gbc_zDrawField = new GridBagConstraints();
		gbc_zDrawField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zDrawField.insets = new Insets(0, 0, 5, 5);
		gbc_zDrawField.gridx = 4;
		gbc_zDrawField.gridy = 1;
		panel_3.add(zDrawField, gbc_zDrawField);
		zDrawField.setColumns(4);

		JLabel lblLetterWidthmm = new JLabel("Letter Width [mm]:");
		lblLetterWidthmm.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblLetterWidthmm.setForeground(Color.DARK_GRAY);
		lblLetterWidthmm.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblLetterWidthmm = new GridBagConstraints();
		gbc_lblLetterWidthmm.anchor = GridBagConstraints.EAST;
		gbc_lblLetterWidthmm.insets = new Insets(0, 0, 5, 5);
		gbc_lblLetterWidthmm.gridx = 0;
		gbc_lblLetterWidthmm.gridy = 2;
		panel_3.add(lblLetterWidthmm, gbc_lblLetterWidthmm);

		letWidthField = new JTextField("" + WriteManager.width);
		letWidthField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		letWidthField.setForeground(Color.BLUE);
		GridBagConstraints gbc_letWidthField = new GridBagConstraints();
		gbc_letWidthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_letWidthField.insets = new Insets(0, 0, 5, 5);
		gbc_letWidthField.gridx = 1;
		gbc_letWidthField.gridy = 2;
		panel_3.add(letWidthField, gbc_letWidthField);
		letWidthField.setColumns(4);

		JLabel lblZmoveDepthmm = new JLabel("zMove Height [mm]:");
		lblZmoveDepthmm.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblZmoveDepthmm.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblZmoveDepthmm = new GridBagConstraints();
		gbc_lblZmoveDepthmm.insets = new Insets(0, 0, 5, 5);
		gbc_lblZmoveDepthmm.anchor = GridBagConstraints.EAST;
		gbc_lblZmoveDepthmm.gridx = 3;
		gbc_lblZmoveDepthmm.gridy = 2;
		panel_3.add(lblZmoveDepthmm, gbc_lblZmoveDepthmm);

		zMoveField = new JTextField("" + WriteManager.zMove);
		zMoveField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		zMoveField.setForeground(Color.BLUE);
		zMoveField.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_zMoveField = new GridBagConstraints();
		gbc_zMoveField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zMoveField.insets = new Insets(0, 0, 5, 5);
		gbc_zMoveField.gridx = 4;
		gbc_zMoveField.gridy = 2;
		panel_3.add(zMoveField, gbc_zMoveField);
		zMoveField.setColumns(4);

		JLabel lblKerningmm = new JLabel("Kerning [mm]:");
		lblKerningmm.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblKerningmm.setForeground(Color.DARK_GRAY);
		lblKerningmm.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblKerningmm = new GridBagConstraints();
		gbc_lblKerningmm.anchor = GridBagConstraints.EAST;
		gbc_lblKerningmm.insets = new Insets(0, 0, 5, 5);
		gbc_lblKerningmm.gridx = 0;
		gbc_lblKerningmm.gridy = 3;
		panel_3.add(lblKerningmm, gbc_lblKerningmm);

		kerningField = new JTextField("" + WriteManager.kerning);
		kerningField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		kerningField.setForeground(Color.BLUE);
		GridBagConstraints gbc_kerningField = new GridBagConstraints();
		gbc_kerningField.fill = GridBagConstraints.HORIZONTAL;
		gbc_kerningField.insets = new Insets(0, 0, 5, 5);
		gbc_kerningField.gridx = 1;
		gbc_kerningField.gridy = 3;
		panel_3.add(kerningField, gbc_kerningField);
		kerningField.setColumns(4);

		JLabel lblPageWidthmm = new JLabel("Page Height [mm]:");
		lblPageWidthmm.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblPageWidthmm.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblPageWidthmm = new GridBagConstraints();
		gbc_lblPageWidthmm.anchor = GridBagConstraints.EAST;
		gbc_lblPageWidthmm.insets = new Insets(0, 0, 5, 5);
		gbc_lblPageWidthmm.gridx = 3;
		gbc_lblPageWidthmm.gridy = 3;
		panel_3.add(lblPageWidthmm, gbc_lblPageWidthmm);

		pHeightField = new JTextField("" + WriteManager.pageHeight);
		pHeightField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		pHeightField.setForeground(Color.BLUE);
		GridBagConstraints gbc_pHeightField = new GridBagConstraints();
		gbc_pHeightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pHeightField.insets = new Insets(0, 0, 5, 5);
		gbc_pHeightField.gridx = 4;
		gbc_pHeightField.gridy = 3;
		panel_3.add(pHeightField, gbc_pHeightField);
		pHeightField.setColumns(4);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 5;
		gbc_horizontalStrut.gridy = 3;
		panel_3.add(horizontalStrut, gbc_horizontalStrut);

		JLabel lblSmallcapsRatio = new JLabel("SmallCaps Ratio:");
		lblSmallcapsRatio.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblSmallcapsRatio.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblSmallcapsRatio = new GridBagConstraints();
		gbc_lblSmallcapsRatio.insets = new Insets(0, 0, 5, 5);
		gbc_lblSmallcapsRatio.anchor = GridBagConstraints.EAST;
		gbc_lblSmallcapsRatio.gridx = 0;
		gbc_lblSmallcapsRatio.gridy = 4;
		panel_3.add(lblSmallcapsRatio, gbc_lblSmallcapsRatio);

		scrField = new JTextField("" + WriteManager.scRatio);
		scrField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		scrField.setForeground(Color.BLUE);
		GridBagConstraints gbc_scrField = new GridBagConstraints();
		gbc_scrField.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrField.insets = new Insets(0, 0, 5, 5);
		gbc_scrField.gridx = 1;
		gbc_scrField.gridy = 4;
		panel_3.add(scrField, gbc_scrField);
		scrField.setColumns(4);

		JLabel lblPageWidthmm_1 = new JLabel("Page Width [mm]:");
		lblPageWidthmm_1.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblPageWidthmm_1.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblPageWidthmm_1 = new GridBagConstraints();
		gbc_lblPageWidthmm_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPageWidthmm_1.anchor = GridBagConstraints.EAST;
		gbc_lblPageWidthmm_1.gridx = 3;
		gbc_lblPageWidthmm_1.gridy = 4;
		panel_3.add(lblPageWidthmm_1, gbc_lblPageWidthmm_1);

		pWidthField = new JTextField("" + WriteManager.pageWidth);
		pWidthField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		pWidthField.setForeground(Color.BLUE);
		GridBagConstraints gbc_pWidthField = new GridBagConstraints();
		gbc_pWidthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pWidthField.insets = new Insets(0, 0, 5, 5);
		gbc_pWidthField.gridx = 4;
		gbc_pWidthField.gridy = 4;
		panel_3.add(pWidthField, gbc_pWidthField);
		pWidthField.setColumns(4);

		JLabel signatureText_1 = new JLabel("Shear Angle [degrees]:");
		signatureText_1.setFont(new Font("Eurostile", Font.PLAIN, 14));
		signatureText_1.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_signatureText_1 = new GridBagConstraints();
		gbc_signatureText_1.anchor = GridBagConstraints.EAST;
		gbc_signatureText_1.insets = new Insets(0, 0, 5, 5);
		gbc_signatureText_1.gridx = 0;
		gbc_signatureText_1.gridy = 5;
		panel_3.add(signatureText_1, gbc_signatureText_1);

		saField = new JTextField("" + WriteManager.shearAngle);
		saField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		saField.setForeground(Color.BLUE);
		GridBagConstraints gbc_saField = new GridBagConstraints();
		gbc_saField.fill = GridBagConstraints.HORIZONTAL;
		gbc_saField.insets = new Insets(0, 0, 5, 5);
		gbc_saField.gridx = 1;
		gbc_saField.gridy = 5;
		panel_3.add(saField, gbc_saField);
		saField.setColumns(4);

		JLabel signatureText_3 = new JLabel("Feedrate [mm/min]:");
		signatureText_3.setFont(new Font("Eurostile", Font.PLAIN, 14));
		signatureText_3.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_signatureText_3 = new GridBagConstraints();
		gbc_signatureText_3.insets = new Insets(0, 0, 5, 5);
		gbc_signatureText_3.anchor = GridBagConstraints.EAST;
		gbc_signatureText_3.gridx = 3;
		gbc_signatureText_3.gridy = 5;
		panel_3.add(signatureText_3, gbc_signatureText_3);

		feedrateField = new JTextField("" + WriteManager.feedrate);
		feedrateField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		feedrateField.setForeground(Color.BLUE);
		GridBagConstraints gbc_feedrateField = new GridBagConstraints();
		gbc_feedrateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_feedrateField.insets = new Insets(0, 0, 5, 5);
		gbc_feedrateField.gridx = 4;
		gbc_feedrateField.gridy = 5;
		panel_3.add(feedrateField, gbc_feedrateField);
		feedrateField.setColumns(4);

		JLabel lblLineSpacingmm = new JLabel("Line Spacing [mm]:");
		lblLineSpacingmm.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblLineSpacingmm.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblLineSpacingmm = new GridBagConstraints();
		gbc_lblLineSpacingmm.anchor = GridBagConstraints.EAST;
		gbc_lblLineSpacingmm.insets = new Insets(0, 0, 5, 5);
		gbc_lblLineSpacingmm.gridx = 0;
		gbc_lblLineSpacingmm.gridy = 6;
		panel_3.add(lblLineSpacingmm, gbc_lblLineSpacingmm);

		lineSpacingField = new JTextField("" + WriteManager.lineSpacing);
		lineSpacingField.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lineSpacingField.setForeground(Color.BLUE);
		GridBagConstraints gbc_lineSpacingField = new GridBagConstraints();
		gbc_lineSpacingField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lineSpacingField.insets = new Insets(0, 0, 5, 5);
		gbc_lineSpacingField.gridx = 1;
		gbc_lineSpacingField.gridy = 6;
		panel_3.add(lineSpacingField, gbc_lineSpacingField);
		lineSpacingField.setColumns(4);

		JLabel lblFontFamily = new JLabel("Font Family:");
		lblFontFamily.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblFontFamily.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblFontFamily = new GridBagConstraints();
		gbc_lblFontFamily.insets = new Insets(0, 0, 5, 5);
		gbc_lblFontFamily.anchor = GridBagConstraints.EAST;
		gbc_lblFontFamily.gridx = 3;
		gbc_lblFontFamily.gridy = 6;
		panel_3.add(lblFontFamily, gbc_lblFontFamily);

		fontBox = new JComboBox/*<String>*/(fontList);
		((JLabel) fontBox.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		fontBox.setFont(new Font("Eurostile", Font.PLAIN, 14));
		fontBox.setForeground(Color.BLUE);
		GridBagConstraints gbc_fontBox = new GridBagConstraints();
		gbc_fontBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_fontBox.insets = new Insets(0, 0, 5, 5);
		gbc_fontBox.gridx = 4;
		gbc_fontBox.gridy = 6;
		panel_3.add(fontBox, gbc_fontBox);
		fontBox.setSelectedIndex(0); //set to "Fast" font initially

		zPathLabel = new JLabel("Z Path Profile:");
		zPathLabel.setForeground(Color.DARK_GRAY);
		zPathLabel.setFont(new Font("Eurostile", Font.PLAIN, 14));
		zPathLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_zPathLabel = new GridBagConstraints();
		gbc_zPathLabel.anchor = GridBagConstraints.EAST;
		gbc_zPathLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zPathLabel.gridx = 0;
		gbc_zPathLabel.gridy = 7;
		panel_3.add(zPathLabel, gbc_zPathLabel);

		zPathBox = new JComboBox/*<String>*/(zPathList);
		zPathBox.addActionListener(listen.new zPathListener());
		((JLabel) zPathBox.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		zPathBox.setForeground(Color.BLUE);
		zPathBox.setFont(new Font("Eurostile", Font.PLAIN, 14));
		GridBagConstraints gbc_zPathBox = new GridBagConstraints();
		gbc_zPathBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_zPathBox.gridwidth = 4;
		gbc_zPathBox.insets = new Insets(0, 0, 5, 5);
		gbc_zPathBox.gridx = 1;
		gbc_zPathBox.gridy = 7;
		panel_3.add(zPathBox, gbc_zPathBox);
		//zPathBox.setSelectedIndex(0);

		zPathImage = new JLabel("");
		zPathImage.setIcon(new ImageIcon(VisualPanel.class.getResource("/icons/zVertMove.png")));
		GridBagConstraints gbc_zPathImage = new GridBagConstraints();
		gbc_zPathImage.gridwidth = 6;
		gbc_zPathImage.insets = new Insets(0, 0, 5, 0);
		gbc_zPathImage.gridx = 0;
		gbc_zPathImage.gridy = 8;
		panel_3.add(zPathImage, gbc_zPathImage);

		JLabel lblImportSettingsFrom = new JLabel("Current Settings:");
		lblImportSettingsFrom.setFont(new Font("Eurostile", Font.PLAIN, 14));
		lblImportSettingsFrom.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblImportSettingsFrom = new GridBagConstraints();
		gbc_lblImportSettingsFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblImportSettingsFrom.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblImportSettingsFrom.gridx = 0;
		gbc_lblImportSettingsFrom.gridy = 9;
		panel_3.add(lblImportSettingsFrom, gbc_lblImportSettingsFrom);

		settingsLabel = new JLabel("Default");
		settingsLabel.setForeground(Color.BLUE);
		settingsLabel.setFont(new Font("Eurostile", Font.PLAIN, 14));
		GridBagConstraints gbc_settingsLabel = new GridBagConstraints();
		gbc_settingsLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_settingsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_settingsLabel.gridx = 1;
		gbc_settingsLabel.gridy = 9;
		panel_3.add(settingsLabel, gbc_settingsLabel);

		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Eurostile", Font.PLAIN, 14));
		btnImport.addActionListener(listen.new settingsListener());
		btnImport.setForeground(Color.BLUE);
		GridBagConstraints gbc_btnImport = new GridBagConstraints();
		gbc_btnImport.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnImport.insets = new Insets(0, 0, 5, 5);
		gbc_btnImport.gridx = 3;
		gbc_btnImport.gridy = 9;
		panel_3.add(btnImport, gbc_btnImport);

		JButton saveSettings = new JButton("Save");
		saveSettings.addActionListener(listen.new saveSettingsListener());
		saveSettings.setForeground(Color.BLUE);
		saveSettings.setFont(new Font("Eurostile", Font.PLAIN, 14));
		GridBagConstraints gbc_saveSettings = new GridBagConstraints();
		gbc_saveSettings.anchor = GridBagConstraints.NORTH;
		gbc_saveSettings.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveSettings.insets = new Insets(0, 0, 5, 5);
		gbc_saveSettings.gridx = 4;
		gbc_saveSettings.gridy = 9;
		panel_3.add(saveSettings, gbc_saveSettings);

		JPanel panel_click = new JPanel();
		GridBagConstraints gbc_panel_click = new GridBagConstraints();
		gbc_panel_click.anchor = GridBagConstraints.SOUTH;
		gbc_panel_click.gridwidth = 6;
		gbc_panel_click.gridx = 0;
		gbc_panel_click.gridy = 10;
		gbc_panel_click.insets = new Insets(10,10,10,10);
		panel_3.add(panel_click, gbc_panel_click);
		panel_click.setBackground(SystemColor.window);

		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[]{50, 37, 47, 72, 67, 0};
		gbl_panel1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_click.setLayout(gbl_panel1);

		JButton btny = new JButton("+Y");
		GridBagConstraints gbc_btny = new GridBagConstraints();
		gbc_btny.fill = GridBagConstraints.HORIZONTAL;
		gbc_btny.anchor = GridBagConstraints.NORTH;
		gbc_btny.insets = new Insets(0, 0, 5, 5);
		gbc_btny.gridx = 1;
		gbc_btny.gridy = 0;
		panel_click.add(btny, gbc_btny);
		btny.setForeground(Color.DARK_GRAY);
		btny.addActionListener(listen.new posYListener());

	/*	JButton btnXYHome = new JButton("XY Home");
		btnXYHome.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnXYHome.setBackground(new Color(0, 128, 0));
		GridBagConstraints gbc_btnXYHome = new GridBagConstraints();
		gbc_btnXYHome.gridheight = 2;
		gbc_btnXYHome.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnXYHome.insets = new Insets(0, 0, 5, 5);
		gbc_btnXYHome.gridx = 3;
		gbc_btnXYHome.gridy = 0;
		panel_click.add(btnXYHome, gbc_btnXYHome);
		btnXYHome.setForeground(new Color(0, 128, 0));
		btnXYHome.addActionListener(listen.new xyHomeListener()); */

		btnz = new JButton("+Z");
		GridBagConstraints gbc_btnz = new GridBagConstraints();
		gbc_btnz.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnz.insets = new Insets(0, 0, 5, 0);
		gbc_btnz.anchor = GridBagConstraints.NORTH;
		gbc_btnz.gridx = 4;
		gbc_btnz.gridy = 0;
		panel_click.add(btnz, gbc_btnz);
		btnz.setForeground(Color.DARK_GRAY);
		btnz.addActionListener(listen.new posZListener());

		JLabel lblIncrementmm_1 = new JLabel("Increment [mm]");
		GridBagConstraints gbc_lblIncrementmm_1 = new GridBagConstraints();
		gbc_lblIncrementmm_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblIncrementmm_1.gridx = 1;
		gbc_lblIncrementmm_1.gridy = 1;
		panel_click.add(lblIncrementmm_1, gbc_lblIncrementmm_1);
		lblIncrementmm_1.setForeground(Color.DARK_GRAY);

		btnx = new JButton("-X");
		GridBagConstraints gbc_btnx = new GridBagConstraints();
		gbc_btnx.fill = GridBagConstraints.VERTICAL;
		gbc_btnx.gridheight = 2;
		gbc_btnx.anchor = GridBagConstraints.WEST;
		gbc_btnx.insets = new Insets(0, 0, 5, 5);
		gbc_btnx.gridx = 0;
		gbc_btnx.gridy = 1;
		panel_click.add(btnx, gbc_btnx);
		btnx.setForeground(Color.DARK_GRAY);
		btnx.addActionListener(listen.new negXListener());

		JLabel lblIncrementmm = new JLabel("Increment [mm]");
		lblIncrementmm.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblIncrementmm = new GridBagConstraints();
		gbc_lblIncrementmm.insets = new Insets(0, 0, 5, 0);
		gbc_lblIncrementmm.gridx = 4;
		gbc_lblIncrementmm.gridy = 1;
		panel_click.add(lblIncrementmm, gbc_lblIncrementmm);

		xyIncrement = new JTextField();
		xyIncrement.setForeground(new Color(0, 128, 0));
		xyIncrement.setHorizontalAlignment(SwingConstants.CENTER);
		xyIncrement.setText("10.0");
		GridBagConstraints gbc_xyIncrement = new GridBagConstraints();
		gbc_xyIncrement.fill = GridBagConstraints.HORIZONTAL;
		gbc_xyIncrement.anchor = GridBagConstraints.NORTH;
		gbc_xyIncrement.insets = new Insets(0, 0, 5, 5);
		gbc_xyIncrement.gridx = 1;
		gbc_xyIncrement.gridy = 2;
		panel_click.add(xyIncrement, gbc_xyIncrement);
		xyIncrement.setColumns(10);
		xyIncrement.addKeyListener(listen.new move());

		btnx_1 = new JButton("+X");
		GridBagConstraints gbc_btnx_1 = new GridBagConstraints();
		gbc_btnx_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnx_1.gridheight = 2;
		gbc_btnx_1.anchor = GridBagConstraints.WEST;
		gbc_btnx_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnx_1.gridx = 2;
		gbc_btnx_1.gridy = 1;
		panel_click.add(btnx_1, gbc_btnx_1);
		btnx_1.setForeground(Color.DARK_GRAY);
		btnx_1.addActionListener(listen.new posXListener());

	/*	btnZHome = new JButton("Z Home");
		btnZHome.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		GridBagConstraints gbc_btnZHome = new GridBagConstraints();
		gbc_btnZHome.insets = new Insets(0, 0, 0, 5);
		gbc_btnZHome.gridheight = 2;
		gbc_btnZHome.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnZHome.gridx = 3;
		gbc_btnZHome.gridy = 2;
		panel_click.add(btnZHome, gbc_btnZHome);
		btnZHome.setForeground(new Color(0, 128, 0));
		btnZHome.addActionListener(listen.new zHomeListener()); */

		zIncrement = new JTextField();
		zIncrement.setForeground(new Color(0, 128, 0));
		zIncrement.setHorizontalAlignment(SwingConstants.CENTER);
		zIncrement.setText("1.0");
		GridBagConstraints gbc_zIncrement = new GridBagConstraints();
		gbc_zIncrement.fill = GridBagConstraints.HORIZONTAL;
		gbc_zIncrement.anchor = GridBagConstraints.NORTH;
		gbc_zIncrement.insets = new Insets(0, 0, 5, 0);
		gbc_zIncrement.gridx = 4;
		gbc_zIncrement.gridy = 2;
		panel_click.add(zIncrement, gbc_zIncrement);
		zIncrement.setColumns(10);
		zIncrement.addKeyListener(listen.new move());

		btny_1 = new JButton("-Y");
		GridBagConstraints gbc_btny_1 = new GridBagConstraints();
		gbc_btny_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btny_1.anchor = GridBagConstraints.NORTH;
		gbc_btny_1.insets = new Insets(0, 0, 0, 5);
		gbc_btny_1.gridx = 1;
		gbc_btny_1.gridy = 3;
		panel_click.add(btny_1, gbc_btny_1);
		btny_1.setForeground(Color.DARK_GRAY);
		btny_1.addActionListener(listen.new negYListener());

		btnz_1 = new JButton("-Z");
		GridBagConstraints gbc_btnz_1 = new GridBagConstraints();
		gbc_btnz_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnz_1.anchor = GridBagConstraints.NORTH;
		gbc_btnz_1.gridx = 4;
		gbc_btnz_1.gridy = 3;
		panel_click.add(btnz_1, gbc_btnz_1);
		btnz_1.setForeground(Color.DARK_GRAY);
		btnz_1.addActionListener(listen.new negZListener());

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{119, 377, 96, 0};
		gbl_panel.rowHeights = new int[]{29, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblTinygTypewriter = new JLabel("TinyG TypeWriter");
		lblTinygTypewriter.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTinygTypewriter.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTinygTypewriter = new GridBagConstraints();
		gbc_lblTinygTypewriter.anchor = GridBagConstraints.WEST;
		gbc_lblTinygTypewriter.fill = GridBagConstraints.VERTICAL;
		gbc_lblTinygTypewriter.insets = new Insets(0, 10, 0, 5);
		gbc_lblTinygTypewriter.gridx = 0;
		gbc_lblTinygTypewriter.gridy = 0;
		panel.add(lblTinygTypewriter, gbc_lblTinygTypewriter);

		lblNotConnected = new JLabel("Not Connected");
		lblNotConnected.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNotConnected.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNotConnected = new GridBagConstraints();
		gbc_lblNotConnected.fill = GridBagConstraints.BOTH;
		gbc_lblNotConnected.insets = new Insets(0, 0, 0, 5);
		gbc_lblNotConnected.gridx = 1;
		gbc_lblNotConnected.gridy = 0;
		panel.add(lblNotConnected, gbc_lblNotConnected);

		btnConnect = new JButton("Connect");
		btnConnect.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnConnect.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnect.gridx = 2;
		gbc_btnConnect.gridy = 0;
		panel.add(btnConnect, gbc_btnConnect);
		btnConnect.addActionListener(listen.new connectListener());

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 92, 0};
		gbl_panel_1.rowHeights = new int[]{0, 461, 28, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JLabel lblTinygOutput = new JLabel("TinyG Output: ");
		lblTinygOutput.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTinygOutput.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblTinygOutput = new GridBagConstraints();
		gbc_lblTinygOutput.insets = new Insets(5, 0, 5, 0);
		gbc_lblTinygOutput.anchor = GridBagConstraints.WEST;
		gbc_lblTinygOutput.gridx = 0;
		gbc_lblTinygOutput.gridy = 0;
		panel_1.add(lblTinygOutput, gbc_lblTinygOutput);

		tinyGOutputField = new JTextField();
		GridBagConstraints gbc_tinyGOutputField = new GridBagConstraints();
		gbc_tinyGOutputField.insets = new Insets(5, 0, 5, 5);
		gbc_tinyGOutputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_tinyGOutputField.gridx = 1;
		gbc_tinyGOutputField.gridy = 0;
		panel_1.add(tinyGOutputField, gbc_tinyGOutputField);
		tinyGOutputField.setColumns(10);
		tinyGOutputField.setEditable(false);

		outputText = new JTextPane();
		GridBagConstraints gbc_outputText = new GridBagConstraints();
		gbc_outputText.gridwidth = 2;
		gbc_outputText.fill = GridBagConstraints.BOTH;
		gbc_outputText.insets = new Insets(0, 0, 5, 10);
		gbc_outputText.gridx = 0;
		gbc_outputText.gridy = 1;
		panel_1.add(outputText, gbc_outputText);
		outputText.setEditable(false);

		JLabel lblTypeTextHere = new JLabel("Type Text Here: ");
		lblTypeTextHere.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblTypeTextHere = new GridBagConstraints();
		gbc_lblTypeTextHere.insets = new Insets(0, 0, 5, 5);
		gbc_lblTypeTextHere.anchor = GridBagConstraints.WEST;
		gbc_lblTypeTextHere.gridx = 0;
		gbc_lblTypeTextHere.gridy = 2;
		panel_1.add(lblTypeTextHere, gbc_lblTypeTextHere);

		inputText = new JTextField();
		GridBagConstraints gbc_inputText = new GridBagConstraints();
		gbc_inputText.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputText.anchor = GridBagConstraints.NORTH;
		gbc_inputText.insets = new Insets(0,0,5,5);
		gbc_inputText.gridx = 1;
		gbc_inputText.gridy = 2;
		panel_1.add(inputText, gbc_inputText);
		inputText.setColumns(10);
		inputText.setHorizontalAlignment(SwingConstants.RIGHT);
		inputText.addKeyListener(listen.new returnListener());
		inputText.getDocument().addDocumentListener(listen.new textListener());
		inputText.getDocument().addDocumentListener(listen.new tinyGListener());

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.WEST);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 220, 0};
		gbl_panel_4.rowHeights = new int[]{16, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);

		scrollPane = new ScrollPane();
	//	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	//	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.insets = new Insets(10,10,5,10);
		gbc_scrollPane.gridy = 0;
		panel_4.add(scrollPane, gbc_scrollPane);

		sentGCode = new JTextPane();
		scrollPane.add(sentGCode);
		sentGCode.setEditable(false);
	//	scrollPane.add(sentGCode);

		JLabel lblSendGcode = new JLabel("Send GCode:");
		lblSendGcode.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblSendGcode = new GridBagConstraints();
		gbc_lblSendGcode.insets = new Insets(0, 10, 5, 0);
		gbc_lblSendGcode.anchor = GridBagConstraints.EAST;
		gbc_lblSendGcode.gridx = 1;
		gbc_lblSendGcode.gridy = 1;
		panel_4.add(lblSendGcode, gbc_lblSendGcode);

		gCode = new JTextField();
		GridBagConstraints gbc_gCode = new GridBagConstraints();
		gbc_gCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_gCode.gridx = 2;
		gbc_gCode.gridy = 1;
		gbc_gCode.insets = new Insets(0,0,5,10);
		panel_4.add(gCode, gbc_gCode);
		gCode.setColumns(10);
		gCode.addKeyListener(listen.new gCodeListener());
	}

}
