package com.iantipov.game.sprites.buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.ClickButton;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonExit extends ClickButton {
    public ButtonExit(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("btExit"), interObj);
        setHeightProportion(0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setTop(worldBounds.getTop());
        setRight(worldBounds.getRight());
    }
}
