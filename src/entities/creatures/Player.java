package entities.creatures;

import java.awt.Graphics;

import gfx.Assets;
import gfx.ImageLoader;
import main.Game;

public class Player extends Creature{
	
	private Game game;

	public Player(Game game, float x, float y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		// default width and height
		this.game = game;
	}
	
	@Override
	public void tick() {
		getInput();
		move();
	}
	
	private void getInput(){
		this.xMove = 0;
		this.yMove = 0;
		if(game.getKeyManager().up)
			yMove -= speed;
		if(game.getKeyManager().down)
			yMove += speed;
		if(game.getKeyManager().left)
			xMove -= speed;
		if(game.getKeyManager().right)
			xMove += speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.draav_female, (int) x, (int) y, width, height, null);
	}
		
}
