package dkck;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class LogPanel extends JFrame {

	private JTextArea textArea = null;

	private JScrollPane pane = null;

	public LogPanel(String title, int width, int height) {
	    
		super(title);
	    
	    setSize(100, 100);
	    textArea = new JTextArea();
	    pane = new JScrollPane(textArea);
	    getContentPane().add(pane);
	    setVisible(true);
	}
}
