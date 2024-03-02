package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface Fruit_LayerHandler {

    void initLayer(Fruit_Tile[][] tiles, int row, int col);

    void updateLayer(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col);

    void removeLayer(Fruit_Tile[][] tiles, int row, int col);

}
