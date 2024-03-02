package com.gamenative.fruitymatch.fruit_game.layer.tile;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public enum Fruit_FruitType implements Fruit_TileType {
    CHERRY,
    STRAWBERRY,
    LEMON,
    COCONUT,
    BANANA,
    NONE;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean hasEffect() {
        return this != NONE;
    }

    public Texture getTexture() {
        switch (this) {
            case CHERRY:
                return Fruit_Textures.CHERRY;
            case STRAWBERRY:
                return Fruit_Textures.STRAWBERRY;
            case LEMON:
                return Fruit_Textures.LEMON;
            case COCONUT:
                return Fruit_Textures.COCONUT;
            case BANANA:
                return Fruit_Textures.BANANA;
            default:
                throw new IllegalArgumentException("Fruit Texture not found!");
        }
    }

    public Texture[] getPiecesTexture() {
        switch (this) {
            case CHERRY:
                return new Texture[]{Fruit_Textures.CHERRY_PIECE_01, Fruit_Textures.CHERRY_PIECE_02};
            case STRAWBERRY:
                return new Texture[]{Fruit_Textures.STRAWBERRY_PIECE_01, Fruit_Textures.STRAWBERRY_PIECE_02};
            case LEMON:
                return new Texture[]{Fruit_Textures.LEMON_PIECE_01, Fruit_Textures.LEMON_PIECE_02};
            case COCONUT:
                return new Texture[]{Fruit_Textures.COCONUT_PIECE_01, Fruit_Textures.COCONUT_PIECE_02};
            case BANANA:
                return new Texture[]{Fruit_Textures.BANANA_PIECE_01, Fruit_Textures.BANANA_PIECE_02};
            default:
                throw new IllegalArgumentException("Fruit pieces Texture not found!");
        }
    }

    public Texture getScoreTexture() {
        switch (this) {
            case CHERRY:
                return Fruit_Textures.SCORE_PINK;
            case STRAWBERRY:
                return Fruit_Textures.SCORE_RED;
            case LEMON:
                return Fruit_Textures.SCORE_YELLOW;
            case COCONUT:
                return Fruit_Textures.SCORE_BROWN;
            case BANANA:
                return Fruit_Textures.SCORE_WHITE;
            default:
                throw new IllegalArgumentException("Fruit score Texture not found!");
        }
    }
    //========================================================

}
