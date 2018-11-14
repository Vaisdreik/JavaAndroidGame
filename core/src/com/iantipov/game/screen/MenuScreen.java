package com.iantipov.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.Settings;
import com.iantipov.game.base.Base2DScreen;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;
import com.iantipov.game.sprites.Background;
import com.iantipov.game.sprites.buttons.ButtonExit;
import com.iantipov.game.sprites.buttons.ButtonPlay;
import com.iantipov.game.sprites.Star;


public class MenuScreen extends Base2DScreen implements Interactable {

    private static final int STAR_COUNT = 256;

    private Game game;

    private Texture bgTexture;
    private Background background;

    private TextureAtlas textureAtlas;
    private Star[] stars;

    private ButtonExit btnExit;
    private ButtonPlay btnPlay;

    private Music music;

    public MenuScreen(Game game) {
        super();
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(bgTexture));
        textureAtlas = new TextureAtlas("menuAtlas.tpack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(textureAtlas);
        }
        btnExit = new ButtonExit(textureAtlas, this);
        btnPlay = new ButtonPlay(textureAtlas, this);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Space_adventure_01.mp3"));
        music.setLooping(true);
        music.setVolume(Settings.getInstance().getMusic_volume());
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        btnExit.draw(batch);
        btnPlay.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
        btnExit.resize(worldBounds);
        btnPlay.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        textureAtlas.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        btnExit.touchDown(touch, pointer);
        btnPlay.touchDown(touch, pointer);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        btnExit.touchUp(touch, pointer);
        btnPlay.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public void action(Object obj) {
        if (obj == btnExit) {
            Gdx.app.exit();
        } else if (obj == btnPlay) {
            game.setScreen(new GameScreen(game));
        }
    }
}
