package framework;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author David Browne
 */
public class Assets {

    public static BufferedImage player_still;
    public static BufferedImage[] player_up, player_walk;
    public static BufferedImage grass, dirt, stone, sand, sandstone, darkstone;
    public static Font font90, font50;

    static int width = 32;
    static int height = 32;

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/images/tiles1.png"));
        grass = sheet.crop(1508, 773, 208, 208);
        dirt = sheet.crop(208, 1248, 208, 208);
        stone = sheet.crop(width * 5, 0, 208, 208);
        sand = sheet.crop(208, 1664, 208, 208);
        sandstone = sheet.crop(416, 1664, 208, 208);
        darkstone = sheet.crop(208, 435, 208, 208);

        SpriteSheet psheet = new SpriteSheet(ImageLoader.loadImage("/images/chars.png"));
        player_still = psheet.crop(0, 860, 428, 855);
        player_walk = new BufferedImage[4];
        player_walk[0] = psheet.crop(0, 0, 396, 862);
        player_walk[1] = psheet.crop(394, 0, 431, 854);
        player_walk[2] = psheet.crop(432, 851, 543, 851);
        player_walk[3] = psheet.crop(0, 860, 428, 855);
        player_up = new BufferedImage[2];
        player_up[0] = psheet.crop(1419, 0, 618, 827);
        player_up[1] = psheet.crop(1419, 0, 618, 827);

    }

    public static BufferedImage flip(BufferedImage Image) {
        try {
            BufferedImage image = Image;
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage flipped = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            for(int y=0;y<height;y++){
                for(int x=0;x<width;x++){
                    flipped.setRGB((width-1)-x,y,image.getRGB(x,y));
                }
                
            }return flipped;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
}
