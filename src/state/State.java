package state;

import java.awt.Graphics;

import main.Game;

/**
 * 
 * @author Nicolas
 *
 * This class acts as a game state manager
 */
public abstract class State {

	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	// CLASS METHODS
	protected Game game;
	
	public State(Game game){
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
