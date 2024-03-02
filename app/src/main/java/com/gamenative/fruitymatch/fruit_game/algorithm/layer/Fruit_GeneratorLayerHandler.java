package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.generator.Fruit_GeneratorSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileResetter;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_GeneratorLayerHandler extends Fruit_BaseLayerHandler {

    private final Fruit_GeneratorSystem mGeneratorSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_GeneratorLayerHandler(Fruit_GeneratorSystem generatorSystem) {
        mGeneratorSystem = generatorSystem;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onInitLayer(Fruit_Tile tile) {
        List<Fruit_TileResetter> resetters = mGeneratorSystem.getResetters();
        int size = resetters.size();
        for (int i = 0; i < size; i++) {
            Fruit_TileResetter resetter = resetters.get(i);
            tile.addResetter(resetter);
        }
    }

    @Override
    protected void onUpdateLayer(Fruit_Tile tile, Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col) {
    }

    @Override
    protected void onRemoveLayer(Fruit_Tile tile) {
        List<Fruit_TileResetter> resetters = mGeneratorSystem.getResetters();
        int size = resetters.size();
        for (int i = 0; i < size; i++) {
            Fruit_TileResetter resetter = resetters.get(i);
            tile.removeResetter(resetter);
        }
    }
    //========================================================

}
