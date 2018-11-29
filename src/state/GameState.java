package state;

import java.awt.Graphics;

import gfx.Assets;

public class GameState extends State {

	public GameState(){
		
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(Assets.flowered_grass, 0, 0, null);
	}

}
