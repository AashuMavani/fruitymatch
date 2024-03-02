package com.gamenative.fruitymatch.fruit_game.algorithm.special.handler;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface Fruit_SpecialTileHandler {

    void handleSpecialTile(Fruit_Tile[][] tiles, Fruit_Tile tile, int row, int col);

}
