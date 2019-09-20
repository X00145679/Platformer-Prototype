package game;

import framework.GameObject;
import framework.ObjectId;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import objects.Block;
import objects.Flag;
import objects.Player;
import objects.Soldier;
import window.BufferedImageLoader;
import window.Camera;

/**
 *
 * @author david
 */
public class Handler {
    
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    public BufferedImage level1,level2,level3,level4,level5 = null;
    
    private GameObject tempObject;
    private Camera cam;
    public Game game;
    
    public Handler(Camera cam,Game game){
        this.cam=cam;
        this.game=game;
        BufferedImageLoader loader = new BufferedImageLoader();
        level1 = loader.loadImage("/level1.png");
        level2 = loader.loadImage("/level2.png");
        level3 = loader.loadImage("/level3.png");
        level4 = loader.loadImage("/level4.png");
        level5 = loader.loadImage("/level5.png");
        
    }
    
    public void tick(){
        for(int i =0;i<object.size();i++){
            tempObject = object.get(i);
            tempObject.tick(object);
            
        }
    }
    
    public int getGameHeight(){
        return Game.HEIGHT;
    }
    
    public int getGameWidth(){
        return Game.HEIGHT;
    }
    
    public Camera getCamera(){
        return game.getCam();
    }
        
    public Player getPlayer(){
        for(int i =0;i<object.size();i++){
            tempObject = object.get(i);
            
            if(tempObject.getId()== ObjectId.Player){
                return (Player) object.get(i);
    }
            
            
    }return null;
    }
    
    public void render(Graphics g){
        int xStart = (int) (this.getPlayer().getX()-(this.game.getWidth()/2)-32);
        int xEnd =(int) (this.getPlayer().getX()+(this.game.getWidth()/2));
        for(int i =0;i<object.size();i++){
            tempObject = object.get(i);
             if(tempObject.getX()>xStart&&tempObject.getX()<xEnd)
               tempObject.render(g);
            
        
       
            //
            
        }
    }
    
    public void clearLevel(){
        object.clear();
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    
    public void switchLevel(){
       clearLevel();
       
       
       switch(Game.level)
       {
           case 1:
               LoadImageLevel(level2);
               Game.level++;
               
               break;
            case 2:
               LoadImageLevel(level3);
               Game.level++;
               break;
            case 3:
               LoadImageLevel(level4);
               Game.level++;
               break;
            case 4:
               LoadImageLevel(level5);
               Game.level++;
               break;
            default:
                LoadImageLevel(level1);
                Game.level=1;
                break;
       }
       
    }
    
    public void reloadLevel(){
       clearLevel();
       cam.setX(0);
       
       switch(Game.level)
       {
           case 1:
               
               LoadImageLevel(level1);
               break;
               
            case 2:
               
               LoadImageLevel(level2);
               break;
            
            case 3:
               
               LoadImageLevel(level3);
               break;
            
            case 4:
               
               LoadImageLevel(level4);
               break;
            case 5:
               
               LoadImageLevel(level5);
               break;
            
            default: 
                LoadImageLevel(level1);
                break;
       }
       
    }
    
        public void LoadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println(" width: "+w+" height: "+h);
        
        for(int xx = 0; xx<h;xx++){
            for(int yy=0;yy<w;yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                
                if(red == 0 && green == 0 & blue == 0) addObject(new Block(xx*32,yy*32,ObjectId.sandstone));
                if(red == 10 && green == 0 & blue == 0) addObject(new Block(xx*32,yy*32,ObjectId.sand));
                if(red == 0 && green == 10 & blue == 0) addObject(new Block(xx*32,yy*32,ObjectId.dirt));
                if(red == 0 && green == 20 & blue == 0) addObject(new Block(xx*32,yy*32,ObjectId.grass));
                if(red == 0 && green == 30 & blue == 0) addObject(new Block(xx*32,yy*32,ObjectId.stone));
                if(red == 0 && green == 40 & blue == 0) addObject(new Block(xx*32,yy*32,ObjectId.darkstone));
                if(red == 0 && green == 0 & blue == 255) addObject(new Player(xx*32,yy*32,this,cam,ObjectId.Player));
                if(red == 255 && green == 0 & blue == 0) addObject(new Flag(xx*32,yy*32,ObjectId.flag));
                if(red == 2 && green == 0 & blue == 0) addObject(new Soldier(xx*32,yy*32,this,ObjectId.soldier));
            }
                
        }
    }
    
    
}
