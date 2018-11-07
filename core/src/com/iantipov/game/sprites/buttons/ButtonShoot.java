package com.iantipov.game.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.ClickButton;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonShoot extends ClickButton {
    public ButtonShoot(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("explosion"), interObj);
        setHeightProportion(7f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
    }
}
