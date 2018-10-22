package com.iantipov.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.base.Base2DGameObject;
import com.iantipov.game.interfaces.Movable;

public class GameObject extends Base2DGameObject implements Movable {

    private Texture img;
    private Vector2 size;
    private Vector2 pos;
    private Vector2 dir;
    private float speed;

    public GameObject() {
        img = new Texture("badlogic.jpg");
        size = new Vector2(img.getWidth(), img.getHeight());
        pos = new Vector2(0, 0);
        dir = new Vector2(3, 2);
        speed = 0.5f;
        dir.nor().scl(speed);
    }

    public GameObject(Texture texture, Vector2 textureSize, Vector2 position, float velocity) {
        img = texture;
        size = textureSize;
        pos = position;
        dir = new Vector2(0, 0);
        speed = velocity;
        dir.nor().scl(speed);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, pos.x, pos.y, size.x, size.y);
    }

    @Override
    public void move() {
        if (pos.x + size.x + dir.x >= Gdx.graphics.getWidth() | pos.x + dir.x <= 0)
            dir.x = -dir.x;
        if (pos.y + size.y + dir.y >= Gdx.graphics.getHeight() | pos.y + dir.y <= 0)
            dir.y = -dir.y;
        pos.add(dir);
    }

    @Override
    public void moveTo(Vector2 point) {
        dir = point.sub(new Vector2(pos.x + size.x / 2, pos.y + size.y / 2));
        dir.nor();
        dir.scl(speed);
        move();
    }

    @Override
    public void moveTo(Directions direction) {
        switch (direction) {
            case Left:
                dir = new Vector2(-1, 0);
                break;
            case Right:
                dir = new Vector2(1, 0);
                break;
            case Up:
                dir = new Vector2(0, 1);
                break;
            case Down:
                dir = new Vector2(0, -1);
                break;
        }
        dir.scl(speed);
        move();
    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
