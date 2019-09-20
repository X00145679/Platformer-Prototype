
package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author david
 */
public abstract class GameObject {
    
    protected float x,y;
    protected ObjectId id;
    protected float velX=0,velY=0;
    protected boolean falling=true;
    protected boolean jumping=false;
    protected boolean walking=false;
    public int health=100;
    
    protected int facing = 1,height,width;
    //1 is right -1 is left
    
    public GameObject(float x, float y,ObjectId id){
        this.x=x;
        this.y=y;
        this.id=id; 
        
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g); 

    public boolean isFalling() {
        return falling;
    }

    public boolean isWalking() {
        return walking;
    }

    public void setWalking(boolean walking) {
        this.walking = walking;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
    public float getX() {
         return x;
    }

    
    public float getY() {
        return y;
    }

    
    public void setX(float x) {
        x=this.x;
    }

    
    public void setY(float y) {
        y=this.y;
    }

   
    public float getVelX() {
        return velX;
    }

    
    public float getVelY() {
        return velY;
    }
    
    public void moveLeft(){
        setVelX(-5);
        setWalking(true);
        facing=-1;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }
    
    public void moveRight(){
        setVelX(5);
        setWalking(true);
        facing=1;
    }
    
    public void jump(){
        
        
        setVelY(-10);
        setJumping(true);
    }
    
    public void stopMoving(){
        setVelX(0);
        setWalking(false);
    }
    public void stopJumping(){
        if(!falling){
        setJumping(false);}
    }
    

    
    public void setVelX(float x) {
          this.velX = x;
    }

    
    public void setVelY(float y) {
        this.velY=y;
    }


    public ObjectId getId() {
        return id;
    }
    
    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }
    
    
    
    
}
