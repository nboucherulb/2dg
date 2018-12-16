package main.ui;

import main.Handler;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

  private Handler handler;
  private ArrayList<UIObject> uiObjects;

  public UIManager(Handler handler) {
    this.handler = handler;
    uiObjects = new ArrayList<UIObject>();
  }

  public void tick() {
    for (UIObject o : uiObjects)
      o.tick();
  }

  public void render(Graphics g) {
    for (UIObject o : uiObjects)
      o.render(g);
  }

  public void onMouseMove(MouseEvent e) {
    for (UIObject o : uiObjects)
      o.onMouseMove(e);
  }

  public void onMouseRelease(MouseEvent e) {
    for (UIObject o : uiObjects)
      o.onMouseRelease(e);
  }

  public void addUIObject(UIObject o) {
    uiObjects.add(o);
  }

  public void removeUIObject(UIObject o) {
    uiObjects.remove(o);
  }

  // getters and setters

  public Handler getHandler() {
    return handler;
  }

  public void setHandler(Handler handler) {
    this.handler = handler;
  }

}
