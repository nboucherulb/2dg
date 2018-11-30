package main.worlds;

import java.awt.Graphics;

import main.tiles.Tile;

public class World {
	
	// unit = number of tiles
	private int width, height;
	private int[][] tiles;
	
	public World(String path){
		loadWorld(path);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getTile(x, y).render(g, x * Tile.TILE_WIDTH, y *Tile.TILE_HEIGHT);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.grassTile;
		return t;
	}
	
	private void loadWorld(String path){
		
	}
	
}
