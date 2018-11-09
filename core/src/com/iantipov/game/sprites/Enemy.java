package com.iantipov.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Ship;
import com.iantipov.game.math.Rect;
import com.iantipov.game.pool.BulletPool;

public class Enemy extends Ship {
    private Vector2 v0 = new Vector2();

    public Enemy(BulletPool bulletPool, Rect worldBounds, Sound shootSound) {
        super(shootSound);
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        if (isOutside(worldBounds)) {
            destroy();
        }
        super.update(delta);
        if (pos.y > worldBounds.getTop())
            v.add(0, v.y/2);
        else v.set(v0);
        pos.mulAdd(v, delta);
        shoot();
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.reloadTimer = 0.3f;
        this.hp = hp;
        setHeightProportion(height);
        v.set(v0);
    }
}
