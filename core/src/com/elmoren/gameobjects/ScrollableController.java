package com.elmoren.gameobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.elmoren.gameworld.GameWorld;

public class ScrollableController {

	private GameWorld world;
	private ArrayList<Tree> obstacles;
	private float direction;
	Random r;

	public ScrollableController(GameWorld world) {
		r = new Random();
		this.world = world;
		this.obstacles = new ArrayList<Tree>();
	}

	public void add(Tree s) {
		obstacles.add(s);
	}

	public void update(float delta) {

		for (Scrollable s: obstacles) {
			s.setAngle(direction);
			s.update(delta);
		}

	}
	
	public boolean collides(Cat cat) {
		for (Tree s: obstacles) {
			if ( s.collides(cat) ) {
				return true;
			}
		}
		
		return false;
	}
	
	public void stop() {
		for (Scrollable s: obstacles) {
			s.stop();
		}
	}
	
	public void setDirection(float angle) {
		direction = angle;		
	}

	public ArrayList<Tree> getObstacles() {
		return obstacles;
	}

	public void restart() {
		obstacles.clear();		
	}

	public int size() {
		return obstacles.size();
	}
}
