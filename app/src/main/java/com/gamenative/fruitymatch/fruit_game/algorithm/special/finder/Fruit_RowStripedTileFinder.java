package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_RowStripedTileFinder extends Fruit_TripleSpecialTileFinder {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_RowStripedTileFinder(Engine engine) {
        super(engine);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void findSpecialTile(Fruit_Tile[][] tiles, int row, int col) {
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row - 3; i++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable()) {
                    continue;
                }

                // Check is tile type match the next three row
                Fruit_TileType type = tiles[i][j].getTileType();
                if (type == tiles[i + 1][j].getTileType()
                        && type == tiles[i + 2][j].getTileType()
                        && type == tiles[i + 3][j].getTileType()) {

                    // Check the tile player select
                    if (tiles[i + 2][j].isSelect()) {
                        setPositionYFactors(-2, -1, 1);
                        upgrade(tiles, i + 2, j);
                    } else {
                        setPositionYFactors(-1, 1, 2);
                        upgrade(tiles, i + 1, j);   // Default is upper one
                    }
                }
            }
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void upgrade(Fruit_Tile[][] tiles, int row, int col) {
        // Up shape:  Down shape:
        // 0          0
        // X          0
        // 0          X
        // 0          0

        Fruit_Tile targetTile = tiles[row][col];
        // We make sure it is not already special tile
        if (targetTile.getSpecialType() != Fruit_SpecialType.NONE) {
            return;
        }
        // Set target tile special type
        targetTile.setSpecialType(Fruit_SpecialType.ROW_STRIPED);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
    }
    //========================================================

}
