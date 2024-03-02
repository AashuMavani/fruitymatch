package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_PipeTile extends Fruit_EmptyTile {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_PipeTile(Engine engine, Texture texture) {
        super(engine, texture);
        // We let the pipe on top of tile, so they will pass underneath
        mLayer = Fruit_GameLayer.TILE_LAYER + 1;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isNegligible() {
        // We neglect pipe when swap to top
        return true;
    }
    //========================================================

}
