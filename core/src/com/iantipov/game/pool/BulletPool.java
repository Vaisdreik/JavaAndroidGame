package com.iantipov.game.pool;

import com.iantipov.game.base.SpritesPool;
import com.iantipov.game.sprites.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
