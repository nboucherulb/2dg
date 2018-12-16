package entities.spells;

import entities.Entity;
import main.Handler;

import java.awt.Graphics;

public abstract class Spell extends Entity {

  protected int damage;

  public Spell(Handler handler, float x, float y, int width, int height) {
    super(handler, x, y, width, height);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void tick() {
    // TODO Auto-generated method stub

  }

  @Override
  public void render(Graphics g) {
    // TODO Auto-generated method stub

  }

  @Override
  public void die() {
    // TODO Auto-generated method stub

  }

}
