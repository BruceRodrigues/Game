package common.gamepanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import common.BaseCore;
import common.gamescene.SceneManager;

public abstract class BaseGamePanel extends JPanel implements Runnable,
		KeyListener, BaseCore {

	private static final long serialVersionUID = 1L;

	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public int FPS = 60;
	private long targetTime = 1000 / this.FPS;

	private Thread gameThread;

	private boolean running;

	protected BufferedImage image;

	protected Graphics2D graphics;

	protected SceneManager manager;

	public BaseGamePanel() {
		super();
		this.setPreferredSize(new Dimension(BaseGamePanel.WIDTH,
				BaseGamePanel.HEIGHT));
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
		this.image = new BufferedImage(BaseGamePanel.WIDTH,
				BaseGamePanel.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.graphics = (Graphics2D) this.image.getGraphics();
		this.running = true;

		this.addAllScenes();

	}

	public abstract void addAllScenes();

	@Override
	public void run() {
		this.start();

		long start, elapsed, wait;

		while (this.running) {
			start = System.nanoTime();

			this.update();
			this.draw(this.graphics);

			Graphics g = this.getGraphics();
			g.drawImage(this.image, 0, 0, BaseGamePanel.WIDTH,
					BaseGamePanel.HEIGHT, null);
			g.dispose();

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
