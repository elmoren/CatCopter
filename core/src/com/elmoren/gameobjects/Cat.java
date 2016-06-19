package com.elmoren.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Cat {
	
	public static final int LEFT      = -1;
	public static final int STRAIGHT  =  0;
	public static final int RIGHT     =  1;
	
	private final float MAX_LEFT  = -45.0f;
	private final float MAX_RIGHT =  45.0f;
	private final float TURN_RATE =   4.5f;
	private final float HOVER_DELTA = 1.7f;
	private final float HOVER_RATE = 0.05f;
	
	private Vector2 position;
	private int direction;
	
	private float rotation; // For handling bird rotation
	private int width;
	private int height;
	private float elevation;
	private float maxElevation;
	private float minElevation;
	private boolean isTurning;
	private float hoverDirection;
	private Rectangle boundingBox;
	private boolean isAlive;
	
	public Cat (float x, float y, int width, int height, float elevation) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y); 
        isTurning = false;
        rotation = 0;
        direction = STRAIGHT;
        this.elevation = elevation;
        this.maxElevation = elevation + HOVER_DELTA;
        this.minElevation = elevation - HOVER_DELTA;
        this.hoverDirection = 1.0f;        
        boundingBox = new Rectangle(x - (width/2), y, this.width, this.height);
        isAlive = true;
    }
	
    public void update(float delta) {
    	
    	if (isTurning) {
    		rotation += (this.direction * TURN_RATE);
    		
    	}
    	else {
    		if (rotation > 0.0f) {
    			rotation -= TURN_RATE;
    		}
    		else if (rotation < 0.0f) {
    			rotation += TURN_RATE;
    		}
    	}
    	
    	if (rotation < MAX_LEFT)
    		rotation = MAX_LEFT;
    	if (rotation > MAX_RIGHT)
    		rotation = MAX_RIGHT;
    	
		if (Math.abs(rotation) < TURN_RATE)
			rotation = 0f;
		
	   				
		if (elevation > maxElevation) {
			elevation = maxElevation;
			hoverDirection = -1f;
		} 
		else if (elevation < minElevation){
			elevation = minElevation;
			hoverDirection = 1f;
		}
		else {
			elevation += HOVER_RATE * hoverDirection;
		}
    }

    public void die() {
    	isAlive = false;
    }
    
    public boolean isAlive() {
    	return isAlive;
    }
    
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getElevation() {
    	return elevation;
    }
    
    public float getRotation() {
        return rotation;
    }

    public boolean isTurning() {
    	return this.isTurning;
    }
    
	public void setTurning(boolean turning) {
		this.isTurning = turning;
	}

	public void setDirection(int dir) {
		if (dir > 0)
			direction = RIGHT;
		else if (dir < 0)
			direction = LEFT;
		else
			direction = STRAIGHT;
	}
		
	public Rectangle getBoundingBox() {
		return boundingBox;		
	}

	public void restart() {
		direction = Cat.STRAIGHT;
		rotation = 0f;
		isAlive = true;
	}
}
