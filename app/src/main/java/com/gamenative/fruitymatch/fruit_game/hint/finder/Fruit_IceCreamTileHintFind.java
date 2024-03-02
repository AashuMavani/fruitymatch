package com.gamenative.fruitymatch.fruit_game.hint.finder;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamTileHintFind implements Fruit_HintFinder {

    private final List<Fruit_Tile> mHintTiles = new ArrayList<>();

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public List<Fruit_Tile> findHint(Fruit_Tile[][] tiles, int row, int col) {
        // Clear the previous hint
        mHintTiles.clear();

        // Find color special tile and nearby tiles
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                // Find color special tile
                Fruit_Tile tile = tiles[i][j];
                if (tile.getSpecialType() == Fruit_SpecialType.ICE_CREAM
                        && tile.isSwappable()) {

                    // Check potential match
                    if (i > 0 && tiles[i - 1][j].isMatchable()
                            && tiles[i - 1][j].isSwappable()) {
                        mHintTiles.add(tile);
                        mHintTiles.add(tiles[i - 1][j]);
                        return mHintTiles;
                    }
                    if (i < row - 1 && tiles[i + 1][j].isMatchable()
                            && tiles[i + 1][j].isSwappable()) {
                        mHintTiles.add(tile);
                        mHintTiles.add(tiles[i + 1][j]);
                        return mHintTiles;
                    }
                    if (j > 0 && tiles[i][j - 1].isMatchable()
                            && tiles[i][j - 1].isSwappable()) {
                        mHintTiles.add(tile);
                        mHintTiles.add(tiles[i][j - 1]);
                        return mHintTiles;
                    }
                    if (j < col - 1 && tiles[i][j + 1].isMatchable()
                            && tiles[i][j + 1].isSwappable()) {
                        mHintTiles.add(tile);
                        mHintTiles.add(tiles[i][j + 1]);
                        return mHintTiles;
                    }
                }
            }
        }

        return mHintTiles;
    }
    //========================================================

}
