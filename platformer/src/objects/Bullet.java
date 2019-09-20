package objects;

import framework.GameObject;
import framework.ObjectId;
import game.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author david
 */
public class Bullet extends GameObject{

    private Handler handler;
    
    public Bullet(float x, float y,Handler handler, ObjectId id, int velX) {
        super(x, y, id);
        this.velX=velX;
        this.handler = handler;
    }
    
    

    @Override
    public void tick(LinkedList<GameObject> object) {
       
        
       x+=velX;
       Collision(object);
       
       
    }
    
    private void Collision(LinkedList<GameObject> object){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId()!= ObjectId.Player && tempObject.getId()!= ObjectId.bullet && tempObject.getId()!= ObjectId.soldier){
                if(this.getBounds().intersects(tempObject.getBounds())){
                    
                    handler.removeObject(this);
                    
                    }
                
        }
        }
        
            
    }
    

    @Override
    public void render(Graphics g) {
         g.setColor(Color.red);
         g.fillRect((int)x, (int)y, 16, 16);
    }
    
    public Rectangle getBounds(){
         return new Rectangle((int)x,(int)y,16,16);
    }

    
}
