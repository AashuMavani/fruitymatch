package com.gamenative.fruitymatch.fruit_game.algorithm.target;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_PieTile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_PieTargetHandler implements Fruit_TargetHandler {

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean checkTarget(Fruit_Tile tile) {
        if (tile instanceof Fruit_PieTile) {
            Fruit_PieTile pie = ((Fruit_PieTile) tile);
            return pie.isObstacle() && pie.getObstacleLayer() == 1;
        }
        return false;
    }
    //========================================================

}
