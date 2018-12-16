package entities.statics;

import gfx.Assets;
import main.Handler;
import main.tiles.Tile;

import java.awt.Graphics;

public class Rock extends StaticEntity {

  public Rock(Handler handler, float x, float y) {
    super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

    bounds.x = 3;
    bounds.y = 16;
    bounds.width = 26;
    bounds.height = 12;
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub

  }

  @Override
  public void render(Graphics g) {
    g.drawImage(Assets.rock_tile_aa_1, (int) (x - handler.getGameCamera().getxOffset()),
        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    // showCollisionBox(g);
  }

}
