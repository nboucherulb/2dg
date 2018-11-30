package gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Assets {
	
	public static BufferedImage flowered_grass;
	public static BufferedImage textured_grass;
	public static BufferedImage draav_female;
	public static BufferedImage draav_male;
	
	public static BufferedImage trees_b1;
	public static BufferedImage trees_b2;
	public static BufferedImage trees_b3;
	public static BufferedImage trees_b4;
	public static BufferedImage trees_b1_swap;
	public static BufferedImage trees_b2_swap;
	public static BufferedImage trees_b3_swap;
	public static BufferedImage trees_b4_swap;
	
	public static BufferedImage rock_tile_aa_1;
	public static BufferedImage rock_tile_aa_2;
	public static BufferedImage rock_tile_ab_1;
	public static BufferedImage rock_tile_ab_2;
	public static BufferedImage rock_tile_ac_1;
	public static BufferedImage rock_tile_ac_2;
	
	public static List<Image> trees = new ArrayList<>();
	
	private static final int width = 32;
	private static final int height = 32;
	
	private static final int large_width = 64;
	private static final int large_height = 64;
	
	public static void init(){
		// Icon
		initIconTree();
		
		// Background
		initGrassTiles();
		initRockTiles();
		
		// Obstacles
		initTreesB();	
		
		// Characters
		initCharacters();
	}
	
	private static void initIconTree(){
		trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_16.png")).getImage());
		trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_32.png")).getImage());
		trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_64.png")).getImage());
		trees.add(new ImageIcon(ImageLoader.loadImage("/textures/trees/tree_128.png")).getImage());
	}
	
	private static void initGrassTiles(){
		SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/textures/grass_tiles.png"));
		
		flowered_grass = grassSheet.crop(0, 0, width, height);
		textured_grass = grassSheet.crop(0, height*2, width, height);
	}
	
	private static void initRockTiles(){
		SpriteSheet rockSheet = new SpriteSheet(ImageLoader.loadImage("/textures/obstacle_tiles/rocks_a_spritesheet_32x32.png"));
		
		rock_tile_aa_1 = rockSheet.crop(0, 0, width, height);
		rock_tile_aa_2 = rockSheet.crop(0, height, width, height);
		rock_tile_ab_1 = rockSheet.crop(width, 0, width, height);
		rock_tile_ab_2 = rockSheet.crop(width, height, width, height);
		rock_tile_ac_1 = rockSheet.crop(width*2, 0, width, height);
		rock_tile_ac_2 = rockSheet.crop(width*2, height, width, height);
	}
	
	private static void initCharacters(){
		SpriteSheet draavFsheet = new SpriteSheet(ImageLoader.loadImage("/characters/Draav_female_32.png"));
		SpriteSheet draavMsheet = new SpriteSheet(ImageLoader.loadImage("/characters/Draav_male_32.png"));
		
		draav_female = draavFsheet.crop(0, 0, width, height);
		draav_male = draavMsheet.crop(0, 0, width, height);
	}
	
	private static void initTreesB(){
		SpriteSheet treesBSheet = new SpriteSheet(ImageLoader.loadImage("/textures/trees/trees_b_32x64.png"));
		
		trees_b1 = treesBSheet.crop(0, 0, width, large_height);
		trees_b2 = treesBSheet.crop(width, 0, width, large_height);
		trees_b3 = treesBSheet.crop(width*2, 0, width, large_height);
		trees_b4 = treesBSheet.crop(width*3, 0, width, large_height);		
		trees_b4_swap = treesBSheet.crop(0, large_height, width, large_height);
		trees_b3_swap = treesBSheet.crop(width, large_height, width, large_height);
		trees_b2_swap = treesBSheet.crop(width*2, large_height, width, large_height);
		trees_b1_swap = treesBSheet.crop(width*3, large_height, width, large_height);
	}
	
}
