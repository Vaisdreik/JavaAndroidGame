package com.iantipov.game.sprites;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Sprite;
import com.iantipov.game.math.Rect;
import com.iantipov.game.pool.BulletPool;

public class PlayerShip extends Sprite {

    private static final float SPEED = 2f;
    private Vector2 v = new Vector2();
    private Rect worldBounds;

    private BulletPool bulletPool;
    private TextureAtlas atlas;
    private boolean pressedLeft;
    private boolean pressedRight;

    public PlayerShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.atlas = atlas;
        setHeightProportion(5f);
        v.x = 0;
        v.y = 0;
        this.bulletPool = bulletPool;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndHandleBounds();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 6f);
    }

    private void checkAndHandleBounds() {
        if (getLeft() < worldBounds.getLeft()) setLeft(worldBounds.getLeft());
        if (getRight() > worldBounds.getRight()) setRight(worldBounds.getRight());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

    public void moveLeft(boolean isMove) {
        if (isMove) v.x += -1f*SPEED;
        else v.x = 0;
    }

    public void moveRight(boolean isMove) {
        if (isMove) v.x += 1f*SPEED;
        else v.x = 0;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft(pressedLeft);
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight(pressedRight);
                break;
        }
        return false;
    }
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight(pressedRight);
                } else {
                    moveLeft(pressedLeft);
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft(pressedLeft);
                } else {
                    moveRight(pressedRight);
                }
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public void shoot() {
        System.out.println("Fire!");
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, atlas.findRegion("bulletMainShip"), new Vector2(pos.x, getTop()), new Vector2(0, 12f), 0.8f, worldBounds, 1);
    }
}
