package com.gamenative.fruitymatch.fruit_game.algorithm.special.handler;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.effect.flash.Fruit_ColumnFlashEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ColumnStripedTileHandler extends Fruit_BaseSpecialTileHandler {

    private final Fruit_ColumnFlashEffectSystem mFlashEffectSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ColumnStripedTileHandler(Engine engine) {
        super(engine);
        mFlashEffectSystem = new Fruit_ColumnFlashEffectSystem(engine, 1);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void handleSpecialTile(Fruit_Tile[][] tiles, Fruit_Tile tile, int row, int col) {
        int targetCol = tile.getColumn();
        for (int i = 0; i < row; i++) {
            Fruit_Tile t = tiles[i][targetCol];
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
