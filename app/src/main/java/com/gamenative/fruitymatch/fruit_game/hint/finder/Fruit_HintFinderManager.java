package com.gamenative.fruitymatch.fruit_game.hint.finder;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HintFinderManager {

    private final List<Fruit_HintFinder> mHintFinders = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_HintFinderManager() {
        mHintFinders.add(new Fruit_SpecialCombineHintFinder());
        mHintFinders.add(new Fruit_IceCreamHintFinder());
        mHintFinders.add(new Fruit_ExplosiveStripedTileHintFinder());
        mHintFinders.add(new Fruit_TileHintFinder());
        mHintFinders.add(new Fruit_IceCreamTileHintFind());
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public List<Fruit_Tile> findHint(Fruit_Tile[][] tiles, int row, int col) {
        int size = mHintFinders.size();
        for (int i = 0; i < size; i++) {
            Fruit_HintFinder finder = mHintFinders.get(i);
            List<Fruit_Tile> hintTiles = finder.findHint(tiles, row, col);
            // Check is any hint detected
            if (!hintTiles.isEmpty()) {
                return hintTiles;
            }
        }

        return null;
    }
    //========================================================

}
