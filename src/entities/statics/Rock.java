package entities.statics;

import gfx.Assets;
import main.Handler;
import main.tiles.Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Rock extends StaticEntity {

  private RockType type;

  public Rock(Handler handler, float x, float y) {
    super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    bounds.x = 4;
    bounds.y = 24;
    bounds.width = 26;
    bounds.height = 8;
    this.type = RockType.EGG;
  }

  public Rock(Handler handler, float x, float y, RockType type) {
    this(handler, x, y);
    this.type = type;
    if (type.equals(RockType.LONG) || type.equals(RockType.LONG_MOSSY)) {
      this.setWidth(Tile.TILE_WIDTH * 2);
      this.bounds.x = 4;
      this.bounds.y = 24;
      this.bounds.width = 56;
      this.bounds.height = 8;
    }
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub

  }

  @Override
  public void die() {

  }

  @Override
  public void render(Graphics g) {
    BufferedImage asset;
    switch (type) {
      case LONG:
        asset = Assets.long_rock;
        break;
      case LONG_MOSSY:

        asset = Assets.long_rock_mossy;
        break;
      case EGG:
        asset = Assets.egg_rock;
        break;
      case EGG_MOSSY:
        asset = Assets.egg_rock_mossy;
        break;
      case DUO:
        asset = Assets.duo_rock;
        break;
      case DUO_MOSSY:
        asset = Assets.duo_rock_mossy;
        break;

      default:
        asset = Assets.egg_rock;
        break;
    }
    g.drawImage(asset, (int) (x - handler.getGameCamera().getxOffset()),
        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    // showCollisionBox(g);
  }

  public enum RockType {
    LONG, LONG_MOSSY, EGG, EGG_MOSSY, DUO, DUO_MOSSY
  }

}
