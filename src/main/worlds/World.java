package main.worlds;

import entities.EntityManager;
import entities.creatures.Player;
import entities.statics.Rock;
import entities.statics.Rock.RockType;
import entities.statics.Tree;
import entities.statics.Tree.TreeType;
import main.Handler;
import main.tiles.Tile;
import main.utils.Utils;

import java.awt.Graphics;

public class World {

  private Handler handler;

  // unit = number of tiles
  private int width, height;
  private int spawnX, spawnY;
  private int[][] tiles;

  // Entities
  private EntityManager entityManager;

  public World(Handler handler, String path) {
    this.handler = handler;
    entityManager = new EntityManager(handler, new Player(handler, 100, 100));

    // entityManager.addEntity(new Rock(handler, 160, 260));
    entityManager.addEntity(new Rock(handler, 160, 450, RockType.LONG));
    entityManager.addEntity(new Rock(handler, 250, 450, RockType.DUO));
    entityManager.addEntity(new Rock(handler, 340, 450, RockType.EGG));
    entityManager.addEntity(new Rock(handler, 160, 500, RockType.LONG_MOSSY));
    entityManager.addEntity(new Rock(handler, 250, 500, RockType.DUO_MOSSY));
    entityManager.addEntity(new Rock(handler, 340, 500, RockType.EGG_MOSSY));

    entityManager.addEntity(new Tree(handler, 180, 75));
    entityManager.addEntity(new Tree(handler, 264, 75, TreeType.WINTER_01));
    entityManager.addEntity(new Tree(handler, 348, 75, TreeType.WINTER_02));

    loadWorld(path);

    entityManager.getPlayer().setX(spawnX);
    entityManager.getPlayer().setY(spawnY);
  }

  public void tick() {
    entityManager.tick();
  }

  public void render(Graphics g) {

    int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
    int xEnd = (int) Math.min(width,
        (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
    int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
    int yEnd = (int) Math.min(height,
        (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);;

    for (int y = yStart; y < yEnd; y++) {
      for (int x = xStart; x < xEnd; x++) {
        getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
            (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
      }
    }

    entityManager.render(g);
  }

  public Tile getTile(int x, int y) {
    // if the player is glitched outside the map, it'll stand on a ghost grass tile so the game
    // doesn't crash
    if (x < 0 || y < 0 || x >= width || y >= height)
      return Tile.grassTile;

    Tile t = Tile.tiles[tiles[x][y]];
    if (t == null)
      return Tile.grassTile;
    return t;
  }

  private void loadWorld(String path) {
    String file = Utils.loadFileAsString(path);
    String[] tokens = file.split("\\s+");
    width = Utils.parseInt(tokens[0]);
    height = Utils.parseInt(tokens[1]);
    spawnX = Utils.parseInt(tokens[2]);
    spawnY = Utils.parseInt(tokens[3]);

    tiles = new int[width][height];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
      }
    }
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

}
