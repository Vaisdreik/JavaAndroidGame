package com.iantipov.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Base2DScreen;
import com.iantipov.game.gameObjects.GameObject;
import com.iantipov.game.interfaces.Movable;

public class MenuScreen extends Base2DScreen {
    private SpriteBatch batch;
    private GameObject logo;
    private GameObject ship;

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        logo = new GameObject();
        ship = new GameObject(new Texture("Ship.png"), new Vector2(50,50), new Vector2(100, 100), 1.8f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        logo.draw(batch);
        logo.move();
        ship.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        logo.dispose();
        ship.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touch= " + screenX + "," + (Gdx.graphics.getHeight() - screenY));
        logo.moveTo(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 21 || keycode == 29)
            ship.moveTo(Movable.Directions.Left);
        if (keycode == 19 || keycode == 51)
            ship.moveTo(Movable.Directions.Up);
        if (keycode == 22 || keycode == 32)
            ship.moveTo(Movable.Directions.Right);
        if (keycode == 20 || keycode == 47)
            ship.moveTo(Movable.Directions.Down);
        //System.out.println("keycode= " + keycode);
        return super.keyDown(keycode);
    }
}
