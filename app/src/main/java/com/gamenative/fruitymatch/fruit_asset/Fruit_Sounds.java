package com.gamenative.fruitymatch.fruit_asset;

import com.gamenative.fruitymatch.R;
import com.nativegame.natyengine.audio.sound.Sound;
import com.nativegame.natyengine.audio.sound.SoundManager;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Sounds {

    public static Sound BUTTON_CLICK;
    public static Sound DIALOG_SLIDE;
    public static Sound WHEEL_SPIN;

    public static Sound GAME_START;
    public static Sound GAME_WIN;
    public static Sound GAME_OVER;
    public static Sound ADD_SCORE;
    public static Sound ADD_STAR;
    public static Sound ADD_COMBO;
    public static Sound ADD_BONUS;
    public static Sound COLLECT_STARFISH;
    public static Sound COLLECT_SHELL;

    public static Sound TILE_COMBO_01;
    public static Sound TILE_COMBO_02;
    public static Sound TILE_COMBO_03;
    public static Sound TILE_COMBO_04;
    public static Sound TILE_SWAP;
    public static Sound TILE_SLIDE;
    public static Sound TILE_SHUFFLE;
    public static Sound TILE_BOUNCE;
    public static Sound TILE_UPGRADE;

    public static Sound ICE_CREAM_UPGRADE;
    public static Sound ICE_CREAM_TRANSFORM;
    public static Sound ICE_CREAM_COMBINE;
    public static Sound ICE_CREAM_EXPLODE;
    public static Sound STRIPED_EXPLODE;
    public static Sound EXPLOSIVE_EXPLODE;
    public static Sound EXPLOSIVE_STRIPED_COMBINE;

    public static Sound COOKIE_EXPLODE;
    public static Sound CAKE_EXPLODE;
    public static Sound PIE_EXPLODE;
    public static Sound PIE_PAN_EXPLODE;
    public static Sound CANDY_EXPLODE;
    public static Sound CANDY_WRAPPER_EXPLODE;
    public static Sound ICE_EXPLODE;
    public static Sound LOCK_EXPLODE;
    public static Sound HONEY_EXPLODE;
    public static Sound SAND_EXPLODE;

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static void load(SoundManager soundManager) {
        BUTTON_CLICK = soundManager.load(R.raw.fruit_button_click);
        DIALOG_SLIDE = soundManager.load(R.raw.fruit_dialog_slide);
        WHEEL_SPIN = soundManager.load(R.raw.fruit_wheel_spin);

        GAME_START = soundManager.load(R.raw.fruit_game_start);
        GAME_WIN = soundManager.load(R.raw.fruit_game_win);
        GAME_OVER = soundManager.load(R.raw.fruit_game_over);
        ADD_SCORE = soundManager.load(R.raw.fruit_add_score);
        ADD_STAR = soundManager.load(R.raw.fruit_add_star);
        ADD_COMBO = soundManager.load(R.raw.fruit_add_combo);
        ADD_BONUS = soundManager.load(R.raw.fruit_add_bonus);
        COLLECT_STARFISH = soundManager.load(R.raw.fruit_collect_starfish);
        COLLECT_SHELL = soundManager.load(R.raw.fruit_collect_shell);

        TILE_COMBO_01 = soundManager.load(R.raw.fruit_tile_combo_01);
        TILE_COMBO_02 = soundManager.load(R.raw.fruit_tile_combo_02);
        TILE_COMBO_03 = soundManager.load(R.raw.fruit_tile_combo_03);
        TILE_COMBO_04 = soundManager.load(R.raw.fruit_tile_combo_04);
        TILE_SWAP = soundManager.load(R.raw.fruit_tile_swap);
        TILE_SLIDE = soundManager.load(R.raw.fruit_tile_slide);
        TILE_SHUFFLE = soundManager.load(R.raw.fruit_tile_shuffle);
        TILE_BOUNCE = soundManager.load(R.raw.fruit_tile_bounce);
        TILE_UPGRADE = soundManager.load(R.raw.fruit_tile_upgrade);

        ICE_CREAM_UPGRADE = soundManager.load(R.raw.fruit_ice_cream_upgrade);
        ICE_CREAM_TRANSFORM = soundManager.load(R.raw.fruit_ice_cream_transform);
        ICE_CREAM_COMBINE = soundManager.load(R.raw.fruit_ice_cream_combine);
        ICE_CREAM_EXPLODE = soundManager.load(R.raw.fruit_ice_cream_explode);
        STRIPED_EXPLODE = soundManager.load(R.raw.fruit_striped_explode);
        EXPLOSIVE_EXPLODE = soundManager.load(R.raw.fruit_explosive_explode);
        EXPLOSIVE_STRIPED_COMBINE = soundManager.load(R.raw.fruit_explosive_striped_combine);

        COOKIE_EXPLODE = soundManager.load(R.raw.fruit_cookie_explode);
        CAKE_EXPLODE = soundManager.load(R.raw.fruit_cake_explode);
        PIE_EXPLODE = soundManager.load(R.raw.fruit_pie_explode);
        PIE_PAN_EXPLODE = soundManager.load(R.raw.fruit_pie_pan_explode);
        CANDY_EXPLODE = soundManager.load(R.raw.fruit_candy_explode);
        CANDY_WRAPPER_EXPLODE = soundManager.load(R.raw.fruit_candy_wrapper_explode);
        ICE_EXPLODE = soundManager.load(R.raw.fruit_ice_explode);
        LOCK_EXPLODE = soundManager.load(R.raw.fruit_lock_explode);
        HONEY_EXPLODE = soundManager.load(R.raw.fruit_honey_explode);
        SAND_EXPLODE = soundManager.load(R.raw.fruit_sand_explode);
    }
    //========================================================

}
