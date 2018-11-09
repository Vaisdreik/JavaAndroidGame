package com.iantipov.game.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.ClickButton;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonPlay extends ClickButton {
    public ButtonPlay(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("btPlay"), interObj);
        setHeightProportion(0.17f);
    }

    @Override
    public void resize(Rect worldBounds) {
        //setBottom(worldBounds.getBottom());
        //setLeft(worldBounds.getLeft());
    }
}
