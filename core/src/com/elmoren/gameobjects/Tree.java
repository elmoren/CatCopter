package com.elmoren.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Tree extends Scrollable {
	
	public static final int TREE_WIDTH = 64;
	public static final int TREE_HEIGHT = 128;

    private Rectangle boundingBox;
    private Random r;

    // When Tree constructor is invoked, invoke the super (Scrollable) constructor
    public Tree(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        // Initialize a Random object for Random number generation
        boundingBox = new Rectangle(x, y, width, height);
        r = new Random();
    }

    @Override
    public void update(float delta) {

        super.update(delta);

        float x = -1f * (float) (velocity * Math.sin(Math.toRadians(angle)));
        float y = (float) (velocity * Math.cos(Math.toRadians(angle)));
    	
        position.add(x, y);
        
        if (position.y > 375f)
        	reset(x, -300f);
                
    }
    
    public boolean collides(Cat cat) {
    	
    	boundingBox.x = position.x - (width / 2);
    	boundingBox.y = position.y;

    	return Intersector.overlaps(cat.getBoundingBox(), this.boundingBox);
    }
    
    @Override
    public void reset(float x, float y) {
        // Call the reset method in the superclass (Scrollable)
        super.reset(x, y);
        
        position.x = r.nextInt(1000) - 500;
        position.y = y - r.nextInt(300);
        
        System.out.println("Reset called");
    }
    
    public void onRestart(float x, float y, float scrollSpeed) {
        velocity = scrollSpeed;
        reset(x, y);
    }
    
    public void setAngle(float a) {
    	angle = a;
    }
    
}
