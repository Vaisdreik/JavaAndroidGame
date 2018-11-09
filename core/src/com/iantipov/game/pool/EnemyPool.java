package com.iantipov.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.iantipov.game.base.SpritesPool;
import com.iantipov.game.math.Rect;
import com.iantipov.game.sprites.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {
    private BulletPool bulletPool;
    private Rect worldBounds;
    private Sound shootSound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound shootSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, worldBounds, shootSound);
    }
}