package gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Assets {

  public static BufferedImage flowered_grass;
  public static BufferedImage textured_grass;
  public static BufferedImage complex_textured_grass;
  public static BufferedImage draav_male;

  public static BufferedImage trees_b1;
  public static BufferedImage trees_b2;
  public static BufferedImage trees_b3;
  public static BufferedImage trees_b4;
  public static BufferedImage trees_b1_swap;
  public static BufferedImage trees_b2_swap;
  public static BufferedImage trees_b3_swap;
  public static BufferedImage trees_b4_swap;

  public static BufferedImage tree_summer_01;
  public static BufferedImage tree_summer_02;
  public static BufferedImage tree_winter_01;
  public static BufferedImage tree_winter_02;

  public static BufferedImage rock_tile_aa_1;
  public static BufferedImage rock_tile_aa_2;
  public static BufferedImage rock_tile_ab_1;
  public static BufferedImage rock_tile_ab_2;
  public static BufferedImage rock_tile_ac_1;
  public static BufferedImage rock_tile_ac_2;

  // #################### ANIMATIONS ###################################

  public static BufferedImage[] _Mage16WalkR;
  public static BufferedImage[] _Mage16WalkL;
  public static BufferedImage[] _Mage16IdleR;
  public static BufferedImage[] _Mage16IdleL;
  public static BufferedImage[] _Mage16SpellCastR;
  public static BufferedImage[] _Mage16SpellCastL;

  public static BufferedImage[] _DraFIdleFront;
  public static BufferedImage[] _DraFIdleBack;
  public static BufferedImage[] _DraFIdleSideL;
  public static BufferedImage[] _DraFIdleSideR;
  public static BufferedImage[] _DraFWalkDown;

  // ###################################################################

  public static List<Image> trees = new ArrayList<>();

  private static final int width = 32;
  private static final int height = 32;

  private static final int large_width = 64;
  private static final int large_height = 64;

  private static final int small_width = 16;
  private static final int small_height = 16;

  public static void init() {
    // Icon
    initIconTree();

    // Background
    initGrassTiles();
    initRockTiles();

    // Obstacles
    initTrees();
    // initTreesB();

    // Characters
    initCharacters();
  }

  public static void fillAnimTableWith(BufferedImage[] animationBuffer, int nbRows, int nbColumns,
      int width, int height, SpriteSheet sheet) {
    int i = 0;
    for (int y = 0; y < nbRows; y++) {
      for (int x = 0; x < nbColumns; x++) {
        if (i >= animationBuffer.length)
          return;
        animationBuffer[i] = sheet.crop(x * width, y * height, width, height);
        i++;
      }
    }
  }

  private static void initIconTree() {
    trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_16.png")).getImage());
    trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_32.png")).getImage());
    trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_64.png")).getImage());
    trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_128.png")).getImage());
  }

  private static void initGrassTiles() {
    SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/textures/grass_tiles.png"));
    SpriteSheet complexGrassSheet =
        new SpriteSheet(ImageLoader.loadImage("/textures/ground_tiles/grass32x32_01.png"));

    complex_textured_grass = complexGrassSheet.crop(0, 0, width, height);

    flowered_grass = grassSheet.crop(0, 0, width, height);
    textured_grass = grassSheet.crop(0, height * 2, width, height);
  }

  private static void initRockTiles() {
    SpriteSheet rockSheet = new SpriteSheet(
        ImageLoader.loadImage("/textures/obstacle_tiles/rocks_a_spritesheet_32x32.png"));

    rock_tile_aa_1 = rockSheet.crop(0, 0, width, height);
    rock_tile_aa_2 = rockSheet.crop(0, height, width, height);
    rock_tile_ab_1 = rockSheet.crop(width, 0, width, height);
    rock_tile_ab_2 = rockSheet.crop(width, height, width, height);
    rock_tile_ac_1 = rockSheet.crop(width * 2, 0, width, height);
    rock_tile_ac_2 = rockSheet.crop(width * 2, height, width, height);
  }

  private static void initCharacters() {
    initDraavF();
    initDraavM();

    initMage16();
  }

  private static void initMage16() {
    SpriteSheet mage16Sheet =
        new SpriteSheet(ImageLoader.loadImage("/characters/mage_16x16/mage_anims.png"));
    _Mage16IdleR = new BufferedImage[1];
    _Mage16IdleR[0] = mage16Sheet.crop(0, 0, small_width, small_height);
    _Mage16IdleL = new BufferedImage[1];
    _Mage16IdleL[0] = mage16Sheet.crop(small_width, small_height, small_width, small_height);

    _Mage16SpellCastR = new BufferedImage[1];
    _Mage16SpellCastR[0] =
        mage16Sheet.crop(small_width * 2, small_height * 2, small_width, small_height);
    _Mage16SpellCastL = new BufferedImage[1];
    _Mage16SpellCastL[0] =
        mage16Sheet.crop(small_width * 3, small_height * 2, small_width, small_height);

    _Mage16WalkR = new BufferedImage[4];
    _Mage16WalkR[0] = mage16Sheet.crop(small_width, 0, small_width, small_height);
    _Mage16WalkR[1] = mage16Sheet.crop(small_width * 2, 0, small_width, small_height);
    _Mage16WalkR[2] = mage16Sheet.crop(small_width * 3, 0, small_width, small_height);
    _Mage16WalkR[3] = mage16Sheet.crop(0, small_height, small_width, small_height);

    _Mage16WalkL = new BufferedImage[4];
    _Mage16WalkL[0] = mage16Sheet.crop(small_width * 2, small_height, small_width, small_height);
    _Mage16WalkL[1] = mage16Sheet.crop(small_width * 3, small_height, small_width, small_height);
    _Mage16WalkL[2] = mage16Sheet.crop(0, small_height * 2, small_width, small_height);
    _Mage16WalkL[3] = mage16Sheet.crop(small_width, small_height * 2, small_width, small_height);


  }

  private static void initDraavF() {
    SpriteSheet draavFsheet =
        new SpriteSheet(ImageLoader.loadImage("/characters/Draav_F/idle/DraF_idle_front.png"));
    _DraFIdleFront = new BufferedImage[8];
    fillAnimTableWith(_DraFIdleFront, 2, 4, large_width, large_height, draavFsheet);

    draavFsheet =
        new SpriteSheet(ImageLoader.loadImage("/characters/Draav_F/idle/DraF_idle_back.png"));
    _DraFIdleBack = new BufferedImage[8];
    fillAnimTableWith(_DraFIdleBack, 2, 4, large_width, large_height, draavFsheet);

    draavFsheet =
        new SpriteSheet(ImageLoader.loadImage("/characters/Draav_F/idle/DraF_idle_sideL.png"));
    _DraFIdleSideL = new BufferedImage[8];
    fillAnimTableWith(_DraFIdleSideL, 2, 4, large_width, large_height, draavFsheet);

    draavFsheet =
        new SpriteSheet(ImageLoader.loadImage("/characters/Draav_F/idle/DraF_idle_sideR.png"));
    _DraFIdleSideR = new BufferedImage[8];
    fillAnimTableWith(_DraFIdleSideR, 2, 4, large_width, large_height, draavFsheet);

    draavFsheet =
        new SpriteSheet(ImageLoader.loadImage("/characters/Draav_F/walk/DraF_walk_down.png"));
    _DraFWalkDown = new BufferedImage[12];
    fillAnimTableWith(_DraFWalkDown, 3, 4, large_width, large_height, draavFsheet);

  }

  private static void initDraavM() {
    SpriteSheet draavMsheet =
        new SpriteSheet(ImageLoader.loadImage("/characters/Draav_male_32.png"));
    draav_male = draavMsheet.crop(0, 0, width, height);

  }

  private static void initTrees() {
    SpriteSheet treeSheet =
        new SpriteSheet(ImageLoader.loadImage("/textures/trees/trees_variations.png"));

    tree_summer_01 = treeSheet.crop(0, 0, large_width, large_height * 2);
    tree_summer_02 = treeSheet.crop(large_width, 0, large_width, large_height * 2);
    tree_winter_01 = treeSheet.crop(large_width * 2, 0, large_width, large_height * 2);
    tree_winter_02 = treeSheet.crop(large_width * 3, 0, large_width, large_height * 2);
  }

  private static void initTreesB() {
    SpriteSheet treesBSheet =
        new SpriteSheet(ImageLoader.loadImage("/textures/trees/trees_b_32x64.png"));

    trees_b1 = treesBSheet.crop(0, 0, width, large_height);
    trees_b2 = treesBSheet.crop(width, 0, width, large_height);
    trees_b3 = treesBSheet.crop(width * 2, 0, width, large_height);
    trees_b4 = treesBSheet.crop(width * 3, 0, width, large_height);
    trees_b4_swap = treesBSheet.crop(0, large_height, width, large_height);
    trees_b3_swap = treesBSheet.crop(width, large_height, width, large_height);
    trees_b2_swap = treesBSheet.crop(width * 2, large_height, width, large_height);
    trees_b1_swap = treesBSheet.crop(width * 3, large_height, width, large_height);
  }

}
