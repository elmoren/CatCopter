package com.elmoren.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
    
    protected Vector2 position;
    protected float velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;
    protected float angle;
    
    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        this.velocity = scrollSpeed;
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void stop() {
    	velocity = 0;
    }
    
    public void update(float delta) {    
    	
 //   	position.add(0, velocity * delta);
    	
    	if (position.y + width < 0) {
            isScrolledLeft = true;
        }
    }

    public void reset(float x, float y) {
        position.x = x;
        position.y = y;
        isScrolledLeft = false;
    }

    // Getters for instance variables
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

	public void setAngle(float rotation) {		
		this.angle = rotation;
	}

}