package entities.statics;

import gfx.Assets;
import main.Handler;
import main.tiles.Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tree extends StaticEntity {

  private TreeType type;

  public Tree(Handler handler, float x, float y) {
    super(handler, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 4);

    bounds.x = 18;
    bounds.y = 118;
    bounds.width = 31;
    bounds.height = 5;

    this.type = TreeType.SUMMER_01;
  }

  public Tree(Handler handler, float x, float y, TreeType type) {
    this(handler, x, y);
    this.type = type;
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub

  }

  @Override
  public void render(Graphics g) {
    BufferedImage asset;
    switch (type) {
      case SUMMER_01:
        asset = Assets.tree_summer_01;
        break;
      case SUMMER_02:
        asset = Assets.tree_summer_02;
        break;
      case WINTER_01:
        asset = Assets.tree_winter_01;
        break;
      case WINTER_02:
        asset = Assets.tree_winter_02;
        break;

      default:
        asset = Assets.tree_summer_01;
        break;

    }
    g.drawImage(asset, (int) (x - handler.getGameCamera().getxOffset()),
        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    // showCollisionBox(g);
  }

  public enum TreeType {
    SUMMER_01, SUMMER_02, WINTER_01, WINTER_02
  }
}
