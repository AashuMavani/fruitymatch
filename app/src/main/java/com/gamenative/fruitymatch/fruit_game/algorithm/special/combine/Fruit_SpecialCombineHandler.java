package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface Fruit_SpecialCombineHandler {

    long getStartDelay();

    boolean checkSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col);

}
