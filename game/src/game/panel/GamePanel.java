package game.panel;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import common.BaseCore;
import common.gamestate.SceneManager;

public class GamePanel extends JPanel implements Runnable, KeyListener,
		BaseCore {

	private static final long serialVersionUID = 1L;

	private final int WIDTH = 320;
	private final int HEIGHT = 240;
	private final int FPS = 60;
	private long targetTime = 1000 / this.FPS;

	private Thread gameThread;

	private boolean running;

	private BufferedImage image;

	private Graphics2D graphics;

	private SceneManager manager;

	public GamePanel() {
		super();
		this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
		this.setFocusable(true);
		this.setVisible(true);
		this.requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (this.gameThread == null) {
			this.gameThread = new Thread(this);
			addKeyListener(this);
			this.gameThread.start();
		}
	}

	@Override
	public void start() {
		this.manager = new SceneManager();
		this.image = new BufferedImage(this.WIDTH, this.HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		this.graphics = (Graphics2D) this.image.getGraphics();
		this.running = true;
	}

	@Override
	public void run() {
		this.start();

		long start, elapsed, wait;

		while (this.running) {
			start = System.nanoTime();

			this.update();
			this.draw(this.graphics);

			elapsed = System.nanoTime() - start;
			wait = this.targetTime - elapsed / 1000000;
			if (wait < 0) {
				wait = 0;
			}

			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean update() {
		this.manager.update();
		return true;
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.manager.draw(graphics);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.manager.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.manager.keyReleased(e.getKeyCode());
	}
}
