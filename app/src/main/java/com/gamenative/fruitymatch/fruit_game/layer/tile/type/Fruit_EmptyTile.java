package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileResetter;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_EmptyTile extends Fruit_Tile {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_EmptyTile(Engine engine, Texture texture) {
        super(engine, texture, Fruit_FruitType.NONE);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void resetXByColumn(int column) {
    }

    @Override
    public void resetYByRow(int row) {
    }

    @Override
    public void initTile() {
    }

    @Override
    public void popTile() {
    }

    @Override
    public void matchTile() {
    }

    @Override
    public void resetTile() {
    }

    @Override
    public void hideTile() {
    }

    @Override
    public void shuffleTile() {
    }

    @Override
    public void playTileEffect() {
    }

    @Override
    public void moveTile(long elapsedMillis) {
    }

    @Override
    public void swapTile(long elapsedMillis) {
    }

    @Override
    public boolean isMoving() {
        // Empty tile can not move
        return false;
    }

    @Override
    public boolean isSwappable() {
        // Empty tile can not swap
        return false;
    }

    @Override
    public void setSwappable(boolean swappable) {
    }

    @Override
    public boolean isPoppable() {
        // Empty tile can not pop
        return false;
    }

    @Override
    public void setPoppable(boolean poppable) {
    }

    @Override
    public boolean isMatchable() {
        // Empty tile can not match
        return false;
    }

    @Override
    public void setMatchable(boolean matchable) {
    }

    @Override
    public boolean isShufflable() {
        // Empty tile can not shuffle
        return false;
    }

    @Override
    public void setShufflable(boolean shufflable) {
    }

    @Override
    public boolean isNegligible() {
        // Empty tile can not neglect
        return false;
    }

    @Override
    public void setNegligible(boolean negligible) {
    }

    @Override
    public void addResetter(Fruit_TileResetter resetter) {
    }

    @Override
    public void removeResetter(Fruit_TileResetter resetter) {
    }

    @Override
    public void selectTile() {
    }

    @Override
    public void unSelectTile() {
    }
    //========================================================

}
