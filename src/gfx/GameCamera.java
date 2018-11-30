package gfx;

import entities.Entity;
import main.Game;

public class GameCamera {
	
	// how far off do we draw something from its original position?
	private float xOffset, yOffset;
	private Game game;

	public GameCamera(Game game, float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.game = game;
	}
	
	public void centerOnEntity(Entity e){
		xOffset = e.getX() - game.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - game.getHeight() / 2 + e.getHeight() / 2;
	}
	
	public void move(float xAmt, float yAmt){
		this.xOffset += xAmt;
		this.yOffset += yAmt;
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}


}
