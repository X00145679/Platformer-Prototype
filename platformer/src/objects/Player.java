package objects;

import framework.Animation;
import framework.Assets;
import framework.GameObject;
import framework.ObjectId;
import game.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import window.Camera;

/**
 *
 * @author david
 */
public class Player extends GameObject {

    private float width = 32, height = 64;
    private Camera cam; 
    
    
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;
    private Handler handler;
    private Animation animJump, animWalk;
    private boolean dead=false;

    public Player(float x, float y, Handler handler,Camera cam, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        this.cam=cam;
        animJump = new Animation(200, Assets.player_up);
        animWalk = new Animation(200, Assets.player_walk);
        
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

 

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }
        animJump.tick();
        animWalk.tick();
        Collision(object);
        if(this.getY()>= game.Game.HEIGHT)
            this.health=0;
        
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.blue);
        //g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.drawImage(getCurrentAnimFrame(), (int) x, (int) y,(int)width,(int)height, null);

        Graphics2D g2d = (Graphics2D) g;
        /*g2d.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsTop());*/
    }

    public BufferedImage getCurrentAnimFrame() {
        BufferedImage image;
        if (jumping) {
            image = animJump.getCurrentFrame();
        }
        else if (walking) {
            image = animWalk.getCurrentFrame();
        }
        
        else {
            image = Assets.player_still;
        }
        if(facing==-1){
            image = Assets.flip(image);
        }
        return image;
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
    
    

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
          
            
            if (tempObject.getId() == ObjectId.flag) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                }
                else if(getBoundsLeft().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                }
                else if(getBoundsRight().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                }
                else if(getBoundsTop().intersects(tempObject.getBounds())) {
                    handler.switchLevel();
                }
                

            }

            //top collision       
            if (tempObject.getId() != ObjectId.Player && tempObject.getId() != ObjectId.bullet) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + height / 2;
                    velY = 0;
                    System.out.println("top");
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

}
