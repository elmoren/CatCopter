package com.elmoren.cchelpers;

/**
 * Created by Nate on 6/14/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.elmoren.gameworld.GameWorld;

public class AssetLoader {

    public static Texture texture;
    public static Texture catTexture;
    public static Texture treeTexture;
    public static Texture sunTexture;
    public static TextureRegion bg, grass;

    public static TextureRegion tree, sun, cat;
    public static TextureRegion catFrames[];
    public static Sound dead, flap, coin;
    public static BitmapFont font, shadow;
    public static GlyphLayout glyphLayout;
    public static Preferences prefs;

    public static Decal bgDecal;
    public static Decal catDecal;
    public static Decal treeDecals[];
    public static Decal sunDecal;

    public static Animation catAnimation;
    public static float stateTime;
    public static void load() {

        // Init Font data
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        glyphLayout = new GlyphLayout();

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        catTexture = new Texture(Gdx.files.internal("data/cat.png"));
        treeTexture = new Texture(Gdx.files.internal("data/Tree.png"));
        sunTexture = new Texture(Gdx.files.internal("data/textures.png"));

        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        sunTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        sun = new TextureRegion(sunTexture, 0, 0, 25, 25);
        sun.flip(false, true);

        tree = new TextureRegion(treeTexture);
        tree.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        cat =  new TextureRegion(catTexture);
        cat.flip(true, false);
        TextureRegion[][] tmp = TextureRegion.split(catTexture, catTexture.getWidth()/4, catTexture.getHeight());
        catFrames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            catFrames[i] = tmp[0][i];
        }
        catAnimation = new Animation(0.05f, catFrames);
        stateTime = 0f;

        sunDecal = Decal.newDecal(sun, true);
        bgDecal = Decal.newDecal(bg, false);
        catDecal = Decal.newDecal(cat, true);
        bgDecal.rotateX(52.5f);
        bgDecal.setPosition(0, -200, 0);

        sunDecal.rotateX(55);

        treeDecals = new Decal[GameWorld.WORLD_MAX_OBSTACLES];
        for (int i = 0; i < GameWorld.WORLD_MAX_OBSTACLES; i++) {
            treeDecals[i] = Decal.newDecal(tree, true);
            treeDecals[i].rotateX(65);
        }

//        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
//        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
//        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

//        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
//        font.setScale(.25f, -.25f);
//        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
//        shadow.setScale(.25f, -.25f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("CoptorCat");

        // Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

    }


    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
//        dead.dispose();
//        flap.dispose();
//        coin.dispose();
        font.dispose();
        shadow.dispose();
    }
}