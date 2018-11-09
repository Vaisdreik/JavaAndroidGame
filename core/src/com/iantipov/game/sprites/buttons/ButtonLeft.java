package com.iantipov.game.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.PressButton;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonLeft extends PressButton {
    public ButtonLeft(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("left"), interObj);
        setHeightProportion(0.17f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }


}
