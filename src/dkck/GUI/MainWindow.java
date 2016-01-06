package dkck.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dkck.Bomb;
import dkck.Interpreter;
import dkck.Item;
import dkck.ItemsOperations;
import dkck.Point;
import dkck.Rocket;
import dkck.Sapper;
import dkck.Tree;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int gridRows = 50;

	public static final int gridColumns = 50;

	public static ItemsOperations itemsCollection;

	public static final Color cellColor = Color.WHITE;

	public static final Color bombActiveColor = Color.RED;

	public static final Color bombDisableColor = Color.LIGHT_GRAY;

	public static final Color bombDisarmedColor = Color.GRAY;

	public static final Color sapperAliveColor = new Color(122, 188, 255);

	public static final Color sapperDeadColor = new Color(122, 140, 255);

	public static final Color treeColor = new Color(138, 182, 107);

	public static final Color rocketColor = Color.ORANGE;

	public static final Class<?>[] classSequence = { Bomb.class, Sapper.class, Tree.class, Rocket.class, Point.class };

	public static Interpreter interpreter;

	// Window elements

	// Main panels

	private static GridPanel gridPanel = new GridPanel();
	private static RightPanel rightPanel = new RightPanel();
	private static BottomPanel bottomPanel = new BottomPanel();

	// GridPanel elements

	public static Grid grid = new Grid(gridRows, gridColumns);

	// RightPanel elements

	public static HPPanel HPPanel = new HPPanel();
	public static PositionPanel positionPanel = new PositionPanel();
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

	public static void main(String[] args) throws InterruptedException {

		new MainWindow();

		itemsCollection = new ItemsOperations();
		itemsCollection.addItems();
		interpreter = new Interpreter();

	}

	// metoda sprawdzajaca czy bomba istnieje
	static public Item findElementByID(int id, Class<?> typeOfItem) {

		for (int i = 0; i < itemsCollection.getItemsArray().size(); i++) {
			Item tempItem = itemsCollection.getItemsArray().get(i);

			if ((typeOfItem.isInstance(tempItem))
					&& (typeOfItem.equals(tempItem.getClass()) && tempItem.getId() == id)) {
				return tempItem;
			}
		}

		updateLog("Item nie istnieje");

		return null;
	}

	public MainWindow() {

		super();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(900, 578));

		// Add elements to panels

		gridPanel.add(grid);

		rightPanel.add(HPPanel);
		rightPanel.add(positionPanel);
		rightPanel.add(new JLabel("Zegar:"));
		rightPanel.add(timerPanel);
		rightPanel.add(new JLabel("Komunikaty:"));
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
