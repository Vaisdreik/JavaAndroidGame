package com.iantipov.game.sprites.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.ClickButton;
import com.iantipov.game.interfaces.Interactable;
import com.iantipov.game.math.Rect;

public class ButtonNewGame extends ClickButton {
    private Texture bgButtonTexture;
    public ButtonNewGame(TextureAtlas atlas, Interactable interObj) {
        super(atlas.findRegion("button_new_game"), interObj);
        setHeightProportion(0.06f);
        Pixmap pixmap = new Pixmap(regions[0].getRegionWidth(), regions[0].getRegionHeight(), Pixmap.Format.RGBA8888);
        Color color = Color.GRAY;
        pixmap.setColor(color.r, color.g, color.b, 0.5f);
        pixmap.fillRectangle(0, 0, regions[0].getRegionWidth(), regions[0].getRegionHeight());
        bgButtonTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                bgButtonTexture,
                getLeft()-0.02f, getBottom()-0.02f,
                halfWidth, halfHeight,
                getWidth()+0.04f, getHeight()+0.04f,
                scale, scale,
                angle,
                0, 0,
                regions[0].getRegionWidth(), regions[0].getRegionHeight(),
                false, false);
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        pos.add(0, -0.05f);
    }
}
