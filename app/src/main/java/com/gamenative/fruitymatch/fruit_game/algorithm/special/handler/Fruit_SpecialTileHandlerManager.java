package com.gamenative.fruitymatch.fruit_game.algorithm.special.handler;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SpecialTileHandlerManager {

    private final Map<Fruit_SpecialType, Fruit_SpecialTileHandler> mSpecialTileHandlers = new HashMap<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_SpecialTileHandlerManager(Engine engine) {
        // Add all the special tile handler
        mSpecialTileHandlers.put(Fruit_SpecialType.ICE_CREAM, new Fruit_IceCreamHandler(engine));
        mSpecialTileHandlers.put(Fruit_SpecialType.EXPLOSIVE, new Fruit_ExplosiveTileHandler(engine));
        mSpecialTileHandlers.put(Fruit_SpecialType.ROW_STRIPED, new Fruit_RowStripedTileHandler(engine));
        mSpecialTileHandlers.put(Fruit_SpecialType.COLUMN_STRIPED, new Fruit_ColumnStripedTileHandler(engine));
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void checkSpecialTile(Fruit_Tile[][] tiles, Fruit_Tile tile, int row, int col) {
        Fruit_SpecialType type = tile.getSpecialType();
        Fruit_SpecialTileHandler handler = mSpecialTileHandlers.get(type);
        if (handler != null) {
            handler.handleSpecialTile(tiles, tile, row, col);
        }
    }
    //========================================================

}
