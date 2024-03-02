package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ColumnStripedTileFinder extends Fruit_TripleSpecialTileFinder {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ColumnStripedTileFinder(Engine engine) {
        super(engine);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void findSpecialTile(Fruit_Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 3; j++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable()) {
                    continue;
                }

                // Check is tile type match the next three column
                Fruit_TileType type = tiles[i][j].getTileType();
                if (type == tiles[i][j + 1].getTileType()
                        && type == tiles[i][j + 2].getTileType()
                        && type == tiles[i][j + 3].getTileType()) {

                    // Check the tile player select
                    if (tiles[i][j + 2].isSelect()) {
                        setPositionXFactors(-2, -1, 1);
                        upgrade(tiles, i, j + 2);
                    } else {
                        setPositionXFactors(-1, 1, 2);
                        upgrade(tiles, i, j + 1);   // Default is left one
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
        // Left shape:  Right shape:
        // 0 X 0 0      0 0 X 0

        Fruit_Tile targetTile = tiles[row][col];
        // We make sure it is not already special tile
        if (targetTile.getSpecialType() != Fruit_SpecialType.NONE) {
            return;
        }
        // Set target tile special type
        targetTile.setSpecialType(Fruit_SpecialType.COLUMN_STRIPED);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
    }
    //========================================================

}
