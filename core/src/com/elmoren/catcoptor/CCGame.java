package com.elmoren.catcoptor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elmoren.cchelpers.AssetLoader;
import com.elmoren.screens.GameScreen;

public class CCGame extends Game {
	SpriteBatch batch;
	Texture img;

    @Override
    public void create() {
        System.out.println("CC Game Created");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }

}
