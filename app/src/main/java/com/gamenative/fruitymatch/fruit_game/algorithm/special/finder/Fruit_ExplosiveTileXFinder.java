package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExplosiveTileXFinder extends Fruit_QuadSpecialTileFinder {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExplosiveTileXFinder(Engine engine) {
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
                    if (i > 0 && i < row - 1) {
                        checkCenter(tiles, i, j + 1);
                    }
                }
            }
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void checkCenter(Fruit_Tile[][] tiles, int row, int col) {
        // Center shape:
        //   0
        // 0 X 0
        //   0
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
            setPositionXFactors(0, 0, -1, 1);
            setPositionYFactors(-1, 1, 0, 0);
            // Set upgrade to others
            setUpgradeTiles(tiles, row, col);

            playUpgradeEffect(targetTile);
        }
    }
    //========================================================

}