package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_BaseLayerHandler implements Fruit_LayerHandler {

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void initLayer(Fruit_Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                onInitLayer(t);
            }
        }
    }

    @Override
    public void updateLayer(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                onUpdateLayer(t, targetHandlerManager, tiles, row, col);
            }
        }
    }

    @Override
    public void removeLayer(Fruit_Tile[][] tiles, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                onRemoveLayer(t);
            }
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected abstract void onInitLayer(Fruit_Tile tile);

    protected abstract void onUpdateLayer(Fruit_Tile tile, Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col);

    protected abstract void onRemoveLayer(Fruit_Tile tile);
    //========================================================

}
