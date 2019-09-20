package framework;


import game.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import objects.Bullet;

/**
 *
 * @author david
 */
public class KeyInput extends KeyAdapter {
   Handler handler; 
   
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
   @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i =0; i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId()== ObjectId.Player){
                if(key == KeyEvent.VK_D) {
                    tempObject.moveRight();
                    
                }
            
                if(key == KeyEvent.VK_A){ 
                tempObject.moveLeft();
                
                
            }
                if(key == KeyEvent.VK_SPACE&& !tempObject.isJumping()){
                    tempObject.jump();
            }
                if(key == KeyEvent.VK_W){
                    handler.addObject(new Bullet(tempObject.getX(),tempObject.getY()+20,handler,ObjectId.bullet,tempObject.getFacing()*10));
                }
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }
    
   @Override
    public void keyReleased(KeyEvent e){
        
        int key = e.getKeyCode();
        for(int i =0; i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId()== ObjectId.Player){
                if(key == KeyEvent.VK_D) {
                    tempObject.stopMoving();
                    
                }
                if(key == KeyEvent.VK_A){
                    tempObject.stopMoving();
                    
                }
                if(key == KeyEvent.VK_SPACE){
                    tempObject.stopJumping();
                    
                }
            }
        }
    }
    
}
