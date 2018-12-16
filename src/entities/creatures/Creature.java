package entities.creatures;

import entities.Entity;
import main.Handler;
import main.tiles.Tile;

public abstract class Creature extends Entity {

  public static final float DEFAULT_SPEED = 3.0f;
  public static final float DEFAULT_SPRINT = 1.5f;

  public static final int DEFAULT_CREATURE_WIDTH = 64;
  public static final int DEFAULT_CREATURE_HEIGHT = 64;

  public static final int SMALL_CREATURE_WIDTH = 32;
  public static final int SMALL_CREATURE_HEIGHT = 32;

  public static final int TINY_CREATURE_WIDTH = 16;
  public static final int TINY_CREATURE_HEIGHT = 16;

  protected float speed;
  protected float sprintModifier;

  protected float xMove, yMove;

  public Creature(Handler handler, float x, float y, int width, int height) {
    super(handler, x, y, width, height);
    speed = DEFAULT_SPEED;
    sprintModifier = DEFAULT_SPRINT;
    this.xMove = 0;
    this.yMove = 0;
  }

  public void move() {
    if (!checkEntityCollisions(xMove, 0f))
      moveX();
    if (!checkEntityCollisions(0f, yMove))
      moveY();
  }

  public void moveX() {
    if (xMove > 0) { // moving right
      int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
      if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
          && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
        x += xMove;
      } else {
        // the -1 here allows a 1px gap to avoid glueing to the obstacle
        x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
      }

    } else if (xMove < 0) { // moving left
      int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
      if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
          && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
        x += xMove;
      } else {
        // the -1 here allows a 1px gap to avoid glueing to the obstacle
        x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
      }
    }
  }

  public void moveY() {
    if (yMove < 0) { // moving up
      int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
      if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty)
          && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
        y += yMove;
      } else {
        // the -1 here allows a 1px gap to avoid glueing to the obstacle
        y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
      }
    } else if (yMove > 0) { // moving down
      int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
      if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty)
          && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
        y += yMove;
      } else {
        // the -1 here allows a 1px gap to avoid glueing to the obstacle
        y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
      }
    }
  }

  protected boolean collisionWithTile(int x, int y) {
    return handler.getWorld().getTile(x, y).isSolid();
  }

  // GETTERS AND SETTERS

  public float getxMove() {
    return xMove;
  }

  public void setxMove(float xMove) {
    this.xMove = xMove;
  }

  public float getyMove() {
    return yMove;
  }

  public void setyMove(float yMove) {
    this.yMove = yMove;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

}
