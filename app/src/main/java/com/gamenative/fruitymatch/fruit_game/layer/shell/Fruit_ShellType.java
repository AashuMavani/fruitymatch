package com.gamenative.fruitymatch.fruit_game.layer.shell;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public enum Fruit_ShellType {
    SMALL_VERTICAL,
    SMALL_HORIZONTAL,
    MEDIUM,
    LARGE;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Texture getTexture() {
        switch (this) {
            case SMALL_VERTICAL:
                return Fruit_Textures.SHELL_01_VERTICAL;
            case SMALL_HORIZONTAL:
                return Fruit_Textures.SHELL_01_HORIZONTAL;
            case MEDIUM:
                return Fruit_Textures.SHELL_02;
            case LARGE:
                return Fruit_Textures.SHELL_03;
            default:
                throw new IllegalArgumentException("Shell Texture not found!");
        }
    }

    public int getWidth() {
        switch (this) {
            case SMALL_VERTICAL:
                return 1;
            case SMALL_HORIZONTAL:
                return 3;
            case MEDIUM:
                return 2;
            case LARGE:
                return 3;
            default:
                throw new IllegalArgumentException("Shell width not found!");
        }
    }

    public int getHeight() {
        switch (this) {
            case SMALL_VERTICAL:
                return 3;
            case SMALL_HORIZONTAL:
                return 1;
            case MEDIUM:
                return 2;
            case LARGE:
                return 3;
            default:
                throw new IllegalArgumentException("Shell height not found!");
        }
    }
    //========================================================

}
