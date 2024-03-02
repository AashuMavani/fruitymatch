package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.ice.Fruit_Ice;
import com.gamenative.fruitymatch.fruit_game.layer.ice.Fruit_IceSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_level.Fruit_TargetType;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceLayerHandler extends Fruit_BaseLayerHandler {

    private final Fruit_IceSystem mIceSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceLayerHandler(Fruit_IceSystem iceSystem) {
        mIceSystem = iceSystem;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitLayer(Fruit_Tile tile) {
    }

    @Override
    protected void onUpdateLayer(Fruit_Tile tile, Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col) {
        if (tile.getTileState() != Fruit_TileState.MATCH) {
            return;
        }
        updateIce(targetHandlerManager, tile);
    }

    @Override
    protected void onRemoveLayer(Fruit_Tile tile) {
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void updateIce(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile tile) {
        // Check is tile has ice
        Fruit_Ice ice = mIceSystem.getChildAt(tile.getRow(), tile.getColumn());
        if (ice != null && ice.isRunning()) {
            // Remove ice if tile is fruit or special tile
            if (tile.getTileType() != Fruit_FruitType.NONE || tile.getSpecialType() != Fruit_SpecialType.NONE) {
                ice.playIceEffect();
                // Update target if all the ice layers being removed
                if (ice.getIceLayer() == 0) {
                    targetHandlerManager.updateTarget(Fruit_TargetType.ICE);
                }
            }
        }
    }
    //========================================================

}
