package com.gamenative.fruitymatch.fruit_game.hint.finder;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_TileHintFinder implements Fruit_HintFinder {

    private final List<Fruit_Tile> mHintTiles = new ArrayList<>();

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public List<Fruit_Tile> findHint(Fruit_Tile[][] tiles, int row, int col) {
        // Clear the previous hint
        mHintTiles.clear();

        // Find three consecutive same tiles in column
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col; j++) {

                // Find consecutive same type
                Fruit_TileType type = tiles[i][j].getTileType();
                if (tiles[i][j].isMatchable()
                        && type == tiles[i + 1][j].getTileType()) {

                    // Check potential match
                    if (i < row - 2 && j > 0
                            && type == tiles[i + 2][j - 1].getTileType()
                            && tiles[i + 2][j - 1].isSwappable()
                            && tiles[i + 2][j].isSwappable()) {
                        //  O
                        //  O
                        // O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 1][j]);
                        mHintTiles.add(tiles[i + 2][j - 1]);
                        return mHintTiles;
                    }
                    if (i < row - 2 && j < col - 1
                            && type == tiles[i + 2][j + 1].getTileType()
                            && tiles[i + 2][j + 1].isSwappable()
                            && tiles[i + 2][j].isSwappable()) {
                        // O
                        // O
                        //  O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 1][j]);
                        mHintTiles.add(tiles[i + 2][j + 1]);
                        return mHintTiles;
                    }
                    if (i > 0 && j > 0
                            && type == tiles[i - 1][j - 1].getTileType()
                            && tiles[i - 1][j - 1].isSwappable()
                            && tiles[i - 1][j].isSwappable()) {
                        // O
                        //  O
                        //  O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 1][j]);
                        mHintTiles.add(tiles[i - 1][j - 1]);
                        return mHintTiles;
                    }
                    if (i > 0 && j < col - 1
                            && type == tiles[i - 1][j + 1].getTileType()
                            && tiles[i - 1][j + 1].isSwappable()
                            && tiles[i - 1][j].isSwappable()) {
                        //  O
                        // O
                        // O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 1][j]);
                        mHintTiles.add(tiles[i - 1][j + 1]);
                        return mHintTiles;
                    }
                }
            }
        }
        for (int i = 0; i < row - 2; i++) {
            for (int j = 0; j < col; j++) {

                // Find consecutive same type
                Fruit_TileType type = tiles[i][j].getTileType();
                if (tiles[i][j].isMatchable()
                        && type == tiles[i + 2][j].getTileType()) {

                    // Check potential match
                    if (j > 0 && type == tiles[i + 1][j - 1].getTileType()
                            && tiles[i + 1][j - 1].isSwappable()
                            && tiles[i + 1][j].isSwappable()) {
                        //  O
                        // O
                        //  O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 1][j - 1]);
                        mHintTiles.add(tiles[i + 2][j]);
                        return mHintTiles;
                    }
                    if (j < col - 1 && type == tiles[i + 1][j + 1].getTileType()
                            && tiles[i + 1][j + 1].isSwappable()
                            && tiles[i + 1][j].isSwappable()) {
                        // O
                        //  O
                        // O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 1][j + 1]);
                        mHintTiles.add(tiles[i + 2][j]);
                        return mHintTiles;
                    }
                    if (i < row - 3 && type == tiles[i + 3][j].getTileType()
                            && tiles[i][j].isSwappable()
                            && tiles[i + 1][j].isSwappable()) {
                        // O
                        //
                        // O
                        // O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 2][j]);
                        mHintTiles.add(tiles[i + 3][j]);
                        return mHintTiles;
                    }
                    if (i > 0 && type == tiles[i - 1][j].getTileType()
                            && tiles[i + 1][j].isSwappable()
                            && tiles[i + 2][j].isSwappable()) {
                        // O
                        // O
                        //
                        // O
                        mHintTiles.add(tiles[i - 1][j]);
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 2][j]);
                        return mHintTiles;
                    }
                }
            }
        }

        // Find three consecutive same tiles in row
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 1; j++) {

                // Find consecutive same type
                Fruit_TileType type = tiles[i][j].getTileType();
                if (tiles[i][j].isMatchable()
                        && type == tiles[i][j + 1].getTileType()) {

                    // Check potential match
                    if (i > 0 && j < col - 2
                            && type == tiles[i - 1][j + 2].getTileType()
                            && tiles[i - 1][j + 2].isSwappable()
                            && tiles[i][j + 2].isSwappable()) {
                        //   O
                        // OO
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i][j + 1]);
                        mHintTiles.add(tiles[i - 1][j + 2]);
                        return mHintTiles;
                    }
                    if (i < row - 1 && j < col - 2
                            && type == tiles[i + 1][j + 2].getTileType()
                            && tiles[i + 1][j + 2].isSwappable()
                            && tiles[i][j + 2].isSwappable()) {
                        // OO
                        //   O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i][j + 1]);
                        mHintTiles.add(tiles[i + 1][j + 2]);
                        return mHintTiles;
                    }
                    if (i > 0 && j > 0
                            && type == tiles[i - 1][j - 1].getTileType()
                            && tiles[i - 1][j - 1].isSwappable()
                            && tiles[i][j - 1].isSwappable()) {
                        // O
                        //  OO
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i][j + 1]);
                        mHintTiles.add(tiles[i - 1][j - 1]);
                        return mHintTiles;
                    }
                    if (i < row - 1 && j > 0
                            && type == tiles[i + 1][j - 1].getTileType()
                            && tiles[i + 1][j - 1].isSwappable()
                            && tiles[i][j - 1].isSwappable()) {
                        //  OO
                        // O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i][j + 1]);
                        mHintTiles.add(tiles[i + 1][j - 1]);
                        return mHintTiles;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 2; j++) {

                // Find consecutive same type
                Fruit_TileType type = tiles[i][j].getTileType();
                if (tiles[i][j].isMatchable()
                        && type == tiles[i][j + 2].getTileType()) {

                    // Check potential match
                    if (i > 0 && type == tiles[i - 1][j + 1].getTileType()
                            && tiles[i - 1][j + 1].isSwappable()
                            && tiles[i][j + 1].isSwappable()) {
                        //  O
                        // O O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i - 1][j + 1]);
                        mHintTiles.add(tiles[i][j + 2]);
                        return mHintTiles;
                    }
                    if (i < row - 1 && type == tiles[i + 1][j + 1].getTileType()
                            && tiles[i + 1][j + 1].isSwappable()
                            && tiles[i][j + 1].isSwappable()) {
                        // O O
                        //  O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i + 1][j + 1]);
                        mHintTiles.add(tiles[i][j + 2]);
                        return mHintTiles;
                    }
                    if (j < col - 3 && type == tiles[i][j + 3].getTileType()
                            && tiles[i][j].isSwappable()
                            && tiles[i][j + 1].isSwappable()) {
                        // O OO
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i][j + 2]);
                        mHintTiles.add(tiles[i][j + 3]);
                        return mHintTiles;
                    }
                    if (j > 0 && type == tiles[i][j - 1].getTileType()
                            && tiles[i][j + 2].isSwappable()
                            && tiles[i][j + 1].isSwappable()) {
                        // OO O
                        mHintTiles.add(tiles[i][j]);
                        mHintTiles.add(tiles[i][j + 2]);
                        mHintTiles.add(tiles[i][j - 1]);
                        return mHintTiles;
                    }
                }
            }
        }

        return mHintTiles;
    }
    //========================================================

}
