package spacepong;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameTrigger {

	public static void main(String[] args) {
		JFrame game = new JFrame("Space Pong");
		Game newGame = new Game();
		ButtonPanel buttonPanel = new ButtonPanel();
		InfoBoard scores = new InfoBoard();
		
		game.setSize(1024, 745);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);

		game.add(scores, BorderLayout.NORTH);
		game.add(newGame, BorderLayout.CENTER);
		game.add(buttonPanel, BorderLayout.SOUTH);
	}

}
