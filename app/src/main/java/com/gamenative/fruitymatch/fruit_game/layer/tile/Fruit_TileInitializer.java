package com.gamenative.fruitymatch.fruit_game.layer.tile;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_CakeTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_CandyTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_CookieTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_DummyTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_EmptyTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_PieTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_PipeTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_SolidTile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_StarfishTile;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.util.RandomUtils;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_TileInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_Tile createTile(Fruit_TileSystem tileSystem, Engine engine, int row, int col, char c) {
        switch (c) {
            case 'e':
                return new Fruit_EmptyTile(engine, Fruit_Textures.EMPTY);
            case 'p':
                return new Fruit_PipeTile(engine, Fruit_Textures.PIPE);
            case 'x':
                return new Fruit_StarfishTile(tileSystem, engine, Fruit_Textures.STARFISH);
            case 'n':
                Fruit_FruitType fruitType = getShuffledRandomType(tileSystem, row, col);
                return new Fruit_SolidTile(tileSystem, engine, fruitType.getTexture(), fruitType);
            case 'w':
                return new Fruit_SolidTile(tileSystem, engine, Fruit_Textures.EMPTY, Fruit_FruitType.NONE, Fruit_TileState.UNREACHABLE);
            case 's':
                return new Fruit_SolidTile(tileSystem, engine, Fruit_Textures.STRAWBERRY, Fruit_FruitType.STRAWBERRY);
            case 'l':
                return new Fruit_SolidTile(tileSystem, engine, Fruit_Textures.LEMON, Fruit_FruitType.LEMON);
            case 'h':
                return new Fruit_SolidTile(tileSystem, engine, Fruit_Textures.CHERRY, Fruit_FruitType.CHERRY);
            case 'o':
                return new Fruit_SolidTile(tileSystem, engine, Fruit_Textures.COCONUT, Fruit_FruitType.COCONUT);
            case 'c':
                return new Fruit_CookieTile(tileSystem, engine, Fruit_Textures.COOKIE);
            case 'd':
                return new Fruit_CandyTile(tileSystem, engine, Fruit_Textures.CANDY_02, 2);
            case '1':
                return new Fruit_CakeTile(tileSystem, engine, Fruit_Textures.CAKE_01, 1);
            case '2':
                return new Fruit_CakeTile(tileSystem, engine, Fruit_Textures.CAKE_02, 2);
            case '3':
                return new Fruit_CakeTile(tileSystem, engine, Fruit_Textures.CAKE_03, 3);
            case '6':
                return new Fruit_PieTile(tileSystem, engine, Fruit_Textures.EMPTY, 1);
            case '7':
                return new Fruit_PieTile(tileSystem, engine, Fruit_Textures.EMPTY, 2);
            case '8':
                return new Fruit_PieTile(tileSystem, engine, Fruit_Textures.EMPTY, 3);
            case '9':
                return new Fruit_PieTile(tileSystem, engine, Fruit_Textures.EMPTY, 4);
            case '0':
                return new Fruit_DummyTile(tileSystem, engine, Fruit_Textures.EMPTY);
            case 'R':
                Fruit_FruitType rowSpecialFruitType = getShuffledRandomType(tileSystem, row, col);
                return new Fruit_SolidTile(tileSystem, engine, Fruit_SpecialType.ROW_STRIPED.getTexture(rowSpecialFruitType),
                        rowSpecialFruitType, Fruit_SpecialType.ROW_STRIPED);
            case 'C':
                Fruit_FruitType columnSpecialFruitType = getShuffledRandomType(tileSystem, row, col);
                return new Fruit_SolidTile(tileSystem, engine, Fruit_SpecialType.COLUMN_STRIPED.getTexture(columnSpecialFruitType),
                        columnSpecialFruitType, Fruit_SpecialType.COLUMN_STRIPED);
            case 'E':
                Fruit_FruitType explosionSpecialFruitType = getShuffledRandomType(tileSystem, row, col);
                return new Fruit_SolidTile(tileSystem, engine, Fruit_SpecialType.EXPLOSIVE.getTexture(explosionSpecialFruitType),
                        explosionSpecialFruitType, Fruit_SpecialType.EXPLOSIVE);
            case 'I':
                return new Fruit_SolidTile(tileSystem, engine, Fruit_Textures.ICE_CREAM, Fruit_FruitType.NONE, Fruit_SpecialType.ICE_CREAM);
            default:
                throw new IllegalArgumentException("Tile not found!");
        }
    }

    public static Fruit_FruitType getShuffledRandomType(Fruit_TileSystem tileSystem, int row, int col) {
        Fruit_FruitType type;
        do {
            type = getRandomType();
            // Reset type if match detected
        } while ((row >= 2 && tileSystem.getChildAt(row - 1, col).getTileType() == type
                && tileSystem.getChildAt(row - 2, col).getTileType() == type)
                || (col >= 2 && tileSystem.getChildAt(row, col - 1).getTileType() == type
                && tileSystem.getChildAt(row, col - 2).getTileType() == type));

        return type;
    }

    public static Fruit_FruitType getRandomType() {
        return getRandomType(Fruit_Level.LEVEL_DATA.getFruitCount());
    }

    public static Fruit_FruitType getRandomType(int fruitCount) {
        int i = RandomUtils.nextInt(fruitCount);
        switch (i) {
            case 0:
                return Fruit_FruitType.CHERRY;
            case 1:
                return Fruit_FruitType.STRAWBERRY;
            case 2:
                return Fruit_FruitType.LEMON;
            case 3:
                return Fruit_FruitType.COCONUT;
            case 4:
                return RandomUtils.nextFloat(1) < 0.35f ? Fruit_FruitType.BANANA : getRandomType(4);
            default:
                throw new IllegalArgumentException("FruitType not found!");
        }
    }
    //========================================================

}
