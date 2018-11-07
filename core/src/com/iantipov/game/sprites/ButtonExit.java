package com.iantipov.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.Button;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonExit extends Button {
    public ButtonExit(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("btExit"), interObj);
        setHeightProportion(3f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setTop(worldBounds.getTop());
        setRight(worldBounds.getRight());
    }
}
