package main.tiles;

import gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.rock_tile_aa_1, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
