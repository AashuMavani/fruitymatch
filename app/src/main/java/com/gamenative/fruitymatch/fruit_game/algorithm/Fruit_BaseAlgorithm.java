package com.gamenative.fruitymatch.fruit_game.algorithm;

import com.gamenative.fruitymatch.fruit_game.algorithm.special.finder.Fruit_SpecialTileFinderManager;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_BaseAlgorithm extends Entity implements Fruit_Algorithm {

    protected final Fruit_Tile[][] mTiles;
    protected final int mTotalRow;
    protected final int mTotalCol;

    protected final Fruit_SpecialTileFinderManager mSpecialTileFinder;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_BaseAlgorithm(Engine engine, Fruit_TileSystem tileSystem) {
        super(engine);
        mTiles = tileSystem.getChild();
        mTotalRow = tileSystem.getTotalRow();
        mTotalCol = tileSystem.getTotalColumn();
        mSpecialTileFinder = new Fruit_SpecialTileFinderManager(engine);
    }
    //========================================================

}
