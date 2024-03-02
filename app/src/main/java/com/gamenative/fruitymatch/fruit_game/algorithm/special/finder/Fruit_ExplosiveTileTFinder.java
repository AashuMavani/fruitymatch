package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosiveTileTFinder extends Fruit_QuadSpecialTileFinder {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosiveTileTFinder(Engine engine) {
        super(engine);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void findSpecialTile(Fruit_Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 2; j++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable()) {
                    continue;
                }

                // Check is tile type match the next two column
                Fruit_TileType type = tiles[i][j].getTileType();
                if (type == tiles[i][j + 1].getTileType()
                        && type == tiles[i][j + 2].getTileType()) {
                    // We make sure the index not out of bound
                    if (i > 1) {
                        checkTop(tiles, i, j + 1);
                    }
                    if (i < row - 2) {
                        checkDown(tiles, i, j + 1);
                    }
                    if (i > 0 && i < row - 1) {
                        checkLeft(tiles, i, j);
                        checkRight(tiles, i, j + 2);
                    }
                }
            }
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void checkTop(Fruit_Tile[][] tiles, int row, int col) {
        // Top shape:
        //   0
        //   0
        // 0 X 0
        Fruit_TileType type = tiles[row][col].getTileType();
        if (type == tiles[row - 1][col].getTileType()
                && type == tiles[row - 2][col].getTileType()) {
            // Set target tile special type
            Fruit_Tile targetTile = tiles[row][col];
            // We make sure it is not already special tile
            if (targetTile.getSpecialType() != Fruit_SpecialType.NONE) {
                return;
            }
            targetTile.setSpecialType(Fruit_SpecialType.EXPLOSIVE);

            // Init position factors
            setPositionXFactors(-1, 1, 0, 0);
            setPositionYFactors(0, 0, -1, -2);
            // Set upgrade to others
            setUpgradeTiles(tiles, row, col);

            playUpgradeEffect(targetTile);
        }
    }

    private void checkDown(Fruit_Tile[][] tiles, int row, int col) {
        // Down shape:
        // 0 X 0
        //   0
        //   0
        Fruit_TileType type = tiles[row][col].getTileType();
        if (type == tiles[row + 1][col].getTileType()
                && type == tiles[row + 2][col].getTileType()) {
            // Set target tile special type
            Fruit_Tile targetTile = tiles[row][col];
            // We make sure it is not already special tile
            if (targetTile.getSpecialType() != Fruit_SpecialType.NONE) {
                return;
            }
            targetTile.setSpecialType(Fruit_SpecialType.EXPLOSIVE);

            // Init position factors
            setPositionXFactors(-1, 1, 0, 0);
            setPositionYFactors(0, 0, 1, 2);
            // Set upgrade to others
            setUpgradeTiles(tiles, row, col);

            playUpgradeEffect(targetTile);
        }
    }

    private void checkLeft(Fruit_Tile[][] tiles, int row, int col) {
        // Left shape:
        // 0
        // X 0 0
        // 0
        Fruit_TileType type = tiles[row][col].getTileType();
        if (type == tiles[row - 1][col].getTileType()
                && type == tiles[row + 1][col].getTileType()) {
            // Set target tile special type
            Fruit_Tile targetTile = tiles[row][col];
            // We make sure it is not already special tile
            if (targetTile.getSpecialType() != Fruit_SpecialType.NONE) {
                return;
            }
            targetTile.setSpecialType(Fruit_SpecialType.EXPLOSIVE);

            // Init position factors
            setPositionXFactors(0, 0, 1, 2);
            setPositionYFactors(-1, 1, 0, 0);
            // Set upgrade to others
            setUpgradeTiles(tiles, row, col);

            playUpgradeEffect(targetTile);
        }
    }

    private void checkRight(Fruit_Tile[][] tiles, int row, int col) {
        // Right shape:
        //     0
        // 0 0 X
        //     0
        Fruit_TileType type = tiles[row][col].getTileType();
        if (type == tiles[row - 1][col].getTileType()
                && type == tiles[row + 1][col].getTileType()) {
            // Set target tile special type
            Fruit_Tile targetTile = tiles[row][col];
            // We make sure it is not already special tile
            if (targetTile.getSpecialType() != Fruit_SpecialType.NONE) {
                return;
            }
            targetTile.setSpecialType(Fruit_SpecialType.EXPLOSIVE);

            // Init position factors
            setPositionXFactors(-2, -1, 0, 0);
            setPositionYFactors(0, 0, -1, 1);
            // Set upgrade to others
            setUpgradeTiles(tiles, row, col);

            playUpgradeEffect(targetTile);
        }
    }
    //========================================================

}
