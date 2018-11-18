package com.iantipov.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.PlayerStats;
import com.iantipov.game.math.Rect;
import com.iantipov.game.math.Rnd;
import com.iantipov.game.pool.EnemyPool;
import com.iantipov.game.sprites.Enemy;

public class EnemiesEmmiter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.45f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.3f;
    private static final int ENEMY_MEDIUM_BULLET_DAMAGE = 5;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 4.2f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.06f;
    private static final float ENEMY_BIG_BULLET_VY = -0.25f;
    private static final int ENEMY_BIG_BULLET_DAMAGE = 12;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 4f;
    private static final int ENEMY_BIG_HP = 20;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyMediumRegion;
    private TextureRegion[] enemyBigRegion;
    private Vector2 enemySmallV = new Vector2(0, -0.15f);
    private Vector2 enemyMediumV = new Vector2(0, -0.025f);
    private Vector2 enemyBigV = new Vector2(0, -0.003f);

    private EnemyPool enemyPool;
    private Rect worldBounds;
    private TextureRegion bulletRegion;

    private float generateInterval = 4f;
    private float generateTimer;

    private PlayerStats player;

    public EnemiesEmmiter(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas, PlayerStats player) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0, 1, 2, 2);

        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMediumRegion = Regions.split(textureRegion1, 1, 2, 2);

        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegion2, 1, 2, 2);

        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.player = player;
        generateTimer = 2f;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval - (player.getCurrentLevel()-1)/5f) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.82f - player.getCurrentLevel()/50) {
                enemy.set(
                        enemySmallRegion,
                        (new Vector2(0,player.getCurrentLevel()/500)).add(enemySmallV),
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE + (player.getCurrentLevel()-1)*2,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP + (player.getCurrentLevel()-1)*2
                );
            } else if (type < 0.98f - player.getCurrentLevel()/50) {
                enemy.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        bulletRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_BULLET_DAMAGE * player.getCurrentLevel(),
                        ENEMY_MEDIUM_RELOAD_INTERVAL - (player.getCurrentLevel()-1)/5f,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP + (player.getCurrentLevel()-1)*3
                );
            } else {
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE * player.getCurrentLevel(),
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP + (player.getCurrentLevel()-1)*2
                );
            }
            enemy.setBottom(worldBounds.getTop());
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            type = (float) Math.random();
            if (type < 0.4f)
                enemy.dropBonus(new Vector2(Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth()), worldBounds.getTop()));
        }
    }
}
