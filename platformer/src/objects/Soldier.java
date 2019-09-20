package objects;

import framework.Assets;
import framework.GameObject;
import framework.ObjectId;
import game.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author david
 */
public class Soldier extends GameObject {

    private Handler handler;
    private int height=64,width=32;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;
    public Soldier(float x, float y,Handler handler, ObjectId id) {
        super(x, y, id);
        this.handler=handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if(health<=0)
            handler.removeObject(this);
        
        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }
        Collision(object);
        ai();
    }

    @Override
    public void moveRight() {
        setVelX(3);
        setWalking(true);
        facing=1;
    }

    @Override
    public void moveLeft() {
        setVelX(-3);
        setWalking(true);
        facing=-1;
    }
    
    public void ai(){
        Random rand = new Random();
        int r = rand.nextInt(8);
        
        if(handler.getPlayer().getX()-this.getX()<=200){
            if(handler.getPlayer().getX()<this.x){
                moveLeft();
            }else{
                moveRight();
            }
            
            
            
        }else{
        switch(r){
            case 1:
                moveLeft();
                break;
            case 2:
                moveRight();
                break;
            case 3:
                moveLeft();
                break;
            case 4:
                moveRight();
                break;
            default:
                stopMoving();
                break;
            
        }
        }
        
    }
    
    
    

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player_still, (int)x, (int)y,(int)width,(int)height, null);
        g.setColor(Color.GREEN);
        g.drawString("HP: "+this.health, (int)x,(int) y-15);
    }
    
    
    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
           //Bullet Collsion
            if(tempObject.getId() == ObjectId.bullet){
            //top collision       
            
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    health-=40;
                    handler.removeObject(tempObject);
                    
                }

                //Right Collision
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    health-=40;
                    handler.removeObject(tempObject);
                }

                //Left Collision
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    health-=40;
                    handler.removeObject(tempObject);

                }

                //Bottom collision
                if (getBounds().intersects(tempObject.getBounds())) {
                 health-=40;
                 handler.removeObject(tempObject);
                
            }
            
             
        }

            else if (tempObject.getId() == ObjectId.Player) {
                
                if(getBoundsLeft().intersects(tempObject.getBounds())) {
                    handler.getPlayer().setHealth(handler.getPlayer().health-20);
                    this.moveRight();
                    
                    
                }
                else if(getBoundsRight().intersects(tempObject.getBounds())) {
                    handler.getPlayer().setHealth(handler.getPlayer().health-20);
                    this.moveLeft();
                    
                }
                
                

            }
                
                
                
            
                //object
            //top collision       
            else if (tempObject != this ) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + height / 2;
                    velY = 0;
                    
                }

                //Right Collision
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - width;

                }

                //Left Collision
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + width;

                }

                //Bottom collision
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    //y = handler.object.get(i).getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;

                } else {
                    falling = true;
                }
            }
        }

    }
    
  
    public Rectangle getBounds() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 4))), (int) ((int) y + (height / 2)), (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 4))), (int) y, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 15);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 15);
    }
    
}
