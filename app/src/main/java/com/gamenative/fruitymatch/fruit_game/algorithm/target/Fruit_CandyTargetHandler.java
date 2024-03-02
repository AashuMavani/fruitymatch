package com.gamenative.fruitymatch.fruit_game.algorithm.target;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_CandyTile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_CandyTargetHandler implements Fruit_TargetHandler {

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean checkTarget(Fruit_Tile tile) {
        if (tile instanceof Fruit_CandyTile) {
            Fruit_CandyTile candy = ((Fruit_CandyTile) tile);
            return candy.isObstacle() && candy.getObstacleLayer() == 1;
        }
        return false;
    }
    //========================================================

}
