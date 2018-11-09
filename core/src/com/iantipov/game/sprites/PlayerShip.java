package com.iantipov.game.sprites;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Ship;
import com.iantipov.game.math.Rect;
import com.iantipov.game.pool.BulletPool;

public class PlayerShip extends Ship {

    private static final float SPEED = 0.5f;
    private Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;

    private boolean isConstantShooting = false;

    public PlayerShip(TextureAtlas atlas, BulletPool bulletPool, Sound shootSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2, shootSound);
        setHeightProportion(0.15f);
        this.bulletPool = bulletPool;
        this.bulletV.set(0, 0.5f);
        this.bulletHeight = 0.01f;
        this.bulletDamage = 1;
        this.reloadInterval = 0.3f;
        this.reloadTimer = -1;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        checkAndHandleBounds();
        if (isConstantShooting)
            shoot();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.17f);
    }

    private void checkAndHandleBounds() {
        if (getLeft() < worldBounds.getLeft()) setLeft(worldBounds.getLeft());
        if (getRight() > worldBounds.getRight()) setRight(worldBounds.getRight());
    }

    public void moveLeft(boolean isMove) {
        if (isMove) v.x += -1f * SPEED;
        else v.x = 0;
    }

    public void moveRight(boolean isMove) {
        if (isMove) v.x += 1f * SPEED;
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
            case Input.Keys.SPACE:
            case Input.Keys.UP:
                isConstantShooting = true;
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
            case Input.Keys.SPACE:
            case Input.Keys.UP:
                isConstantShooting = false;
                break;
        }
        return false;
    }

    public boolean shooting() {
        return isConstantShooting = !isConstantShooting;
    }
}
