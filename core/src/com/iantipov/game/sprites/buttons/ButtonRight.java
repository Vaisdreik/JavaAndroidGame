package com.iantipov.game.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.PressButton;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonRight extends PressButton {
    public ButtonRight(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("right"), interObj);
        setHeightProportion(0.18f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
