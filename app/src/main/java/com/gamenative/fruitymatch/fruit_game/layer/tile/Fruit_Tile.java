package com.gamenative.fruitymatch.fruit_game.layer.tile;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_Match3Tile;
import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileType;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.layer.Fruit_LayerSprite;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_Tile extends Fruit_LayerSprite implements Fruit_Match3Tile {

    protected Fruit_FruitType mFruitType;
    protected Fruit_SpecialType mSpecialType;
    protected Fruit_TileState mTileState;
    private boolean mIsSelect = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_Tile(Engine engine, Texture texture, Fruit_FruitType fruitType) {
        this(engine, texture, fruitType, Fruit_SpecialType.NONE, Fruit_TileState.IDLE);
    }

    protected Fruit_Tile(Engine engine, Texture texture, Fruit_FruitType fruitType, Fruit_TileState tileState) {
        this(engine, texture, fruitType, Fruit_SpecialType.NONE, tileState);
    }

    protected Fruit_Tile(Engine engine, Texture texture, Fruit_FruitType fruitType, Fruit_SpecialType specialType) {
        this(engine, texture, fruitType, specialType, Fruit_TileState.IDLE);
    }

    protected Fruit_Tile(Engine engine, Texture texture, Fruit_FruitType fruitType, Fruit_SpecialType specialType, Fruit_TileState tileState) {
        super(engine, texture);
        mFruitType = fruitType;
        mSpecialType = specialType;
        mTileState = tileState;
        setLayer(Fruit_GameLayer.TILE_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Fruit_SpecialType getSpecialType() {
        return mSpecialType;
    }

    public void setSpecialType(Fruit_SpecialType specialType) {
        mSpecialType = specialType;
        if (specialType != Fruit_SpecialType.UPGRADE) {
            // Put the tile back to idle state
            mTileState = Fruit_TileState.IDLE;
            // No texture for UPGRADE
            setTexture(specialType.getTexture(mFruitType));
        }
    }

    public boolean isSelect() {
        return mIsSelect;
    }

    public void setSelect(boolean select) {
        mIsSelect = select;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public Fruit_TileState getTileState() {
        return mTileState;
    }

    @Override
    public void setTileState(Fruit_TileState tileState) {
        mTileState = tileState;
    }

    @Override
    public Fruit_TileType getTileType() {
        return mFruitType;
    }

    @Override
    public void setTileType(Fruit_TileType tileType) {
        Fruit_FruitType fruitType = ((Fruit_FruitType) tileType);
        mFruitType = fruitType;
        if (fruitType != Fruit_FruitType.NONE) {
            // No texture for NONE
            setTexture(fruitType.getTexture());
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public abstract void addResetter(Fruit_TileResetter resetter);

    public abstract void removeResetter(Fruit_TileResetter resetter);

    public abstract void selectTile();

    public abstract void unSelectTile();
    //========================================================

}
