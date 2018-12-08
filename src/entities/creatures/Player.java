package entities.creatures;

import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature {

  private Animation animWalkDown, animIdleFront, animIdleBack, animIdleLeft, animIdleRight;
  private final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
  private int idleDirection;

  public Player(Handler handler, float x, float y) {
    // default width and height
    super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    bounds.x = 11 * 2;
    bounds.y = 19 * 2;
    bounds.width = 16;
    bounds.height = 24;

    // Animations
    idleDirection = DOWN;
    animWalkDown = new Animation(50, Assets._DraFWalkDown);
    animIdleFront = new Animation(200, Assets._DraFIdleFront);
    animIdleBack = new Animation(200, Assets._DraFIdleBack);
    animIdleLeft = new Animation(200, Assets._DraFIdleSideL);
    animIdleRight = new Animation(200, Assets._DraFIdleSideR);
  }

  @Override
  public void tick() {
    // Animations
    animIdleFront.tick();
    animIdleBack.tick();
    animIdleLeft.tick();
    animIdleRight.tick();
    animWalkDown.tick();

    // Movement
    getInput();
    move();
    handler.getGameCamera().centerOnEntity(this);
  }

  private void getInput() {
    this.xMove = 0;
    this.yMove = 0;
    if (handler.getKeyManager().up) {
      yMove -= speed;
      idleDirection = UP;
    }
    if (handler.getKeyManager().down) {
      yMove += speed;
      idleDirection = DOWN;
    }
    if (handler.getKeyManager().left) {
      xMove -= speed;
      idleDirection = LEFT;
    }
    if (handler.getKeyManager().right) {
      xMove += speed;
      idleDirection = RIGHT;
    }
    if (handler.getKeyManager().shift) {
      xMove *= sprintModifier;
      yMove *= sprintModifier;
    }

  }

  @Override
  public void render(Graphics g) {
    g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
        (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    showCollisionBox(g);
  }

  private BufferedImage getCurrentAnimationFrame() {
    if (xMove < 0) { // Move left
      return animIdleLeft.getCurrentFrame();
    } else if (xMove > 0) { // Move right
      return animIdleRight.getCurrentFrame();
    } else if (yMove < 0) { // Move up
      return animIdleBack.getCurrentFrame();
    } else if (yMove > 0) { // Move down
      return animWalkDown.getCurrentFrame();
    } else { // Stay still
      switch (idleDirection) {
        case UP:
          return animIdleBack.getCurrentFrame();
        case DOWN:
          return animIdleFront.getCurrentFrame();
        case LEFT:
          return animIdleLeft.getCurrentFrame();
        case RIGHT:
          return animIdleRight.getCurrentFrame();

        default:
          return animIdleFront.getCurrentFrame();
      }

    }
  }

}
