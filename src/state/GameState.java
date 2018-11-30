package state;

import java.awt.Graphics;

import entities.creatures.Player;
import main.Game;
import main.tiles.Tile;
import main.worlds.World;

public class GameState extends State {
	
	private Player player;
	private World world;

	public GameState(Game game){
		super(game);
		player = new Player(game, 100, 100);
		world = new World("yee");
	}
	
	public void tick() {
		world.tick();
		player.tick();
	}

	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}

}
