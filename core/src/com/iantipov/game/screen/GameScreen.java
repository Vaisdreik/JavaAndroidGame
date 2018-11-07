package com.iantipov.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Base2DScreen;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;
import com.iantipov.game.pool.BulletPool;
import com.iantipov.game.sprites.*;
import com.iantipov.game.sprites.buttons.ButtonExit;
import com.iantipov.game.sprites.buttons.ButtonShoot;
import com.iantipov.game.sprites.buttons.ButtonLeft;
import com.iantipov.game.sprites.buttons.ButtonRight;

public class GameScreen extends Base2DScreen implements Interactable {

    private static final int STAR_COUNT = 60;

    private Game game;
    private PlayerShip playerShip;
    private BulletPool bulletPool;

    private Texture bgTexture;
    private Background background;

    private TextureAtlas textureAtlas;
    private TextureAtlas mainTextureAtlas;
    private TextureAtlas gameGUIAtlas;
    private Star[] stars;

    private ButtonExit btnExit;
    private ButtonLeft btnLeft;
    private ButtonRight btnRight;
    private ButtonShoot btnShoot;

    private Sound shootSound;

    public GameScreen(Game game) {
        super();
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(bgTexture));
        textureAtlas = new TextureAtlas("menuAtlas.tpack");
        mainTextureAtlas = new TextureAtlas("mainAtlas.tpack");
        gameGUIAtlas = new TextureAtlas("gameGUIAtlas.pack");
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(textureAtlas);
        }
        btnExit = new ButtonExit(textureAtlas, this);
        btnLeft = new ButtonLeft(gameGUIAtlas, this);
        btnRight = new ButtonRight(gameGUIAtlas, this);
        btnShoot = new ButtonShoot(gameGUIAtlas, this);
        bulletPool = new BulletPool();
        playerShip = new PlayerShip(mainTextureAtlas, bulletPool);

        shootSound = Gdx.audio.newSound(Gdx.files.internal("shoot_sfx.mp3"));

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    private void checkCollisions() {

    }

    private void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
    }

    private void update(float delta) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
        playerShip.update(delta);
        bulletPool.updateActiveObjects(delta);
    }

    public void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        playerShip.draw(batch);
        bulletPool.drawActiveObjects(batch);
        btnLeft.draw(batch);
        btnRight.draw(batch);
        btnShoot.draw(batch);
        btnExit.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
        playerShip.resize(worldBounds);
        btnExit.resize(worldBounds);
        btnLeft.resize(worldBounds);
        btnRight.resize(worldBounds);
        btnShoot.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        textureAtlas.dispose();
        mainTextureAtlas.dispose();
        gameGUIAtlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        playerShip.keyDown(keycode);
        return super.keyDown(keycode);
    }
    @Override
    public boolean keyUp(int keycode) {
        playerShip.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        btnExit.touchDown(touch, pointer);
        btnLeft.touchDown(touch, pointer);
        btnRight.touchDown(touch, pointer);
        btnShoot.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        btnExit.touchUp(touch, pointer);
        btnLeft.touchUp(touch, pointer);
        btnRight.touchUp(touch, pointer);
        btnShoot.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public void action(Object obj) {
        if (obj == btnExit) {
            Gdx.app.exit();
        } else if (obj == btnLeft) {
                playerShip.moveLeft(btnLeft.getButtonState());
        } else if (obj == btnRight) {
            playerShip.moveRight(btnRight.getButtonState());
        } else if (obj == btnShoot) {
            shootSound.play();
            playerShip.shoot();
        }
    }
}
