package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class SubmitButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubmitButton() {
		super();
		setText("Submit");
		setBackground(SystemColor.controlShadow);
		setForeground(Color.black);
		setPreferredSize(new Dimension(200, 30));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		});
		
	}
}
