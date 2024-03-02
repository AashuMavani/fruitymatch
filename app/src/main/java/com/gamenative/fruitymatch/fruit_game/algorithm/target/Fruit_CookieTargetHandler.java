package com.gamenative.fruitymatch.fruit_game.algorithm.target;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_CookieTile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_CookieTargetHandler implements Fruit_TargetHandler {

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean checkTarget(Fruit_Tile tile) {
        if (tile instanceof Fruit_CookieTile) {
            Fruit_CookieTile cookie = ((Fruit_CookieTile) tile);
            return cookie.isObstacle();
        }
        return false;
    }
    //========================================================

}
