package com.elmoren.cchelpers;

import com.badlogic.gdx.InputProcessor;
import com.elmoren.gameobjects.Cat;
import com.elmoren.gameworld.GameWorld;

/**
 * Created by Nate on 6/14/2016.
 */
public class InputHandler implements InputProcessor {
    private GameWorld world;
    private Cat cat;
    private boolean isTouchedDown;
    private int midPointX;

    public InputHandler (GameWorld w, int midPointX) {
        this.world = w;
        cat = world.getCat();
        this.midPointX = midPointX;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (world.isReady()) {
            world.start();
        }

        if (world.isRunning()) {
            cat.setTurning(true);
            if (screenX > midPointX)
                cat.setDirection(Cat.RIGHT);
            else
                cat.setDirection(Cat.LEFT);

            this.isTouchedDown = true;
        }

        if (world.isGameOver() || world.isHighScore()) {
            world.restart();
        }

        return true;

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        this.isTouchedDown = false;
        cat.setTurning(false);
        cat.setDirection(Cat.STRAIGHT);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean isTouchedDown() {
        return isTouchedDown;
    }
}
