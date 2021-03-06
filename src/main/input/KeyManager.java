package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

  private boolean[] keys;
  public boolean up, down, left, right;
  public boolean shift;
  public boolean spellCast;

  public KeyManager() {
    keys = new boolean[256];
  }

  public void tick() {
    up = keys[KeyEvent.VK_Z] || keys[KeyEvent.VK_UP];
    down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
    left = keys[KeyEvent.VK_Q] || keys[KeyEvent.VK_LEFT];
    right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];

    shift = keys[KeyEvent.VK_SHIFT];
    spellCast = keys[KeyEvent.VK_E];
  }

  @Override
  public void keyPressed(KeyEvent e) {
    keys[e.getKeyCode()] = true;
    System.out.println("Key " + e.getKeyCode() + " pressed!");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keys[e.getKeyCode()] = false;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

}
