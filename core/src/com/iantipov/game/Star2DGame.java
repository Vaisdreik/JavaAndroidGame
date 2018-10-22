package com.iantipov.game;

import com.badlogic.gdx.Game;
import com.iantipov.game.screen.MenuScreen;

public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen());
    }
}
