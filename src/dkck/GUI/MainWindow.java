package dkck.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import dkck.ItemsOperations;

public class MainWindow extends JFrame {

	public static ItemsOperations itemsCollection;
	
	// Window elements
	
	public static Grid grid = new Grid();
	public static JPanel panel = new JPanel();
	public static JLabel HPPanel = new JLabel();
	public static JTextArea positionPanel = new JTextArea();
	public static JTextArea logPanel = new JTextArea();
	public static JScrollPane logScrollPane = new JScrollPane(logPanel);
	public static JTextField consoleIn = new JTextField();
	
	public static void updateLog(String message) {
		String log = MainWindow.logPanel.getText();
		MainWindow.logPanel.setText(message + "\n" + log);
	}
	
	public static void updateHPPanel(String message) {
		MainWindow.HPPanel.setText(message);
	}
	
	public static void updatePositionPanel(String message) {
		MainWindow.positionPanel.setText(message);
	}
	
	public static void main(String[] args) throws InterruptedException {
		MainWindow mainWindow = new MainWindow();
		
		mainWindow.setSize(600, 700);
		itemsCollection = new ItemsOperations();
		itemsCollection.actions();	
	}
	
	public MainWindow() {
		
		super("Test");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Config
		
		panel.setBackground(Color.lightGray);
		panel.setPreferredSize(new Dimension(600, 170));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(null);
		
		HPPanel.setOpaque(true);
		HPPanel.setBackground(Color.white);
		HPPanel.setPreferredSize(new Dimension(150, 25));
		HPPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		HPPanel.setHorizontalAlignment(SwingConstants.CENTER);
		HPPanel.setBounds(new Rectangle(new Point(10, 10), HPPanel.getPreferredSize()));
		
		positionPanel.setBackground(Color.white);
		positionPanel.setPreferredSize(new Dimension(150, 25));
		positionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		positionPanel.setLineWrap(true);
		positionPanel.setBounds(new Rectangle(new Point(10, 45), positionPanel.getPreferredSize()));
		
		logScrollPane.setBackground(Color.white);
		logScrollPane.setPreferredSize(new Dimension(410, 110));
		logScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		logScrollPane.setBounds(new Rectangle(new Point(170, 10), logScrollPane.getPreferredSize()));
				
		consoleIn.setBackground(Color.white);
		consoleIn.setPreferredSize(new Dimension(570, 30));
		consoleIn.setBorder(BorderFactory.createLineBorder(Color.black));
		consoleIn.setBounds(new Rectangle(new Point(10, 130), consoleIn.getPreferredSize()));
		
		//Add elements to window
		
		panel.add(HPPanel);
		panel.add(positionPanel);
		panel.add(logScrollPane);
		panel.add(consoleIn);
		
		add(grid, BorderLayout.NORTH);
		add(panel, BorderLayout.SOUTH);
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
	}

}
