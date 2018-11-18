package com.iantipov.game.pool;

import com.badlogic.gdx.audio.Sound;
import com.iantipov.game.base.SpritesPool;
import com.iantipov.game.math.Rect;
import com.iantipov.game.sprites.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rect worldBounds;
    private Sound shootSound;
    private BonusPool bonusPool;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, BonusPool bonusPool, Rect worldBounds, Sound shootSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
        this.explosionPool = explosionPool;
        this.bonusPool = bonusPool;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, bonusPool,  worldBounds, shootSound);
    }
}
