package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface Fruit_SpecialTileFinder {

    void findSpecialTile(Fruit_Tile[][] tiles, int row, int col);

}
