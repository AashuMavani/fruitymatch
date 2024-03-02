package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_ObstacleTile extends Fruit_SolidTile {

    protected boolean mIsObstacle = true;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_ObstacleTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture, Fruit_FruitType.NONE);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isObstacle() {
        return mIsObstacle;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void popTile() {
        if (mIsObstacle) {
            mTileState = Fruit_TileState.MATCH;
            return;
        }
        super.popTile();
    }
    //========================================================

}
