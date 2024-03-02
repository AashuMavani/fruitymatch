package com.gamenative.fruitymatch.fruit_game.hint.finder;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SpecialCombineHintFinder implements Fruit_HintFinder {

    private final List<Fruit_Tile> mHintTiles = new ArrayList<>();

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public List<Fruit_Tile> findHint(Fruit_Tile[][] tiles, int row, int col) {
        // Clear the previous hint
        mHintTiles.clear();

        // Find two consecutive special tiles in row
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 1; j++) {
                Fruit_Tile tileA = tiles[i][j];
                Fruit_Tile tileB = tiles[i][j + 1];
                if (tileA.isSwappable() && tileB.isSwappable()
                        && tileA.getSpecialType() != Fruit_SpecialType.NONE
                        && tileB.getSpecialType() != Fruit_SpecialType.NONE) {
                    mHintTiles.add(tileA);
                    mHintTiles.add(tileB);
                    return mHintTiles;
                }
            }
        }

        // Find two consecutive special tiles in column
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile tileA = tiles[i][j];
                Fruit_Tile tileB = tiles[i + 1][j];
                if (tileA.isSwappable() && tileB.isSwappable()
                        && tileA.getSpecialType() != Fruit_SpecialType.NONE
                        && tileB.getSpecialType() != Fruit_SpecialType.NONE) {
                    mHintTiles.add(tileA);
                    mHintTiles.add(tileB);
                    return mHintTiles;
                }
            }
        }

        return mHintTiles;
    }
    //========================================================

}
