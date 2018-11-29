package state;

import java.awt.Graphics;

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
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
