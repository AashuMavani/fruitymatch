package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.entrypoint.Fruit_EntryPoint;
import com.gamenative.fruitymatch.fruit_game.layer.entrypoint.Fruit_EntryPointSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_StarfishTile;
import com.gamenative.fruitymatch.fruit_level.Fruit_TargetType;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_EntryPointLayerHandler extends Fruit_BaseLayerHandler {

    private final Fruit_EntryPointSystem mEntryPointSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_EntryPointLayerHandler(Fruit_EntryPointSystem entryPointSystem) {
        mEntryPointSystem = entryPointSystem;
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
        if (tile instanceof Fruit_StarfishTile) {
            Fruit_StarfishTile starfish = ((Fruit_StarfishTile) tile);
            if (starfish.isStarfish()) {
                updateStarfish(targetHandlerManager, starfish);
            }
        }
    }

    @Override
    protected void onRemoveLayer(Fruit_Tile tile) {
        // Remove starfish when layer removed
        if (tile instanceof Fruit_StarfishTile) {
            Fruit_StarfishTile starfish = ((Fruit_StarfishTile) tile);
            if (!starfish.isStarfish()) {
                return;
            }
            starfish.popStarfishTile();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void updateStarfish(Fruit_TargetHandlerManager targetHandlerManager, Fruit_StarfishTile starfish) {
        // Check is starfish at entry point
        Fruit_EntryPoint entryPoint = mEntryPointSystem.getChildAt(starfish.getRow(), starfish.getColumn());
        if (entryPoint != null && entryPoint.isRunning()) {
            // Remove starfish and update target
            starfish.popStarfishTile();
            targetHandlerManager.updateTarget(Fruit_TargetType.STARFISH);
        }
    }
    //========================================================

}
