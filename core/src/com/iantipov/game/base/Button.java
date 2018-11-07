package com.iantipov.game.base;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.interfaces.Interactable;

public class Button extends Sprite {

    private int pointer;
    private boolean isPressed;
    private Interactable interObj;

    public Button(TextureRegion region, Interactable interObj) {
        super(region);
        this.interObj = interObj;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isPressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        this.scale = 0.9f;
        this.isPressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !isPressed) {
            return false;
        }
        if (isMe(touch)) {
            interObj.action(this);
        }
        isPressed = false;
        scale = 1f;
        return false;
    }
}
