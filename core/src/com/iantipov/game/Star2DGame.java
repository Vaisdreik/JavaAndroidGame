package com.iantipov.game;

import com.badlogic.gdx.Game;
import com.iantipov.game.screen.MenuScreen;

public class Star2DGame extends Game {
    private PlayerStats playerStats;

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    @Override
    public void create() {
        loadPlayer();
        setScreen(new MenuScreen(this));
    }

    private void loadPlayer() {
        playerStats = new PlayerStats();
    }
}
