package com.gamenative.fruitymatch.fruit_game.algorithm.special.finder;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SpecialTileFinderManager {

    private final List<Fruit_SpecialTileFinder> mSpecialTileFinders = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SpecialTileFinderManager(Engine engine) {
        // Add all the special tile finder
        mSpecialTileFinders.add(new Fruit_IceCreamFinder(engine));
        mSpecialTileFinders.add(new Fruit_ExplosiveTileXFinder(engine));
        mSpecialTileFinders.add(new Fruit_ExplosiveTileTFinder(engine));
        mSpecialTileFinders.add(new Fruit_ExplosiveTileLFinder(engine));
        mSpecialTileFinders.add(new Fruit_RowStripedTileFinder(engine));
        mSpecialTileFinders.add(new Fruit_ColumnStripedTileFinder(engine));
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void findSpecialTile(Fruit_Tile[][] tiles, int row, int col) {
        int size = mSpecialTileFinders.size();
        for (int i = 0; i < size; i++) {
            Fruit_SpecialTileFinder finder = mSpecialTileFinders.get(i);
            finder.findSpecialTile(tiles, row, col);
        }
    }
    //========================================================

}
