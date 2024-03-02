package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.sand.Fruit_Sand;
import com.gamenative.fruitymatch.fruit_game.layer.sand.Fruit_SandSystem;
import com.gamenative.fruitymatch.fruit_game.layer.shell.Fruit_Shell;
import com.gamenative.fruitymatch.fruit_game.layer.shell.Fruit_ShellSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_level.Fruit_TargetType;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SandLayerHandler extends Fruit_BaseLayerHandler {

    private final Fruit_SandSystem mSandSystem;
    private final Fruit_ShellSystem mShellSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SandLayerHandler(Fruit_SandSystem sandSystem, Fruit_ShellSystem shellSystem) {
        mSandSystem = sandSystem;
        mShellSystem = shellSystem;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void updateLayer(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col) {
        super.updateLayer(targetHandlerManager, tiles, row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                updateShell(targetHandlerManager, t);
            }
        }
    }

    @Override
    protected void onInitLayer(Fruit_Tile tile) {
    }

    @Override
    protected void onUpdateLayer(Fruit_Tile tile, Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col) {
        if (tile.getTileState() != Fruit_TileState.MATCH) {
            return;
        }
        updateSand(tile);
    }

    @Override
    protected void onRemoveLayer(Fruit_Tile tile) {
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void updateSand(Fruit_Tile tile) {
        // Check is tile has sand
        Fruit_Sand sand = mSandSystem.getChildAt(tile.getRow(), tile.getColumn());
        if (sand != null && sand.isRunning()) {
            // Remove sand if tile is fruit or special tile
            if (tile.getTileType() != Fruit_FruitType.NONE || tile.getSpecialType() != Fruit_SpecialType.NONE) {
                sand.playSandEffect();
            }
        }
    }

    private void updateShell(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile tile) {
        int row = tile.getRow();
        int col = tile.getColumn();
        // Check is tile has shell
        Fruit_Shell shell = mShellSystem.getChildAt(row, col);
        if (shell != null && shell.isRunning()) {
            // Check is all the sand cleared
            int width = shell.getShellType().getWidth();
            int height = shell.getShellType().getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Fruit_Sand sand = mSandSystem.getChildAt(row + i, col + j);
                    if (sand != null && sand.isRunning()) {
                        return;
                    }
                }
            }
            // Update target and remove shell
            shell.playShellEffect();
            targetHandlerManager.updateTarget(Fruit_TargetType.SHELL);
        }
    }
    //========================================================

}
