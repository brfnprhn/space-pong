package spacepong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {

	private static boolean playStatus = false;
	
	private Timer timer;
	
	private static int level = 1;
	private static int lives = 3;
	private static int score = 0;
	
	private Random random = new Random();

	private static int paddlePos = 441;
	private int animSpeed = 20;
	
	private static double xdir = 4;
	private static double ydir = 1;
	private static int xball = 10;
	private static int yball = 10;
	
	private static int ballRadius = 12;
	
	private boolean UFOChecker = true;
	private boolean starChecker = true;
	private boolean metChecker = true;
	private boolean healthChecker = true;
	private boolean paddleChecker = true;
	
	private BufferedImage imgStar;
	private BufferedImage imgUFO;
	private BufferedImage imgMeteorite;
	private BufferedImage imgHealth;
	
	private int starPosX = random.nextInt(930);
	private int starPosY = random.nextInt(500); 
	
	private int UFOPosX = random.nextInt(930);
	private int UFOPosY = random.nextInt(500);
	
	private int meteoritePosX = random.nextInt(930);
	private int meteoritePosY = random.nextInt(500);
	
	private int healthPosX = random.nextInt(930);
	private int healthPosY = random.nextInt(500);
	
	public static boolean getPlayStatus() {return playStatus;}
	public static void setPlayStatus(boolean playStatus) {Game.playStatus  = playStatus;}
	
	public static int getScore() {return score;}
	public static void setScore(int score) {Game.score = score;}
	
	public static int getLives() {return lives;}
	
	public static int getLevel() {return level;}
	public static void setLevel(int level) {Game.level = level;}
	
	public static double getXDir() {return xdir;}
	public static double getYDir() {return ydir;}
	public static void setXDir(double xdir) {Game.xdir = xdir;}
	public static void setYDir(double ydir) {Game.ydir = ydir;}
	
	public static void setXBall(int xball) {Game.xball = xball;}
	public static void setYBall(int yball) {Game.yball = yball;}
	
	public static void setPaddlePos(int paddlePos) {Game.paddlePos = paddlePos;}
	
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(animSpeed, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		backgroundSet(g);
		paddleSet(g);
		ballSet(g);
		borderSet(g);
		imageCreator(g);
		g.dispose();
	}
	
	private void backgroundSet(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, 1024, 768);
	}
	
	private void paddleSet(Graphics g) {
		requestFocus(true);
		g.setColor(Color.BLACK);
		g.fillRect(paddlePos, 635, 120, 10);
	}
	
	private void borderSet(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1, 768);
		g.fillRect(0, 0, 1024, 1);
		g.fillRect(1007, 0, 1, 768);
	}
		
	private void ballSet(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(xball, yball, ballRadius, ballRadius);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (playStatus) {
			timer.start();
			ballMovement();
			gameUpdater();
			imageAction();
			repaint();	
		}
		if (lives == 0) {
			playStatus = false;
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (playStatus) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (paddlePos >= 887) {
					paddlePos = 887;
				} else {
					paddlePos += 30;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (paddlePos <= 1) {
					paddlePos = 1;
				} else {
					paddlePos -= 30;
				}
			}
		}	
	}

	private void imageCreator(Graphics g) {
		try {
			Path pathStar = Paths.get("").resolve("Images/star.png").toAbsolutePath();
			Path pathUFO = Paths.get("").resolve("Images/ufo.png").toAbsolutePath();
			Path pathMeteorite = Paths.get("").resolve("Images/meteorite.png").toAbsolutePath();
			Path pathHealth = Paths.get("").resolve("Images/health.png").toAbsolutePath();
			
			imgStar = ImageIO.read(pathStar.toFile());
			imgUFO = ImageIO.read(pathUFO.toFile());
			imgMeteorite = ImageIO.read(pathMeteorite.toFile());
			imgHealth = ImageIO.read(pathHealth.toFile());
		
			for (int i = 0; i < 10; i++) {imageFixer();}
		
			g.drawImage(imgStar, starPosX, starPosY, imgStar.getWidth(), imgStar.getHeight(), null);
			g.drawImage(imgUFO, UFOPosX, UFOPosY, imgUFO.getWidth(), imgUFO.getHeight(), null);
			g.drawImage(imgMeteorite, meteoritePosX, meteoritePosY, imgMeteorite.getWidth(), imgMeteorite.getHeight(), null);
			g.drawImage(imgHealth, healthPosX, healthPosY, imgHealth.getWidth(), imgHealth.getHeight(), null);
			
		} catch (IOException e) {e.printStackTrace();}
	}	
	
	private void imageFixer() {
		Rectangle star = new Rectangle(starPosX, starPosY, imgStar.getHeight(), imgStar.getWidth());
		Rectangle UFO = new Rectangle(UFOPosX, UFOPosY, imgUFO.getHeight(), imgUFO.getWidth());
		Rectangle meteorite = new Rectangle(meteoritePosX, meteoritePosY, imgMeteorite.getHeight(), imgMeteorite.getWidth());
		Rectangle health = new Rectangle(healthPosX, healthPosY, imgHealth.getHeight(), imgHealth.getWidth());
		
		if (star.intersects(UFO) || star.intersects(meteorite) || star.intersects(health) ) {
			starPosX = random.nextInt(930);
			starPosY = random.nextInt(500);	
		}
		if (UFO.intersects(meteorite) || UFO.intersects(health)) {
			UFOPosX = random.nextInt(930);
			UFOPosY = random.nextInt(500);	
		}
		if (meteorite.intersects(health)) {
			meteoritePosX = random.nextInt(930);
			meteoritePosY = random.nextInt(500);
		}
	}
	
	
	private void imageAction() {
		Rectangle ball = new Rectangle(xball, yball, ballRadius, ballRadius);
		Rectangle star = new Rectangle(starPosX, starPosY, imgStar.getHeight(), imgStar.getWidth());
		Rectangle UFO = new Rectangle(UFOPosX, UFOPosY, imgUFO.getHeight(), imgUFO.getWidth());
		Rectangle meteorite = new Rectangle(meteoritePosX, meteoritePosY, imgMeteorite.getHeight(), imgMeteorite.getWidth());
		Rectangle health = new Rectangle(healthPosX, healthPosY, imgHealth.getHeight(), imgHealth.getWidth());
		
		if (ball.intersects(star) && starChecker) {
			score += 100;
			InfoBoard.getScores().setText(String.format("%28sSCORE: %d", " ", score));
			starChecker = false;
		}
		if (ball.intersects(UFO) && UFOChecker) {
			lives--;
			InfoBoard.getLives().setText(String.format("%28sLIVES: %d", " ", lives));
			UFOChecker = false;
		}
		if (ball.intersects(meteorite) && metChecker) {
			xdir *= 1.25;
			ydir *= 1.25;
			metChecker = false;
		}
		if (ball.intersects(health) && healthChecker) {
			lives++;
			InfoBoard.getLives().setText(String.format("%28sLIVES: %d", " ", lives));
			healthChecker = false;
		}
		
		if (!ball.intersects(UFO)) {UFOChecker = true;}
		if (!ball.intersects(star)) {starChecker = true;}
		if (!ball.intersects(meteorite)) {metChecker = true;}
		if (!ball.intersects(health)) {healthChecker = true;}
	}
	
	
	private void ballMovement() {
		if (xball >= 0 || xball <= 995 || yball >= 0) {
			xball += xdir;
			yball -= ydir;
			ydir -= 9.8*0.03;
		}
	
		if (xball < 0 || xball > 995) {
			xdir = -xdir;
		}
		if (yball < 0 ) {
			ydir = -ydir;
		}
	}
	
	
	private void gameUpdater() {
		Rectangle paddle = new Rectangle(paddlePos, 635, 120, 10);
		Rectangle ball = new Rectangle(xball, yball, ballRadius, ballRadius);
		if (ball.intersects(paddle) && paddleChecker) {
			ydir = -ydir;
			ydir -= 1.5;
			score++;
			InfoBoard.getScores().setText(String.format("%28sSCORE: %d", " ", score));
			paddleChecker = false;
		}
		if (yball > 695) {
			playStatus = false;
			xball = 10;
			yball = 10;
			xdir = 4;
			ydir = 1;
			lives--;
			InfoBoard.getLives().setText(String.format("%28sLIVES: %d", " ", lives));
		}
		if (!ball.intersects(paddle)) {paddleChecker = true;}
	}

	
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	

}
