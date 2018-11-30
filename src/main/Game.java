package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import gfx.Assets;
import main.input.KeyManager;
import state.GameState;
import state.MainMenuState;
import state.SavesState;
import state.SettingsState;
import state.State;

/**
 * 
 * This is the main class of the game. It'll run everything, start everything.
 *
 */
public class Game implements Runnable {

	private Display display;
	private String title;
	private int width, height;
	private boolean running = false;

	private Thread thread;
	
	// Bs is a way for the computer to draw things to the screen, using buffers to do so.
	// A buffer holds as much data as your screen.
	// We will use buffer -> buffer -> screen instead of buffer -> screen to avoid flickering.
	private BufferStrategy bs;
	
	// Alows us to draw things on the canvas. Full images, lines, etc.
	// It's a magical paintbrush!
	private Graphics g;
	
	// States
	private State gameState;
	private State mainMenuState;
	private State settingsState;
	private State savesState;
	
	private KeyManager keymanager;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.keymanager = new KeyManager();
	}
	
	private void init(){
		this.display = new Display(title, width, height);
		display.getFrame().addKeyListener(keymanager);
		Assets.init();
		
		gameState = new GameState(this);		
		mainMenuState = new MainMenuState(this);
		settingsState = new SettingsState(this);
		savesState = new SavesState(this);
		
		State.setState(gameState);
	}
	
	/**
	 * Refreshes the screen
	 */
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();		
		// Clears the screen
		g.clearRect(0, 0, width, height);
		
		if(State.getState() != null){
			State.getState().render(g);
		}
				
		bs.show();
		// g.dispose() frees memory faster than the garbage collector would. And a lot of g's will be created.
		g.dispose();
	}
	
	/**
	 * This method could also be called update().
	 * It updates all the game variables.
	 */
	private void tick(){
		keymanager.tick();
		
		if(State.getState() != null){
			State.getState().tick();
		}
	}

	public void run() {
		init();
		
		int fps = 60;
		// why 1B? Because there's 1B nanoseconds in a second.
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}		
			
			// if timer is higher than 1s
			if(timer > 1000000000){
				System.out.println("Ticks and and frames: "+ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
	public KeyManager getKeyManager(){
		return this.keymanager;
	}

	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
