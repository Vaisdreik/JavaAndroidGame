package com.iantipov.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Ship;
import com.iantipov.game.math.Rect;
import com.iantipov.game.math.Rnd;
import com.iantipov.game.pool.BonusPool;
import com.iantipov.game.pool.BulletPool;
import com.iantipov.game.pool.ExplosionPool;

public class Enemy extends Ship {
    private static final float BONUS_DROP_RATE = 0.65f;

    private enum State {DESCENT, FIGHT}

    private Vector2 v0 = new Vector2();

    private State state;
    private Vector2 descentV = new Vector2(0, -0.15f);

    private BonusPool bonusPool;

    public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, BonusPool bonusPool, Rect worldBounds, Sound shootSound) {
        super(shootSound);
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.bonusPool = bonusPool;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        switch (state) {
            case DESCENT:
                if (getTop() <= worldBounds.getTop()) {
                    v.set(v0);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:
                shoot();
                if (getTop() < worldBounds.getBottom()) {
                    //boom();
                    destroy();
                }
                break;
        }
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
        this.fireInterval = reloadInterval;
        this.reloadTimer = 0.3f;
        this.hp = hp;
        this.energy = 10000;
        setHeightProportion(height);
        v.set(descentV);
        state = State.DESCENT;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
        );
    }

    @Override
    public void destroy() {
        super.destroy();
        dropBonus(pos);
    }

    public void dropBonus(Vector2 position) {
        if ((float) Math.random() <= BONUS_DROP_RATE) {
            int type = Rnd.nextInt(0, Bonus.BonusType.values().length - 1);
            Bonus bonus;
            switch (type) {
                default:
                case 1:
                    bonus = bonusPool.obtain();
                    bonus.set(bonusPool.getRegion(0), position, descentV, 0.04f, 90f,worldBounds, Bonus.BonusType.RECOVER_ENERGY, 20);
                    break;
                case 2:
                    bonus = bonusPool.obtain();
                    bonus.set(bonusPool.getRegion(1), position, descentV, 0.04f, 0f, worldBounds, Bonus.BonusType.RECOVER_HP, 20);
                    break;
            }
            System.out.println("drop bonus!");
        }
    }
}
