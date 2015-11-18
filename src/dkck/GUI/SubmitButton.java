package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class SubmitButton extends JButton {

	private static final long serialVersionUID = 1L;

	public SubmitButton() {
		super();
		setText("Wykonaj");
		setBackground(SystemColor.controlShadow);
		setForeground(Color.black);
		setPreferredSize(new Dimension(200, 30));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainWindow.updateLog("USER: " + MainWindow.consoleIn.getText());
		        MainWindow.consoleIn.setText(null);
			}
		});
		
	}
}
