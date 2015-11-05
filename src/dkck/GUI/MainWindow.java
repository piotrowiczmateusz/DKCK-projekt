package dkck.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dkck.Interpreter;
import dkck.ItemsOperations;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int gridRows = 50;
	
	private static final int gridColumns = 50;

	public static ItemsOperations itemsCollection;
	
	public static Interpreter interpreter;

	// Window elements

	// Main panels

	private static GridPanel gridPanel = new GridPanel();
	private static RightPanel rightPanel = new RightPanel();
	private static BottomPanel bottomPanel = new BottomPanel();

	// GridPanel elements

	public static Grid grid = new Grid(gridRows, gridColumns);

	// RightPanel elements

	private static HPPanel HPPanel = new HPPanel();
	private static PositionPanel positionPanel = new PositionPanel();
	public static TimerPanel timerPanel = new TimerPanel();
	private static LogPanel logPanel = new LogPanel();

	// Bottom Panel elements

	public static ConsoleIn consoleIn = new ConsoleIn();
	private static SubmitButton submitButton = new SubmitButton();

	// Methods

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

	public static void updateTimerPanel(String message) {
		// String log = MainWindow.timerPanel.getText();
		// MainWindow.timerPanel.setText(message + "\n" + log);
	}

	public static void main(String[] args) throws InterruptedException {
		new MainWindow();
		
		itemsCollection = new ItemsOperations();
		itemsCollection.addItems();
		interpreter = new Interpreter();
		//itemsCollection.actions();
	}

	public MainWindow() {

		super();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 578));

		// Add elements to panels

		gridPanel.add(grid);

		rightPanel.add(HPPanel);
		rightPanel.add(positionPanel);
		rightPanel.add(new JLabel("Timer window:"));
		rightPanel.add(timerPanel);
		rightPanel.add(new JLabel("Log window:"));
		rightPanel.add(logPanel);

		bottomPanel.add(consoleIn);
		bottomPanel.add(submitButton);

		// add panels to window

		add(gridPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

	}
}
