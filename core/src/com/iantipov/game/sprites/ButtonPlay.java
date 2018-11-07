package com.iantipov.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.Button;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonPlay extends Button {
    public ButtonPlay(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("btPlay"), interObj);
        setHeightProportion(6f);
    }

    @Override
    public void resize(Rect worldBounds) {
        //setBottom(worldBounds.getBottom());
        //setLeft(worldBounds.getLeft());
    }
}
