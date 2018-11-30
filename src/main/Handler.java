package main;

import gfx.GameCamera;
import main.input.KeyManager;
import main.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	/**
	 * 
	 * @return the width of the window
	 */
	public int getWidth(){
		return this.game.getWidth();
	}
	
	/**
	 * 
	 * @return the height of the window
	 */
	public int getHeight(){
		return this.game.getHeight();
	}
	
	public GameCamera getGameCamera(){
		return this.game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return this.game.getKeyManager();
	}
	
	// GETTERS AND SETTERS

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
