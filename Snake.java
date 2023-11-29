import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.Actor;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Snake extends Actor
{
    private int speed = 1; //Kecepatan ular
    private int length = 1; // Panjang awal ular
    private int delay = 10; // Delay antar gerakan
    private int counter = 0; // Counter untuk delay
    private int[] xCoords; //Array untuk menyimpan koordinat x
    private int[] yCoords; //Array untuk menyimpan koordinat y
    private int direction = 0; // Arah awal ular
    

    public Snake()
    {
        setImage("snake.png");
        
        xCoords = new int[100];
        yCoords = new int[100];
        
        xCoords[0] = getX();
        yCoords[0] = getY();
    }
    
    public void act()
    {
        if (counter == delay) {
            move(speed);
            counter = 0;
            
            // Simpan koordinat baru
            xCoords[0] = getX();
            yCoords[0] = getY();
            
            // Pergerakan ular
            moveBody();
            
            // Perubahan arah ular
            checkKeyPress();
            
            // Memeriksa tumbukan dengan tepi layar
            checkEdgeCollision();
        } else{
            counter++;
        }
    }
    
    public void moveBody()
    {
        //Memindahkan Tubuh ular mengikuti kepala
        for (int i = length - 1; i > 0; i--){
            xCoords[i] = xCoords[i - 1];
            yCoords[i] = yCoords[i - 1];
        }
    }
    
    public void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("right") && direction != 2){
            direction = 0;
        }
        if (Greenfoot.isKeyDown("up") && direction != 3){
            direction = 1;
        }
        if (Greenfoot.isKeyDown("left") && direction != 0){
            direction = 2;
        }
        if (Greenfoot.isKeyDown("down") && direction != 1){
            direction = 3;
        }
    }
    
    public void checkEdgeCollision()
    {
        if (getX() <= 0 || getX() >= getWorld().getWidth() -1 || getY() <= 0 || getY() >= getWorld().getHeight() - 1){
            Greenfoot.stop();
        }
    }
    
    public boolean hitWall()
    {
        if (getX() <= 0 || getX() >= getWorld().getWidth() -1 || getY() <= 0 || getY() >= getWorld().getHeight() - 1){
            return true;
        }
        return false;
    }
    
    public boolean hitSelf()
    {
        for (int i = 1; i < length; i ++) {
            if (getX() == xCoords[i] && getY() == yCoords[i]) {
                return true;
            }
        }
        return false;
    }
    
    public void extend()
    {
    length++;
    xCoords[length - 1] = xCoords[length - 2];
    yCoords[length - 1] = yCoords[length - 2];
    getWorld().addObject(new Tail(), xCoords[length - 1], yCoords[length - 1]);
    }


    
    public boolean intersects(Food food)
    {
        if (food != null) {
            return getOneIntersectingObject(Food.class) != null;
        }
        return false;
    }
    
}
