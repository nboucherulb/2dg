package state;

import gfx.Assets;
import main.Handler;
import main.ui.ClickListener;
import main.ui.UIImageButton;
import main.ui.UIManager;

import java.awt.Color;
import java.awt.Graphics;

public class MainMenuState extends State {

  private UIManager uiManager;

  public MainMenuState(Handler handler) {
    super(handler);
    uiManager = new UIManager(handler);
    handler.getMouseManager().setUIManager(uiManager);

    uiManager.addUIObject(new UIImageButton(((uiManager.getHandler().getWidth() - 576) / 2), 130,
        576, 192, Assets.btn_start, new ClickListener() {
          @Override
          public void onClick() {
            handler.getMouseManager().setUIManager(null);
            State.setState(handler.getGame().gameState);
          }
        }));
  }

  @Override
  public void tick() {
    uiManager.tick();
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, 1280, 720);

    uiManager.render(g);
  }

}
