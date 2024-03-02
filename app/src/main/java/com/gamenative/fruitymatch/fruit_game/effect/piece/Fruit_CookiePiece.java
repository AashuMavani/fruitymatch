package com.gamenative.fruitymatch.fruit_game.effect.piece;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public enum Fruit_CookiePiece {
    TOP_LEFT,
    TOP,
    TOP_RIGHT,
    DOWN_LEFT,
    DOWN,
    DOWN_RIGHT;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Texture getTexture() {
        switch (this) {
            case TOP_LEFT:
                return Fruit_Textures.COOKIE_PIECE_01;
            case TOP:
                return Fruit_Textures.COOKIE_PIECE_02;
            case TOP_RIGHT:
                return Fruit_Textures.COOKIE_PIECE_03;
            case DOWN_LEFT:
                return Fruit_Textures.COOKIE_PIECE_04;
            case DOWN:
                return Fruit_Textures.COOKIE_PIECE_05;
            case DOWN_RIGHT:
                return Fruit_Textures.COOKIE_PIECE_06;
            default:
                throw new IllegalArgumentException("CookiePiece texture not found!");
        }
    }

    public int getDirectionX() {
        switch (this) {
            case TOP_LEFT:
            case DOWN_LEFT:
                return -1;
            case TOP:
            case DOWN:
                return 0;
            case TOP_RIGHT:
            case DOWN_RIGHT:
                return 1;
            default:
                throw new IllegalArgumentException("CookiePiece not found!");
        }
    }

    public int getDirectionY() {
        switch (this) {
            case TOP_LEFT:
            case TOP:
            case TOP_RIGHT:
                return -1;
            case DOWN:
            case DOWN_LEFT:
            case DOWN_RIGHT:
                return 1;
            default:
                throw new IllegalArgumentException("CookiePiece not found!");
        }
    }
    //========================================================

}
