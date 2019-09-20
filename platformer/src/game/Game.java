
package game;

import framework.Assets;
import framework.KeyInput;
import framework.ObjectId;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import window.BufferedImageLoader;
import window.Camera;
import window.Window;

/**
 *
 * @author david
 */
public class Game extends Canvas implements Runnable {
    
    private boolean running = false;
    private Thread thread;
    
    public static int WIDTH,HEIGHT;
    
    public static int level=1;
    
    public BufferedImage level1=null,level2 = null,clouds = null;
    
    //Objects
    Handler handler;
    Camera cam;
    
    
    
    
    private void init(){
        Assets.init();
        WIDTH = getWidth();
        HEIGHT = getHeight();
        BufferedImageLoader loader = new BufferedImageLoader();
        level1 = loader.loadImage("/images/level2.png");
        
        clouds = loader.loadImage("/images/cloud.png");
        cam = new Camera(0,0,handler);
        handler = new Handler(cam,this);
        
        
        handler.LoadImageLevel(level1);
        

        this.addKeyListener(new KeyInput(handler));
    }
    
    public synchronized void start(){
        if(running)
               return;
           running = true;
           thread = new Thread(this);
           thread.start();    
    }
    
    public void run(){
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks=60.0;
        double ns = 1000000000/amountOfTicks;
        double delta =0;
        long timer=System.currentTimeMillis();
        int updates = 0;
        int frames=0;
        while(running){
            long now = System.nanoTime();
            delta+= (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;  
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: "+ frames + " TICKS: " +updates);
                frames=0;
                updates=0;
            }
        }
        
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Camera getCam() {
        return cam;
    }

    public BufferedImage getLevel1() {
        return level1;
    }

    public void setLevel1(BufferedImage level1) {
        this.level1 = level1;
    }
    
    public void tick(){
        handler.tick();
        for(int i=0;i<handler.object.size();i++){
            if(handler.object.get(i).getId()==ObjectId.Player){
                cam.tick(handler.object.get(i));
                if(handler.object.get(i).getHealth()<=0){
                    handler.reloadLevel();
                }
            }
            
        }
        
        
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            //maybe make this 2 to improve performance? 
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        
        
        //draw here
        
        g.setColor(new Color(24,47,117));
        g.fillRect(0,0,getWidth(),getHeight());
        
        g2d.translate(cam.getX(), cam.getY()); //start of cam
        
        for(int xx=0;xx <clouds.getWidth()*5;xx+=clouds.getWidth()*3)
            g.drawImage(clouds, xx, 0, null);
        handler.render(g);
        
        for(int i=0;i<handler.object.size();i++){
            if(handler.object.get(i).getId()==ObjectId.Player){
                g.drawString("Health: "+handler.object.get(i).getHealth(), (int)(10-cam.getX()), (int)(10-cam.getY()));
            }
        }
        g2d.translate(-cam.getX(), -cam.getY()); // end of cam
        
        ////////////////////
        g.dispose();
        bs.show();
         
    
    }
    
    
    
    public static void main(String[] args) {
        new Window(800,600,"TEST", new Game());
    }
    
}
