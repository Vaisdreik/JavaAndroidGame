package com.iantipov.game;

public class Settings {
    private static final Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private float music_volume = 0.1f;
    private float sfx_volume = 0.2f;

    public float getMusic_volume() {
        return music_volume;
    }

    public void setMusic_volume(float music_volume) {
        this.music_volume = music_volume;
    }

    public float getSfx_volume() {
        return sfx_volume;
    }

    public void setSfx_volume(float sfx_volume) {
        this.sfx_volume = sfx_volume;
    }

    private Settings() {

    }
}
