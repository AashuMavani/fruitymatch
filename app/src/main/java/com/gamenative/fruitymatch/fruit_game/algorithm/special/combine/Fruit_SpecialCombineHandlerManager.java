package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SpecialCombineHandlerManager {

    private final List<Fruit_SpecialCombineHandler> mSpecialCombineHandlers = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SpecialCombineHandlerManager(Engine engine) {
        // Add all the special combine handler
        mSpecialCombineHandlers.add(new Fruit_IceCreamTileCombineHandler(engine));
        mSpecialCombineHandlers.add(new Fruit_IceCreamStripedTileCombineHandler(engine));
        mSpecialCombineHandlers.add(new Fruit_IceCreamExplosiveTileCombineHandler(engine));
        mSpecialCombineHandlers.add(new Fruit_DoubleIceCreamCombineHandler(engine));
        mSpecialCombineHandlers.add(new Fruit_DoubleExplosiveTileCombineHandler(engine));
        mSpecialCombineHandlers.add(new Fruit_DoubleStripedTileCombineHandler(engine));
        mSpecialCombineHandlers.add(new Fruit_ExplosiveStripedTileCombineHandler(engine));
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public Fruit_SpecialCombineHandler checkSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        int size = mSpecialCombineHandlers.size();
        for (int i = 0; i < size; i++) {
            Fruit_SpecialCombineHandler handler = mSpecialCombineHandlers.get(i);
            // We make sure detected one special combine
            if (handler.checkSpecialCombine(tiles, tileA, tileB, row, col)) {
                return handler;
            }
        }

        return null;
    }
    //========================================================

}
