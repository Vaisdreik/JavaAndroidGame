package com.iantipov.game.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Movable {
    enum Directions {None, Left, Right, Up, Down}

    void move();

    void moveTo(Vector2 point);
    void moveTo(Directions direction);
}
