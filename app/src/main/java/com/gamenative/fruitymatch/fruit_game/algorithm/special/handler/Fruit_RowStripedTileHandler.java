package com.gamenative.fruitymatch.fruit_game.algorithm.special.handler;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_RowFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_RowStripedTileHandler extends Fruit_BaseSpecialTileHandler {

    private final Fruit_RowFlashEffectSystem mFlashEffectSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_RowStripedTileHandler(Engine engine) {
        super(engine);
        mFlashEffectSystem = new Fruit_RowFlashEffectSystem(engine, 1);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void handleSpecialTile(Fruit_Tile[][] tiles, Fruit_Tile tile, int row, int col) {
        int targetRow = tile.getRow();
        for (int j = 0; j < col; j++) {
            Fruit_Tile t = tiles[targetRow][j];
            // We make sure not pop the tile multiple time
            if (t.getTileState() == Fruit_TileState.IDLE) {
                t.popTile();
            }
        }

        playTileEffect(tile);
    }

    @Override
    protected void playTileEffect(Fruit_Tile tile) {
        super.playTileEffect(tile);
        mFlashEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
    }
    //========================================================

}
