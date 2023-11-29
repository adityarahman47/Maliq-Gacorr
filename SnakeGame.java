import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.World;
import greenfoot.Actor;

/**
 * Write a description of class SnakeGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeGame extends World
{

    /**
     * Constructor for objects of class SnakeGame.
     * 
     */
    private Snake snake;
    private Food food;
    private int score;
    
    public SnakeGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        snake = new Snake();
        food = new Food();
        addObject(snake, getWidth() / 2, getHeight() / 2);
        addObject(food, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        score = 0;
        

    }
    
    public void act()
    {
        snake.moveBody();
        checkCollision();
        
    }
    
    public void checkCollision()
    {
        if (snake.intersects(food)) {
            snake.extend();
            removeObject(food);
            addObject(food, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            score++;
        }
        
        if(snake.hitWall() || snake.hitSelf()) {
            Greenfoot.stop();
            showText("Game Over! Score: " + score, getWidth() / 2, getHeight() / 2);
        }
    }
}
