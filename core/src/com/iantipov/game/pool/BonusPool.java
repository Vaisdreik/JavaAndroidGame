package com.iantipov.game.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.iantipov.game.base.SpritesPool;
import com.iantipov.game.sprites.Bonus;

public class BonusPool extends SpritesPool<Bonus> {
    private TextureRegion[] regions;

    public BonusPool(TextureAtlas atlas) {
        regions = new TextureRegion[2];
        this.regions[0] = atlas.findRegion("bonus_energy");
        this.regions[1] = atlas.findRegion("bonus_hp");
    }

    public TextureRegion getRegion(int i) {
        if (i < 0 || i > regions.length - 1) {
            return regions[0];
        } else {
            return regions[i];
        }
    }
//
//    public Bonus obtain(Bonus.BonusType bonusType) {
//        Bonus bonus = super.obtain();
//        if (bonus.getBonusType() == Bonus.BonusType.NONE)
//            return bonus.set(regions[0]);
//        if (bonus.getBonusType() != bonusType) {
//            switch (bonusType) {
//                case RECOVER_ENERGY:
//                    bonus = new Bonus(regions[0], bonusType);
//                    break;
//                case RECOVER_HP:
//                    bonus = new Bonus(regions[1], bonusType);
//                    break;
//            }
//
//        }
//        return bonus;
//    }

    @Override
    protected Bonus newObject() {
        return new Bonus();
    }
}
