package com.iantipov.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Sprite;
import com.iantipov.game.math.Rect;

public class Bonus extends Sprite {

    public enum BonusType {NONE, RECOVER_ENERGY, RECOVER_HP}

    private BonusType bonusType;

    private Vector2 v0 = new Vector2();
    private Vector2 descentV = new Vector2(0, -0.15f);

    private Rect worldBounds;
    private Vector2 v = new Vector2();

    private int amount;

    public Bonus() {
        regions = new TextureRegion[1];
        bonusType = BonusType.NONE;
    }

    public void set(
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            float angle,
            Rect worldBounds,
            BonusType bonusType,
            int amount
    ) {
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.angle = angle;
        this.worldBounds = worldBounds;
        this.bonusType = bonusType;
        this.amount = amount;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
