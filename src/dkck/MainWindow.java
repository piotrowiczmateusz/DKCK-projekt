import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	public static void main(String[] args) {
		new MainWindow();
	}
	
	public MainWindow() {
		super("Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10,10));
		add(new Grid(), BorderLayout.NORTH);
		add(new JTextField(),BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

}
