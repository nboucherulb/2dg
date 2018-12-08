package entities;

import main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

  protected Handler handler;
  protected float x, y;
  protected int width, height;
  protected Rectangle bounds;

  public Entity(Handler handler, float x, float y, int width, int height) {
    this.handler = handler;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    // default bounds
    bounds = new Rectangle(0, 0, width, height);
  }


  public abstract void tick();


  public abstract void render(Graphics g);

  public void showCollisionBox(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
        (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
  }


  public float getX() {
    return x;
  }



  public void setX(float x) {
    this.x = x;
  }



  public float getY() {
    return y;
  }



  public void setY(float y) {
    this.y = y;
  }



  public int getWidth() {
    return width;
  }



  public void setWidth(int width) {
    this.width = width;
  }



  public int getHeight() {
    return height;
  }



  public void setHeight(int height) {
    this.height = height;
  }



}
