package com.gamenative.fruitymatch.fruit_game.algorithm.target;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_CakeTile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_CakeTargetHandler implements Fruit_TargetHandler {

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean checkTarget(Fruit_Tile tile) {
        if (tile instanceof Fruit_CakeTile) {
            Fruit_CakeTile cake = ((Fruit_CakeTile) tile);
            return cake.isObstacle() && cake.getObstacleLayer() == 1;
        }
        return false;
    }
    //========================================================

}
