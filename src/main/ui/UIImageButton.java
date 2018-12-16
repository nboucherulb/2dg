package main.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * This class takes an array of BufferedImages and a clickListener in the constructor. The image
 * array is the texture of the button, and the textures changes due to different actions. The
 * clicker is an object that listens to clicks and does certain actions when it detects one. The
 * clicker is created outside of this class and encapsulated in an interface, so that there's no
 * need of having one clicker class per button type.
 *
 */
public class UIImageButton extends UIObject {

  private BufferedImage[] images;
  private ClickListener clicker;

  public UIImageButton(float x, float y, int width, int height, BufferedImage[] images,
      ClickListener clicker) {
    super(x, y, width, height);
    this.images = images;
    this.clicker = clicker;
  }

  @Override
  public void tick() {}

  @Override
  public void render(Graphics g) {
    if (hovering)
      g.drawImage(images[1], (int) x, (int) y, width, height, null);
    else
      g.drawImage(images[0], (int) x, (int) y, width, height, null);
  }

  @Override
  public void onClick() {
    clicker.onClick();
  }

}
