package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.effect.Fruit_UpgradeFruitEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_TripleSpecialTileFinder extends Fruit_BaseSpecialTileFinder {

    private static final int UPGRADE_NUM = 3;

    private final Fruit_UpgradeFruitEffectSystem mUpgradeFruitEffectSystem;

    private final int[] mPositionXFactors = new int[UPGRADE_NUM];
    private final int[] mPositionYFactors = new int[UPGRADE_NUM];

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_TripleSpecialTileFinder(Engine engine) {
        super(engine);
        mUpgradeFruitEffectSystem = new Fruit_UpgradeFruitEffectSystem(engine, MAX_FIND_NUM * UPGRADE_NUM);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    protected void setPositionXFactors(int positionA, int positionB, int positionC) {
        mPositionXFactors[0] = positionA;
        mPositionXFactors[1] = positionB;
        mPositionXFactors[2] = positionC;
    }

    protected void setPositionYFactors(int positionA, int positionB, int positionC) {
        mPositionYFactors[0] = positionA;
        mPositionYFactors[1] = positionB;
        mPositionYFactors[2] = positionC;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void setUpgradeTiles(Fruit_Tile[][] tiles, int row, int col) {
        for (int i = 0; i < UPGRADE_NUM; i++) {
            Fruit_Tile t = tiles[row + mPositionYFactors[i]][col + mPositionXFactors[i]];
            // We only upgrade the none special tile and tile have not been reset
            if (t.getSpecialType() == Fruit_SpecialType.NONE && t.getTileState() == Fruit_TileState.MATCH) {
                t.setSpecialType(Fruit_SpecialType.UPGRADE);
            }
        }
    }

    @Override
    protected void playUpgradeEffect(Fruit_Tile tile) {
        super.playUpgradeEffect(tile);

        // Init end position
        float endX = tile.getX();
        float endY = tile.getY();

        // Add 3 upgrade fruit
        for (int i = 0; i < UPGRADE_NUM; i++) {
            // Init start position
            float startX = endX + mPositionXFactors[i] * tile.getWidth();
            float startY = endY + mPositionYFactors[i] * tile.getHeight();
            mUpgradeFruitEffectSystem.activate(startX, startY, endX, endY, (Fruit_FruitType) tile.getTileType());
        }
    }
    //========================================================

}
