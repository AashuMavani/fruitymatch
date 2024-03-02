package com.gamenative.fruitymatch.fruit_game.layer.grid;

import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_Grid extends Fruit_LayerSprite {

    private final Fruit_GridType mGridType;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_Grid(Engine engine, Texture texture, Fruit_GridType gridType) {
        super(engine, texture);
        mGridType = gridType;
        setRotation(gridType.getAngle());
        setLayer(Fruit_GameLayer.GRID_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_GridType getGridType() {
        return mGridType;
    }
    //========================================================

}
