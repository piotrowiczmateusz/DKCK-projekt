package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class ConsoleIn extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsoleIn() {
		super();
		setBackground(Color.white);
		setPreferredSize(new Dimension(676, 30));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(new Rectangle(new Point(10, 130), getPreferredSize()));
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					String input = MainWindow.consoleIn.getText();
					
				    try {
						MainWindow.interpreter.interpret(input);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            MainWindow.updateLog("USER: " + input);	           
		            MainWindow.consoleIn.setText(null);	           
		        }        
			}	
		});
	}	 
}
