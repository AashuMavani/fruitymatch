package com.gamenative.fruitymatch.fruit_game.hint.finder;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface Fruit_HintFinder {

    List<Fruit_Tile> findHint(Fruit_Tile[][] tiles, int row, int col);

}
