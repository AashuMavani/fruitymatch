package com.gamenative.fruitymatch.fruit_game.layer.tile;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public enum Fruit_SpecialType {
    NONE,
    UPGRADE,
    ROW_STRIPED,
    COLUMN_STRIPED,
    EXPLOSIVE,
    ICE_CREAM;

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean hasPower() {
        return this != NONE && this != UPGRADE;
    }

    public boolean hasEffect() {
        return this != ICE_CREAM;
    }

    public Texture getTexture(Fruit_FruitType fruitType) {
        switch (this) {
            case ROW_STRIPED:
                return getRowStripedTileTexture(fruitType);
            case COLUMN_STRIPED:
                return getColumnStripedTileTexture(fruitType);
            case EXPLOSIVE:
                return getExplosiveTileTexture(fruitType);
            case ICE_CREAM:
                return Fruit_Textures.ICE_CREAM;
            default:
                throw new IllegalArgumentException("Special tile Texture not found!");
        }
    }

    public Texture[] getPiecesTexture(Fruit_FruitType fruitType) {
        switch (this) {
            case ROW_STRIPED:
                return getRowStripedTilePiecesTexture(fruitType);
            case COLUMN_STRIPED:
                return getColumnStripedTilePiecesTexture(fruitType);
            case EXPLOSIVE:
                return getExplosiveTilePiecesTexture(fruitType);
            default:
                throw new IllegalArgumentException("Special tile pieces Texture not found!");
        }
    }

    private Texture getRowStripedTileTexture(Fruit_FruitType fruitType) {
        switch (fruitType) {
            case CHERRY:
                return Fruit_Textures.ROW_STRIPED_CHERRY;
            case STRAWBERRY:
                return Fruit_Textures.ROW_STRIPED_STRAWBERRY;
            case LEMON:
                return Fruit_Textures.ROW_STRIPED_LEMON;
            case COCONUT:
                return Fruit_Textures.ROW_STRIPED_COCONUT;
            case BANANA:
                return Fruit_Textures.ROW_STRIPED_BANANA;
            default:
                throw new IllegalArgumentException("Row striped tile Texture not found!");
        }
    }

    private Texture getColumnStripedTileTexture(Fruit_FruitType fruitType) {
        switch (fruitType) {
            case CHERRY:
                return Fruit_Textures.COLUMN_STRIPED_CHERRY;
            case STRAWBERRY:
                return Fruit_Textures.COLUMN_STRIPED_STRAWBERRY;
            case LEMON:
                return Fruit_Textures.COLUMN_STRIPED_LEMON;
            case COCONUT:
                return Fruit_Textures.COLUMN_STRIPED_COCONUT;
            case BANANA:
                return Fruit_Textures.COLUMN_STRIPED_BANANA;
            default:
                throw new IllegalArgumentException("Column striped tile Texture not found!");
        }
    }

    private Texture getExplosiveTileTexture(Fruit_FruitType fruitType) {
        switch (fruitType) {
            case CHERRY:
                return Fruit_Textures.EXPLOSIVE_CHERRY;
            case STRAWBERRY:
                return Fruit_Textures.EXPLOSIVE_STRAWBERRY;
            case LEMON:
                return Fruit_Textures.EXPLOSIVE_LEMON;
            case COCONUT:
                return Fruit_Textures.EXPLOSIVE_COCONUT;
            case BANANA:
                return Fruit_Textures.EXPLOSIVE_BANANA;
            default:
                throw new IllegalArgumentException("Explosive tile Texture not found!");
        }
    }

    private Texture[] getRowStripedTilePiecesTexture(Fruit_FruitType fruitType) {
        switch (fruitType) {
            case CHERRY:
                return new Texture[]{Fruit_Textures.ROW_STRIPED_CHERRY_PIECE_01, Fruit_Textures.ROW_STRIPED_CHERRY_PIECE_02};
            case STRAWBERRY:
                return new Texture[]{Fruit_Textures.ROW_STRIPED_STRAWBERRY_PIECE_01, Fruit_Textures.ROW_STRIPED_STRAWBERRY_PIECE_02};
            case LEMON:
                return new Texture[]{Fruit_Textures.ROW_STRIPED_LEMON_PIECE_01, Fruit_Textures.ROW_STRIPED_LEMON_PIECE_02};
            case COCONUT:
                return new Texture[]{Fruit_Textures.ROW_STRIPED_COCONUT_PIECE_01, Fruit_Textures.ROW_STRIPED_COCONUT_PIECE_02};
            case BANANA:
                return new Texture[]{Fruit_Textures.ROW_STRIPED_BANANA_PIECE_01, Fruit_Textures.ROW_STRIPED_BANANA_PIECE_02};
            default:
                throw new IllegalArgumentException("Row striped tile pieces Texture not found!");
        }
    }

    private Texture[] getColumnStripedTilePiecesTexture(Fruit_FruitType fruitType) {
        switch (fruitType) {
            case CHERRY:
                return new Texture[]{Fruit_Textures.COLUMN_STRIPED_CHERRY_PIECE_01, Fruit_Textures.COLUMN_STRIPED_CHERRY_PIECE_02};
            case STRAWBERRY:
                return new Texture[]{Fruit_Textures.COLUMN_STRIPED_STRAWBERRY_PIECE_01, Fruit_Textures.COLUMN_STRIPED_STRAWBERRY_PIECE_02};
            case LEMON:
                return new Texture[]{Fruit_Textures.COLUMN_STRIPED_LEMON_PIECE_01, Fruit_Textures.COLUMN_STRIPED_LEMON_PIECE_02};
            case COCONUT:
                return new Texture[]{Fruit_Textures.COLUMN_STRIPED_COCONUT_PIECE_01, Fruit_Textures.COLUMN_STRIPED_COCONUT_PIECE_02};
            case BANANA:
                return new Texture[]{Fruit_Textures.COLUMN_STRIPED_BANANA_PIECE_01, Fruit_Textures.COLUMN_STRIPED_BANANA_PIECE_02};
            default:
                throw new IllegalArgumentException("Column striped tile pieces Texture not found!");
        }
    }

    private Texture[] getExplosiveTilePiecesTexture(Fruit_FruitType fruitType) {
        switch (fruitType) {
            case CHERRY:
                return new Texture[]{Fruit_Textures.EXPLOSIVE_CHERRY_PIECE_01, Fruit_Textures.EXPLOSIVE_CHERRY_PIECE_02};
            case STRAWBERRY:
                return new Texture[]{Fruit_Textures.EXPLOSIVE_STRAWBERRY_PIECE_01, Fruit_Textures.EXPLOSIVE_STRAWBERRY_PIECE_02};
            case LEMON:
                return new Texture[]{Fruit_Textures.EXPLOSIVE_LEMON_PIECE_01, Fruit_Textures.EXPLOSIVE_LEMON_PIECE_02};
            case COCONUT:
                return new Texture[]{Fruit_Textures.EXPLOSIVE_COCONUT_PIECE_01, Fruit_Textures.EXPLOSIVE_COCONUT_PIECE_02};
            case BANANA:
                return new Texture[]{Fruit_Textures.EXPLOSIVE_BANANA_PIECE_01, Fruit_Textures.EXPLOSIVE_BANANA_PIECE_02};
            default:
                throw new IllegalArgumentException("Explosive tile pieces Texture not found!");
        }
    }
    //========================================================

}
