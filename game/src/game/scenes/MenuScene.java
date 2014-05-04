package game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import common.Vector;
import common.gamescene.BaseScene;
import common.gamescene.SceneManager;
import common.image.Background;

public class MenuScene extends BaseScene {

	private enum Option {
		START("Start"), EXIT("Exit");

		String name;

		Option(String name) {
			this.name = name;
		}
	}

	private Background background;

	private Option currentOption;

	private Font font;

	private Color selectedColor;

	private Color unselectedColor;

	public MenuScene(SceneManager manager) {
		super(manager);
	}

	@Override
	public void start() {
		String imageUrl = "/resources/backgrounds/menubg.gif";
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageUrl));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.background = new Background(image, new Vector(0, 0), new Vector(
				-0.1, 0));
		this.currentOption = Option.START;
		this.font = new Font("Arial", Font.PLAIN, 12);
		this.selectedColor = new Color(100, 0, 255);
		this.unselectedColor = new Color(0, 0, 0);
	}

	@Override
	public boolean update() {
		return this.background.update();
	}

	@Override
	public void draw(Graphics2D graphics) {
		this.background.draw(graphics);

		graphics.setFont(this.font);
		graphics.setColor(this.selectedColor);
		graphics.drawString("Game name", 50, 70);

		for (Option option : Option.values()) {
			if (option == this.currentOption) {
				graphics.setColor(this.selectedColor);
			} else {
				graphics.setColor(this.unselectedColor);
			}
			graphics.drawString(option.name, 145, 140 + option.ordinal() * 15);
		}
	}

	private void selectMenu() {
		switch (this.currentOption) {
		case START:
			break;
		default:
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_ENTER:
			this.selectMenu();
			break;
		case KeyEvent.VK_UP:
			if (this.currentOption != Option.START) {
				this.currentOption = Option.values()[this.currentOption
						.ordinal() - 1];
			}
			break;
		case KeyEvent.VK_DOWN:
			if (this.currentOption != Option.EXIT) {
				this.currentOption = Option.values()[this.currentOption
						.ordinal() + 1];
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(int keyCode) {
	}

}
