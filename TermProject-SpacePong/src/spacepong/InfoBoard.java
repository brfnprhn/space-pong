package spacepong;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class InfoBoard extends JPanel  {
	
	private static JLabel scores = new JLabel(String.format("%28sSCORE: %d", " ", Game.getScore()));
	private static JLabel lives = new JLabel(String.format("%28sLIVES: %d"," ", 3));
	private static JLabel levels = new JLabel(String.format("%28sLEVEL: %d", " ", 1));
	private static JLabel time = new JLabel(String.format("%28sTIME: %s", " ", "01:00"));

	private static Timer countdown;
	private static int sec = 60;
	
	public static JLabel getLevel() {return levels;}
	public static JLabel getScores() {return scores;}
	public static JLabel getLives() {return lives;}
	public static JLabel getTime() {return time;}
	
	public static void setSec(int sec) {InfoBoard.sec = sec;}
	
	public InfoBoard () {
		GridLayout layout = new GridLayout(1, 3);
		setLayout(layout);
		
		setBackground(Color.BLACK);
		scores.setForeground(Color.WHITE);
		lives.setForeground(Color.WHITE);
		levels.setForeground(Color.WHITE);
		time.setForeground(Color.WHITE);
		if (Game.getPlayStatus()) {
			timer();
		}
		
		add(scores);
		add(lives);
		add(levels);
		add(time);

	}

	public static void timer() {
		int delay = 1000;
		countdown = new Timer(delay, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (Game.getPlayStatus()) {
					sec--;
				}
				time.setText(String.format("%28sTIME: 00:%d", " ", sec));
				if (sec < 10) {
					time.setText(String.format("%28sTIME: 00:0%d", " ", sec));
				}
				if (!Game.getPlayStatus()) {
					countdown.stop();
				}
				
				if (sec == 0 && Game.getLives() > 0) {
					sec = 60;
					time.setText(String.format("%28sTIME: 01:00", " "));
					Game.setXDir(Game.getXDir() * 1.5);
					Game.setYDir(Game.getYDir() * 1.5);
					Game.setLevel(Game.getLevel() + 1);
					levels.setText(String.format("%28sLEVEL: %d", " ", Game.getLevel()));
				}
			}
			
			
		});
		countdown.start();
	}
}
