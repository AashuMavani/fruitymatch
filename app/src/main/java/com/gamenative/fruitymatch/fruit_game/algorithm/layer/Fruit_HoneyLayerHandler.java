package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.honey.Fruit_Honey;
import com.gamenative.fruitymatch.fruit_game.layer.honey.Fruit_HoneySystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_level.Fruit_TargetType;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HoneyLayerHandler extends Fruit_BaseLayerHandler {

    private final Fruit_HoneySystem mHoneySystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_HoneyLayerHandler(Fruit_HoneySystem honeySystem) {
        mHoneySystem = honeySystem;
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
        updateHoney(targetHandlerManager, tiles, row, col, tile);
    }

    @Override
    protected void onRemoveLayer(Fruit_Tile tile) {
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void updateHoney(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col, Fruit_Tile tile) {
        Fruit_Honey honey = mHoneySystem.getChildAt(tile.getRow(), tile.getColumn());
        if (honey != null && honey.isRunning()) {
            // Remove ice if tile is fruit or special tile
            if (tile.getTileType() != Fruit_FruitType.NONE || tile.getSpecialType() != Fruit_SpecialType.NONE) {
                dfs(targetHandlerManager, tiles, row, col, tile);
            }
        }
    }

    private void dfs(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col, Fruit_Tile tile) {
        int tileRow = tile.getRow();
        int tileCol = tile.getColumn();
        // Check upper tile
        if (tileRow > 0) {
            Fruit_Tile upperTile = tiles[tileRow - 1][tileCol];
            if (upperTile.getTileState() == Fruit_TileState.MATCH) {
                if (upperTile.getTileType() != Fruit_FruitType.NONE || upperTile.getSpecialType() != Fruit_SpecialType.NONE) {
                    Fruit_Honey honey = mHoneySystem.getChildAt(tileRow - 1, tileCol);
                    if (honey != null && !honey.isRunning()) {
                        honey.playHoneyEffect();
                        targetHandlerManager.updateTarget(Fruit_TargetType.HONEY);
                        dfs(targetHandlerManager, tiles, row, col, upperTile);
                    }
                }
            }
        }
        // Check down tile
        if (tileRow < row - 1) {
            Fruit_Tile downTile = tiles[tileRow + 1][tileCol];
            if (downTile.getTileState() == Fruit_TileState.MATCH) {
                if (downTile.getTileType() != Fruit_FruitType.NONE || downTile.getSpecialType() != Fruit_SpecialType.NONE) {
                    Fruit_Honey honey = mHoneySystem.getChildAt(tileRow + 1, tileCol);
                    if (honey != null && !honey.isRunning()) {
                        honey.playHoneyEffect();
                        targetHandlerManager.updateTarget(Fruit_TargetType.HONEY);
                        dfs(targetHandlerManager, tiles, row, col, downTile);
                    }
                }
            }
        }
        // Check left tile
        if (tileCol > 0) {
            Fruit_Tile leftTile = tiles[tileRow][tileCol - 1];
            if (leftTile.getTileState() == Fruit_TileState.MATCH) {
                if (leftTile.getTileType() != Fruit_FruitType.NONE || leftTile.getSpecialType() != Fruit_SpecialType.NONE) {
                    Fruit_Honey honey = mHoneySystem.getChildAt(tileRow, tileCol - 1);
                    if (honey != null && !honey.isRunning()) {
                        honey.playHoneyEffect();
                        targetHandlerManager.updateTarget(Fruit_TargetType.HONEY);
                        dfs(targetHandlerManager, tiles, row, col, leftTile);
                    }
                }
            }
        }
        // Check right tile
        if (tileCol < col - 1) {
            Fruit_Tile rightTile = tiles[tileRow][tileCol + 1];
            if (rightTile.getTileState() == Fruit_TileState.MATCH) {
                if (rightTile.getTileType() != Fruit_FruitType.NONE || rightTile.getSpecialType() != Fruit_SpecialType.NONE) {
                    Fruit_Honey honey = mHoneySystem.getChildAt(tileRow, tileCol + 1);
                    if (honey != null && !honey.isRunning()) {
                        honey.playHoneyEffect();
                        targetHandlerManager.updateTarget(Fruit_TargetType.HONEY);
                        dfs(targetHandlerManager, tiles, row, col, rightTile);
                    }
                }
            }
        }
    }
    //========================================================

}
