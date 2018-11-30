package gfx;

import entities.Entity;
import main.Handler;
import main.tiles.Tile;

public class GameCamera {
	
	// how far off do we draw something from its original position?
	private float xOffset, yOffset;
	private Handler handler;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.handler = handler;
	}
	
	public void checkBlankSpaces(){
		if(xOffset < 0){
			xOffset = 0;
		}
		if(xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()){
			xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
		}
		if(yOffset < 0){
			yOffset = 0;
		}
		if(yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()){
			yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
		}
	}
	
	public void centerOnEntity(Entity e){
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpaces();
	}
	
	public void move(float xAmt, float yAmt){
		this.xOffset += xAmt;
		this.yOffset += yAmt;
		checkBlankSpaces();
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
