package com.elmoren.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.elmoren.cchelpers.InputHandler;
import com.elmoren.gameworld.GameRenderer;
import com.elmoren.gameworld.GameWorld;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private InputHandler inputHandler;
	public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();      
        float gameWidth = screenWidth;
        float gameHeight = screenHeight;
        
        int midPointY = (int) (gameHeight / 2);
        int midPointX = (int) (gameWidth / 2);
        
        world = new GameWorld((int) gameWidth, (int) gameHeight);
        renderer = new GameRenderer(world, (int) gameWidth, (int) gameHeight, midPointY);

		inputHandler = new InputHandler(world, (int) midPointX);
        Gdx.input.setInputProcessor(inputHandler);
	}
	
	@Override
	public void render(float delta) {

		world.update(delta);
	    renderer.render(delta);
		
	}

	@Override
	public void resize(int width, int height) {
        int midPointX = (int) (Gdx.graphics.getWidth() / 2);
        inputHandler.setMidpointX(midPointX);

		System.out.println("Resize called");
		// Reset the midpoint for the input handling.
	}

	@Override
	public void show() {
		
		System.out.println("Show called");
		
	}

	@Override
	public void hide() {
		
		System.out.println("Hide called");
		
	}

	@Override
	public void pause() {
		
		System.out.println("Pause called");
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

	}

}
