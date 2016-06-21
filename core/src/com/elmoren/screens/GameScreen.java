package com.elmoren.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.elmoren.cchelpers.AssetLoader;
import com.elmoren.cchelpers.InputHandler;
import com.elmoren.gameworld.GameRenderer;
import com.elmoren.gameworld.GameWorld;

public class GameScreen implements Screen {

    // Used to scale Fonts, etc when needed
    public static final float DEFAULT_WIDTH  = 640;
    public static final float DEFAULT_HEIGHT = 480;

	private GameWorld world;
	private GameRenderer renderer;
	private InputHandler inputHandler;

	public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float scale = screenWidth / DEFAULT_WIDTH;

        int midPointY = (int) (screenHeight / 2);
        int midPointX = (int) (screenWidth / 2);
        
        world = new GameWorld((int) screenWidth, (int) screenHeight);
        renderer = new GameRenderer(world, (int) screenWidth, (int) screenHeight, midPointY);

		inputHandler = new InputHandler(world, (int) midPointX);
        Gdx.input.setInputProcessor(inputHandler);

        AssetLoader.font.getData().setScale(scale);
        AssetLoader.shadow.getData().setScale(scale);
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
	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
	}

}
