package com.gamenative.fruitymatch.fruit_asset;

import com.gamenative.fruitymatch.R;
import com.nativegame.natyengine.audio.music.Music;
import com.nativegame.natyengine.audio.music.MusicManager;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Musics {

    public static Music BG_MUSIC;
    public static Music GAME_MUSIC;

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static void load(MusicManager musicManager) {
        BG_MUSIC = musicManager.load(R.raw.fruit_happy_and_joyful_children);
        BG_MUSIC.setVolume(0.3f, 0.3f);
        BG_MUSIC.setLooping(true);
        BG_MUSIC.setCurrentStream(true);

        GAME_MUSIC = musicManager.load(R.raw.fruit_bgm);
        GAME_MUSIC.setVolume(1f, 1f);
        GAME_MUSIC.setLooping(true);
        GAME_MUSIC.setCurrentStream(false);
    }
    //========================================================

}
