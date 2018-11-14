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
import com.iantipov.game.Settings;
import com.iantipov.game.base.Base2DScreen;
import com.iantipov.game.base.ClickButton;
import com.iantipov.game.base.Sprite;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;
import com.iantipov.game.pool.BulletPool;
import com.iantipov.game.pool.EnemyPool;
import com.iantipov.game.pool.ExplosionPool;
import com.iantipov.game.sprites.*;
import com.iantipov.game.sprites.buttons.*;
import com.iantipov.game.utils.EnemiesEmmiter;

import java.util.List;

public class GameScreen extends Base2DScreen implements Interactable {

    private static final int STAR_COUNT = 60;

    private Game game;
    private PlayerShip playerShip;
    private BulletPool bulletPool;

    private Texture bgTexture;
    private Background background;
    private Sprite gameOver;

    private TextureAtlas textureAtlas;
    private TextureAtlas mainTextureAtlas;
    private TextureAtlas gameGUIAtlas;
    private Star[] stars;

    private ButtonExit btnExit;
    private ButtonLeft btnLeft;
    private ButtonRight btnRight;
    private ButtonShoot btnShoot;
    private ClickButton btnNewGame;

    private Sound shootSound;
    private Sound laserSound;
    private Sound explosionSound;
    private Music music;

    private EnemyPool enemyPool;
    private EnemiesEmmiter enemiesEmmiter;
    private ExplosionPool explosionPool;

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
        gameOver = new Sprite(mainTextureAtlas.findRegion("message_game_over"));
        gameOver.setHeightProportion(0.08f);
        gameOver.pos.add(0, 0.15f);
        btnNewGame = new ButtonNewGame(mainTextureAtlas, this);
        btnExit = new ButtonExit(textureAtlas, this);
        btnLeft = new ButtonLeft(gameGUIAtlas, this);
        btnRight = new ButtonRight(gameGUIAtlas, this);
        btnShoot = new ButtonShoot(gameGUIAtlas, this);
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Space_adventure_01.mp3"));
        music.setLooping(true);
        music.setVolume(Settings.getInstance().getMusic_volume());
        music.play();
        explosionPool = new ExplosionPool(mainTextureAtlas, explosionSound);
        bulletPool = new BulletPool();
        playerShip = new PlayerShip(mainTextureAtlas, bulletPool, explosionPool, shootSound);

        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, shootSound);
        enemiesEmmiter = new EnemiesEmmiter(enemyPool, worldBounds, mainTextureAtlas);
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
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getHalfWidth() + playerShip.getHalfWidth();
            if (enemy.pos.dst2(playerShip.pos) < minDist * minDist) {
                enemy.destroy();
                playerShip.destroy();
                return;
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed() || bullet.getOwner() == playerShip) {
                continue;
            }
            if (playerShip.isBulletCollision(bullet)) {
                bullet.destroy();
                playerShip.damage(bullet.getDamage());
                return;
            }
        }
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.isDestroyed() || bullet.getOwner() != playerShip) {
                    continue;
                }
                if (enemy.isBulletCollision(bullet)) {
                    bullet.destroy();
                    enemy.damage(bullet.getDamage());
                    return;
                }
            }
        }
    }

    private void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    private void update(float delta) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
        if (!playerShip.isDestroyed()) {
            playerShip.update(delta);
            bulletPool.updateActiveObjects(delta);
            enemyPool.updateActiveObjects(delta);
            explosionPool.updateActiveObjects(delta);
            enemiesEmmiter.generate(delta);
        } else {
            //TODO: destroy all?
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
        if (!playerShip.isDestroyed()) {
            playerShip.draw(batch);
            bulletPool.drawActiveObjects(batch);
            enemyPool.drawActiveObjects(batch);
            explosionPool.drawActiveObjects(batch);
            btnLeft.draw(batch);
            btnRight.draw(batch);
            btnShoot.draw(batch);

        } else {
            gameOver.draw(batch);
            btnNewGame.draw(batch);
        }


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
        shootSound.dispose();
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
        btnNewGame.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        btnExit.touchUp(touch, pointer);
        btnLeft.touchUp(touch, pointer);
        btnRight.touchUp(touch, pointer);
        btnShoot.touchUp(touch, pointer);
        btnNewGame.touchUp(touch, pointer);
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
            playerShip.shooting();
        } else if (obj == btnNewGame) {
            System.out.println("new game");
            game.setScreen(new GameScreen(game));
        }
    }
}
