package spacepong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {

	private JButton button = new JButton("Play/Pause");
	private JButton reset = new JButton("Reset");
	public ButtonPanel() {
		GridLayout layout = new GridLayout(1, 1, 100, 100);
		setLayout(layout);
		
		setBackground(Color.GRAY);
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.addActionListener(this);
		add(button);
	
		reset.setBackground(Color.WHITE);
		reset.setForeground(Color.BLACK);
		reset.setFocusable(false);
		reset.addActionListener(this);
		add(reset);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(button)) {
			if (Game.getLives() > 0) {
				if (!Game.getPlayStatus()) {
					Game.setPlayStatus(true);
					InfoBoard.timer();
				} else {	
					Game.setPlayStatus(false);
				}
			}
		}
		
		if (e.getSource().equals(reset)) {
			Game.setPlayStatus(true);
			InfoBoard.getLevel().setText(String.format("%28sLEVEL: %d", " ", 1));
			InfoBoard.getLives().setText(String.format("%28sLIVES: %d"," ", 3));
			InfoBoard.getScores().setText(String.format("%28sSCORE: %d", " ", 0));
			InfoBoard.getTime().setText(String.format("%28sTIME: %s", " ", "01:00"));
			InfoBoard.setSec(60);
			InfoBoard.timer();
			
			Game.setYBall(10);
			Game.setXDir(4);
			Game.setYDir(1);
			Game.setXBall(10);
			Game.setPaddlePos(441);
		}
		
		
	}
}
