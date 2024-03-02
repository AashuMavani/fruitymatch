package com.gamenative.fruitymatch.fruit_game.algorithm.layer;

import com.gamenative.fruitymatch.fruit_game.algorithm.target.Fruit_TargetHandlerManager;
import com.gamenative.fruitymatch.fruit_game.layer.honey.Fruit_HoneySystem;
import com.gamenative.fruitymatch.fruit_game.layer.entrypoint.Fruit_EntryPointSystem;
import com.gamenative.fruitymatch.fruit_game.layer.generator.Fruit_GeneratorSystem;
import com.gamenative.fruitymatch.fruit_game.layer.ice.Fruit_IceSystem;
import com.gamenative.fruitymatch.fruit_game.layer.lock.Fruit_LockSystem;
import com.gamenative.fruitymatch.fruit_game.layer.sand.Fruit_SandSystem;
import com.gamenative.fruitymatch.fruit_game.layer.shell.Fruit_ShellSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.engine.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LayerHandlerManager {

    private final List<Fruit_LayerHandler> mLayerHandlers = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_LayerHandlerManager(Engine engine) {
        if (Fruit_Level.LEVEL_DATA.getLock() != null) {
            mLayerHandlers.add(new Fruit_LockLayerHandler(new Fruit_LockSystem(engine)));
        }
        if (Fruit_Level.LEVEL_DATA.getIce() != null) {
            mLayerHandlers.add(new Fruit_IceLayerHandler(new Fruit_IceSystem(engine)));
        }
        if (Fruit_Level.LEVEL_DATA.getHoney() != null) {
            mLayerHandlers.add(new Fruit_HoneyLayerHandler(new Fruit_HoneySystem(engine)));
        }
        if (Fruit_Level.LEVEL_DATA.getEntry() != null) {
            mLayerHandlers.add(new Fruit_EntryPointLayerHandler(new Fruit_EntryPointSystem(engine)));
        }
        if (Fruit_Level.LEVEL_DATA.getSand() != null) {
            mLayerHandlers.add(new Fruit_SandLayerHandler(new Fruit_SandSystem(engine), new Fruit_ShellSystem(engine)));
        }
        if (Fruit_Level.LEVEL_DATA.getGenerator() != null) {
            mLayerHandlers.add(new Fruit_GeneratorLayerHandler(new Fruit_GeneratorSystem(engine)));
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public List<Fruit_LayerHandler> getLayerHandlers() {
        return mLayerHandlers;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void initLayers(Fruit_Tile[][] tiles, int row, int col) {
        int size = mLayerHandlers.size();
        for (int i = 0; i < size; i++) {
            Fruit_LayerHandler handler = mLayerHandlers.get(i);
            handler.initLayer(tiles, row, col);
        }
    }

    public void updateLayers(Fruit_TargetHandlerManager targetHandlerManager, Fruit_Tile[][] tiles, int row, int col) {
        int size = mLayerHandlers.size();
        for (int i = 0; i < size; i++) {
            Fruit_LayerHandler handler = mLayerHandlers.get(i);
            handler.updateLayer(targetHandlerManager, tiles, row, col);
        }
    }

    public void removeLayers(Fruit_Tile[][] tiles, int row, int col) {
        int size = mLayerHandlers.size();
        for (int i = 0; i < size; i++) {
            Fruit_LayerHandler handler = mLayerHandlers.get(i);
            handler.removeLayer(tiles, row, col);
        }
    }
    //========================================================

}
