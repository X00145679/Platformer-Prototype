package window;

import framework.GameObject;
import game.Handler;

/**
 *
 * @author david
 */


public class Camera {
    
    private float x,y;
    private Handler handler;
    public Camera(float x, float y,Handler handler) {
        this.x = x;
        this.y = y;
        this.handler=handler;
    }
    
    public void tick(GameObject player){
        x = -player.getX() + game.Game.WIDTH/2;
        
    }
    
    
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
        public void centerOnEntity(GameObject e){
        System.out.println(x);
        x = e.getX() - handler.game.getWidth() /2  + e.getWidth()/2;
        System.out.println(x);
}
    
}

     
    

 