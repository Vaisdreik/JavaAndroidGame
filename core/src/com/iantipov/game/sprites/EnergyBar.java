package com.iantipov.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.iantipov.game.base.Sprite;
import com.iantipov.game.math.Rect;

public class EnergyBar extends Sprite {
    private Texture bgBarTexture;
    private Texture barTexture;
    private float maxWidth;
    private float currentWidth;

    public EnergyBar(TextureAtlas atlas) {
        super(atlas.findRegion("bulletEnemy"));
        setHeight(0.001f);
        setWidth(0.2f);
        Pixmap pixmap = new Pixmap(regions[0].getRegionWidth(), regions[0].getRegionHeight(), Pixmap.Format.RGBA8888);
        Color color = Color.GRAY;
        pixmap.setColor(color.r, color.g, color.b, 0.5f);
        pixmap.fillRectangle(0, 0, regions[0].getRegionWidth(), regions[0].getRegionHeight());
        bgBarTexture = new Texture(pixmap);
        color = Color.CYAN;
        pixmap.setColor(color.r, color.g, color.b, 1f);
        pixmap.fillRectangle(0, 0, regions[0].getRegionWidth(), regions[0].getRegionHeight());
        barTexture = new Texture(pixmap);
        pixmap.dispose();
        maxWidth = getWidth()+0.04f;
        currentWidth = maxWidth;
    }

    public void updateEnergyBar(int energy, int maxEnergy) {
        currentWidth = maxWidth*((float)energy/(float)maxEnergy);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(
                bgBarTexture,
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                maxWidth, getHeight()+0.025f,
                scale, scale,
                angle,
                0, 0,
                regions[0].getRegionWidth(), regions[0].getRegionHeight(),
                false, false);
        batch.draw(
                barTexture,
                getLeft(), getBottom(),
                halfWidth, halfHeight,
                currentWidth, getHeight()+0.025f,
                scale, scale,
                angle,
                0, 0,
                regions[0].getRegionWidth(), regions[0].getRegionHeight(),
                false, false);
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        setLeft(worldBounds.getLeft() + 0.19f + maxWidth);
        setTop(worldBounds.getTop()-0.03f);
    }
}
