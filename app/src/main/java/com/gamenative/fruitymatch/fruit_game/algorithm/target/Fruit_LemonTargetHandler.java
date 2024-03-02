package com.gamenative.fruitymatch.fruit_game.algorithm.target;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LemonTargetHandler implements Fruit_TargetHandler {

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean checkTarget(Fruit_Tile tile) {
        return tile.getTileType() == Fruit_FruitType.LEMON;
    }
    //========================================================

}
