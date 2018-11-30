package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import main.Handler;

public class Player extends Creature{
	
	public Player(Handler handler, float x, float y) {
		// default width and height
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 11*2;
		bounds.y = 19*2;
		bounds.width = 17;
		bounds.height = 26;
	}
	
	@Override
	public void tick() {
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput(){
		this.xMove = 0;
		this.yMove = 0;
		if(handler.getKeyManager().up)
			yMove -= speed;
		if(handler.getKeyManager().down)
			yMove += speed;
		if(handler.getKeyManager().left)
			xMove -= speed;
		if(handler.getKeyManager().right)
			xMove += speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.draav_female, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
		
}
