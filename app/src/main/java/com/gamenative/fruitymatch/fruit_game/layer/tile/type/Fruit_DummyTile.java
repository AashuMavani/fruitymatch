package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_DummyTile extends Fruit_ObstacleTile {

    private Fruit_Tile mTargetTile;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_DummyTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture);
        setActive(false);
        setVisible(false);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_Tile getTargetTile() {
        return mTargetTile;
    }

    public void setTargetTile(Fruit_Tile targetTile) {
        mTargetTile = targetTile;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isSwappable() {
        if (mIsObstacle) {
            return false;
        }
        return super.isSwappable();
    }

    @Override
    public void popTile() {
        if (mIsObstacle) {
            // We shift the popTile call to the target tile
            mTargetTile.popTile();
            return;
        }
        super.popTile();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void popDummyTile() {
        // Remove the dummy effect
        mTileState = Fruit_TileState.MATCH;
        mIsObstacle = false;
        setActive(true);
        setVisible(true);
    }
    //========================================================

}
