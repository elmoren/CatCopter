package com.elmoren.gameworld;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.elmoren.cchelpers.AssetLoader;
import com.elmoren.gameobjects.Cat;
import com.elmoren.gameobjects.Scrollable;
import com.elmoren.gameobjects.ScrollableController;
import com.elmoren.gameobjects.Tree;

public class GameWorld {

    public static final int WORLD_MAX_OBSTACLES = 10;

	private Cat cat;
	private Rectangle ground;
	private ScrollableController scrollController;
    private int score;
    
    private GameState currentState;
    public enum GameState {
    	READY, RUNNING, GAMEOVER, HIGHSCORE
    }
    
	public GameWorld(int width, int height)	{
        this.cat = new Cat(-8, 65, 17, 12, 12f);
        this.ground = new Rectangle(width * -1.5f, height * -0.5f, width*3, height*2);        
        this.score = 0;
        this.scrollController = new ScrollableController(this);	        
        currentState = GameState.READY;
	}
	
	public void update(float delta) {	
		
        switch (currentState) {
        case READY:
            updateReady(delta);
            break;

        case RUNNING:
            updateRunning(delta);
            break;
        
        case GAMEOVER:
            updateGameOver(delta);
            break;
            
        default:
            break;
        }
		
	}
	
	private void updateGameOver(float delta) {
		// Return turn rate to 0 on dead.
		cat.update(delta);		
	}

	private void updateReady(float delta) {
		
	}
	
	private void updateRunning(float delta) {
		
        if (delta > .15f) {
            delta = .15f;
        }
		
		cat.update(delta);
		scrollController.setDirection(cat.getRotation());
		scrollController.update(delta);			

		if (scrollController.collides(cat) && cat.isAlive()) {
			scrollController.stop();
			cat.die();
			currentState = GameState.GAMEOVER;
			
			if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
			
		}
		else {
			this.score += 1;
		}
	}
	
    public Rectangle getGround() {
        return ground;
    }
    
    public Cat getCat() {
    	return this.cat;
    }
    
    public ArrayList<Tree> getObstacles() {
    	return scrollController.getObstacles();
    }
    
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
    
    public boolean isReady() {
        return currentState == GameState.READY;
    }
    
    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
    
    public void start() {
        for (int i = 0; i < this.WORLD_MAX_OBSTACLES; i++) {
			scrollController.add(new Tree(0f, 100f, 20, 10, 3f));
		}
        currentState = GameState.RUNNING;
    }

	public void restart() {
		cat.restart();		
		score = 0;
		scrollController.restart();
		currentState = GameState.READY;
	}
    
	public int getScore() {
		return score;
	}
}
