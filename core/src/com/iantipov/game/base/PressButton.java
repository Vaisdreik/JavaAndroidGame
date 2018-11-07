package com.iantipov.game.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.iantipov.game.interfaces.Interactable;

public class PressButton extends ClickButton {

    public PressButton(TextureRegion region, Interactable interObj) {
        super(region, interObj);
        this.interObj = interObj;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isPressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        this.scale = 0.8f;
        this.isPressed = true;
        interObj.action(this);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !isPressed) {
            return false;
        }
        isPressed = false;
        scale = 1f;
        interObj.action(this);
        return false;
    }

    public boolean getButtonState() {
        return isPressed;
    }
}
