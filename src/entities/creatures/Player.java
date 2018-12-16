package entities.creatures;

import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature {

  // private Animation animWalkDown, animIdleFront, animIdleBack, animIdleLeft, animIdleRight;
  private final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
  private int lastDirection;
  private boolean isSprinting, isChannelling;
  private Animation animIdleLeft, animIdleRight, animWalkLeft, animWalkRight, animRunLeft,
      animRunRight, animSpellCastLeft, animSpellCastRight;

  public Player(Handler handler, float x, float y) {
    // default width and height
    super(handler, x, y, Creature.SMALL_CREATURE_WIDTH, Creature.SMALL_CREATURE_HEIGHT);
    bounds.x = 0;
    bounds.y = 21;
    bounds.width = 31;
    bounds.height = 10;

    // Animations
    lastDirection = DOWN;
    isSprinting = false;
    isChannelling = false;

    animIdleLeft = new Animation(50, Assets._Mage16IdleL);
    animIdleRight = new Animation(50, Assets._Mage16IdleR);
    animWalkLeft = new Animation(100, Assets._Mage16WalkL);
    animWalkRight = new Animation(100, Assets._Mage16WalkR);
    animRunLeft = new Animation(50, Assets._Mage16WalkL);
    animRunRight = new Animation(50, Assets._Mage16WalkR);
    animSpellCastLeft = new Animation(50, Assets._Mage16SpellCastL);
    animSpellCastRight = new Animation(50, Assets._Mage16SpellCastR);
  }

  @Override
  public void tick() {
    // Animations
    animIdleLeft.tick();
    animIdleRight.tick();
    animWalkLeft.tick();
    animWalkRight.tick();
    animRunLeft.tick();
    animRunRight.tick();
    animSpellCastLeft.tick();
    animSpellCastRight.tick();

    // Movement
    getInput();
    move();
    handler.getGameCamera().centerOnEntity(this);
  }

  private void getInput() {
    this.xMove = 0;
    this.yMove = 0;
    isSprinting = false;
    isChannelling = false;
    if (handler.getKeyManager().up) {
      yMove -= speed;
    }
    if (handler.getKeyManager().down) {
      yMove += speed;
    }
    if (handler.getKeyManager().left) {
      xMove -= speed;
      lastDirection = LEFT;
    }
    if (handler.getKeyManager().right) {
      xMove += speed;
      lastDirection = RIGHT;
    }
    if (handler.getKeyManager().shift) {
      xMove *= sprintModifier;
      yMove *= sprintModifier;
      isSprinting = true;
    }
    if (handler.getKeyManager().spellCast) {
      xMove = 0;
      yMove = 0;
      isChannelling = true;
    }

  }

  @Override
  public void render(Graphics g) {
    g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    // showCollisionBox(g);
  }

  private BufferedImage getCurrentAnimationFrame() {
    if (xMove < 0) { // Move left
      if (isSprinting)
        return animRunLeft.getCurrentFrame();
      return animWalkLeft.getCurrentFrame();
    } else if (xMove > 0) { // Move right
      if (isSprinting)
        return animRunRight.getCurrentFrame();
      return animWalkRight.getCurrentFrame();
    } else if (yMove < 0 || yMove > 0) { // Move up or down
      if (lastDirection == RIGHT) {
        if (isSprinting)
          return animRunRight.getCurrentFrame();
        return animWalkRight.getCurrentFrame();
      } else {
        if (isSprinting)
          return animRunLeft.getCurrentFrame();
        return animWalkLeft.getCurrentFrame();
      }
    } else { // Stay still
      switch (lastDirection) {
        case LEFT:
          if (isChannelling)
            return animSpellCastLeft.getCurrentFrame();
          return animIdleLeft.getCurrentFrame();
        case RIGHT:
          if (isChannelling)
            return animSpellCastRight.getCurrentFrame();
          return animIdleRight.getCurrentFrame();
        default:
          return animIdleRight.getCurrentFrame();
      }

    }
  }

}
