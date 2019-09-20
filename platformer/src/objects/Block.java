
package objects;

import framework.Assets;
import framework.GameObject;
import framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author david
 */
public class Block extends GameObject {

    public Block(float x, float y, ObjectId id) {
        super(x, y, id);
    } 

    @Override
    public void tick(LinkedList<GameObject> object) {
        
    }
    
    

    @Override
    public void render(Graphics g) {
     
            if(getId() == ObjectId.grass){
                g.drawImage(Assets.grass,(int) x, (int)y,32,32, null);
            }
            
            
            if(getId() == ObjectId.darkstone){
                g.drawImage(Assets.darkstone,(int) x, (int)y,32,32, null);
            }
            
            if(getId() == ObjectId.dirt){
                g.drawImage(Assets.dirt,(int) x, (int)y,32,32, null);
            }
            if(getId() == ObjectId.sand){
                g.drawImage(Assets.sand,(int) x, (int)y,32,32, null);
            }
            if(getId() == ObjectId.sandstone){
                g.drawImage(Assets.sandstone,(int) x, (int)y,32,32, null);
            }
            if(getId() == ObjectId.stone){
                g.drawImage(Assets.stone,(int) x, (int)y,32,32, null);
            }
            
        //g.setColor(Color.yellow);
        //g.drawRect((int) x,(int) y, 32, 32);
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y,32,32);
    }

    
}