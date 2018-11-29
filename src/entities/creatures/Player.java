package entities.creatures;

import java.awt.Graphics;

import gfx.Assets;
import gfx.ImageLoader;

public class Player extends Creature{

	public Player(float x, float y) {
		super(x, y);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.draav_female, (int) x, (int) y, null);
	}
		
}
