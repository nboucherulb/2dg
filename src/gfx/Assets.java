package gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage flowered_grass;
	public static BufferedImage textured_grass;
	public static BufferedImage draav_female;
	public static BufferedImage draav_male;
	
	private static final int width = 32;
	private static final int height = 32;
	
	public static void init(){
		
		SpriteSheet draavFsheet = new SpriteSheet(ImageLoader.loadImage("/characters/Draav_female_32.png"));
		SpriteSheet draavMsheet = new SpriteSheet(ImageLoader.loadImage("/characters/Draav_male_32.png"));
		SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/textures/grass_tiles.png"));
		
		draav_female = draavFsheet.crop(0, 0, width, height);
		draav_male = draavMsheet.crop(0, 0, width, height);
		flowered_grass = grassSheet.crop(0, 0, width, height);
		textured_grass = grassSheet.crop(0, height*2, width, height);
	}
	
}
