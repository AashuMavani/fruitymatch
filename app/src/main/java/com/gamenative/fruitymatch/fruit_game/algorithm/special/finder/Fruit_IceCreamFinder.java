package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_TransformFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamFinder extends Fruit_QuadSpecialTileFinder {

    private final Fruit_TransformFlashEffectSystem mTransformFlashEffectSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamFinder(Engine engine) {
        super(engine);
        mTransformFlashEffectSystem = new Fruit_TransformFlashEffectSystem(engine, MAX_FIND_NUM);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void findSpecialTile(Fruit_Tile[][] tiles, int row, int col) {
        // Find color special tile in row
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 4; j++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable()) {
                    continue;
                }

                // Check is tile type match the next four column
                Fruit_TileType type = tiles[i][j].getTileType();
                if (type == tiles[i][j + 1].getTileType()
                        && type == tiles[i][j + 2].getTileType()
                        && type == tiles[i][j + 3].getTileType()
                        && type == tiles[i][j + 4].getTileType()) {
                    upgradeRow(tiles, i, j + 2);
                }
            }
        }

        // Find color special tile in column
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row - 4; i++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable() || tiles[i][j].getTileState() != Fruit_TileState.MATCH) {
                    continue;
                }

                // Check is tile type match the next four row
                Fruit_TileType type = tiles[i][j].getTileType();
                if (type == tiles[i + 1][j].getTileType()
                        && type == tiles[i + 2][j].getTileType()
                        && type == tiles[i + 3][j].getTileType()
                        && type == tiles[i + 4][j].getTileType()) {
                    upgradeColumn(tiles, i + 2, j);
                }
            }
        }
    }

    @Override
    protected void playUpgradeEffect(Fruit_Tile tile) {
        // Add transform effect
        super.playUpgradeEffect(tile);
        mTransformFlashEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
        Fruit_Sounds.ICE_CREAM_UPGRADE.play();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void upgradeRow(Fruit_Tile[][] tiles, int row, int col) {
        // Row shape:
        // 0 0 X 0 0

        // Set target tile special type
        Fruit_Tile targetTile = tiles[row][col];
        targetTile.setSpecialType(Fruit_SpecialType.ICE_CREAM);

        // Init position factors
        setPositionXFactors(-2, -1, 1, 2);
        setPositionYFactors(0, 0, 0, 0);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
        // Important to set it to None, or it may detected as original type
        targetTile.setTileType(Fruit_FruitType.NONE);
    }

    private void upgradeColumn(Fruit_Tile[][] tiles, int row, int col) {
        // Column shape:
        // 0
        // 0
        // X
        // 0
        // 0

        // Set target tile special type
        Fruit_Tile targetTile = tiles[row][col];
        targetTile.setSpecialType(Fruit_SpecialType.ICE_CREAM);

        // Init position factors
        setPositionXFactors(0, 0, 0, 0);
        setPositionYFactors(-2, -1, 1, 2);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
        // Important to set it to None, or it may detected as original type
        targetTile.setTileType(Fruit_FruitType.NONE);
    }
    //========================================================

}
