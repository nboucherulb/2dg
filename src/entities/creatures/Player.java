package entities.creatures;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature {

  // Animations
  // private Animation animWalkDown, animIdleFront, animIdleBack, animIdleLeft, animIdleRight;
  private final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
  private int lastDirection;
  private boolean isSprinting, isChannelling;
  private Animation animIdleLeft, animIdleRight, animWalkLeft, animWalkRight, animRunLeft,
      animRunRight, animSpellCastLeft, animSpellCastRight;

  // Attack timer
  private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown,
      attackDuration = 100;

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

    // Attacks
    checkAttacks();
  }

  private void checkAttacks() {
    if (isChannelling) {
      meleeAttack();
    } else {
      return;
    }
  }

  public void meleeAttack() {
    attackTimer += System.currentTimeMillis() - lastAttackTimer;
    lastAttackTimer = System.currentTimeMillis();
    if (attackTimer > attackDuration) {
      isChannelling = false;
    }
    if (attackTimer < attackCooldown) {
      return;
    }

    Rectangle cb = getCollisionBounds(0, 0);
    // ar = attack rectangle
    Rectangle ar = new Rectangle();
    // if the player is at arSize or less pixels from an entity, it can hit it.
    int arSize = 20;
    ar.width = arSize;
    ar.height = arSize;

    if (lastDirection == UP) {
      ar.x = cb.x + cb.width / 2 - arSize / 2;
      ar.y = cb.y - arSize;
    } else if (lastDirection == DOWN) {
      ar.x = cb.x + cb.width / 2 - arSize / 2;
      ar.y = cb.y + cb.height;
    } else if (lastDirection == LEFT) {
      ar.x = cb.x - arSize;
      ar.y = cb.y + cb.height / 2 - arSize / 2;
    } else if (lastDirection == RIGHT) {
      ar.x = cb.x + cb.width;
      ar.y = cb.y + cb.height / 2 - arSize / 2;
    }

    attackTimer = 0;

    for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
      if (e.equals(this))
        continue;
      if (e.getCollisionBounds(0, 0).intersects(ar)) {
        e.hurt(1);
        return;
      }
    }
  }

  @Override
  public void die() {
    System.out.println("You lose");
  }

  private void getInput() {
    this.xMove = 0;
    this.yMove = 0;
    isSprinting = false;
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
