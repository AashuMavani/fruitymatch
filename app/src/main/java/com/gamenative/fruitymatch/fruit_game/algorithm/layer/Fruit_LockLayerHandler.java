package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.lock.Fruit_Lock;
import com.gamenative.fruitymatch.fruit_game.layer.lock.Fruit_LockSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_level.Fruit_TargetType;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LockLayerHandler extends Fruit_BaseLayerHandler {

    private final Fruit_LockSystem mLockSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_LockLayerHandler(Fruit_LockSystem lockSystem) {
        mLockSystem = lockSystem;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitLayer(Fruit_Tile tile) {
        Fruit_Lock lock = mLockSystem.getChildAt(tile.getRow(), tile.getColumn());
        if (lock != null && lock.isRunning()) {
            tile.setPoppable(false);
            tile.setSwappable(false);
            tile.setShufflable(false);
        }
    }

    @Override
    protected void onUpdateLayer(Fruit_Tile tile, Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col) {
        if (tile.getTileState() != Fruit_TileState.MATCH) {
            return;
        }
        updateLock(targetHandlerManager, tile);
    }

    @Override
    protected void onRemoveLayer(Fruit_Tile tile) {
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void updateLock(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile tile) {
        Fruit_Lock lock = mLockSystem.getChildAt(tile.getRow(), tile.getColumn());
        if (lock != null && lock.isRunning()) {
            lock.playLockEffect();
            tile.setPoppable(true);
            tile.setSwappable(true);
            tile.setShufflable(true);
            tile.setTileState(Fruit_TileState.IDLE);
            targetHandlerManager.updateTarget(Fruit_TargetType.LOCK);
        }
    }
    //========================================================

}
