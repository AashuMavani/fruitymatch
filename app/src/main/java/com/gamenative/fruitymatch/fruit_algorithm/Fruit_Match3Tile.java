package com.gamenative.fruitymatch.fruit_algorithm;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface Fruit_Match3Tile {

    int getRow();

    void setRow(int row);

    int getColumn();

    void setColumn(int column);

    Fruit_TileState getTileState();

    void setTileState(Fruit_TileState tileState);

    Fruit_TileType getTileType();

    void setTileType(Fruit_TileType tileType);

    void resetXByColumn(int column);

    void resetYByRow(int row);

    void initTile();

    void popTile();

    void matchTile();

    void resetTile();

    void hideTile();

    void shuffleTile();

    void playTileEffect();

    void moveTile(long elapsedMillis);

    void swapTile(long elapsedMillis);

    boolean isMoving();

    boolean isSwappable();

    void setSwappable(boolean swappable);

    boolean isPoppable();

    void setPoppable(boolean poppable);

    boolean isMatchable();

    void setMatchable(boolean matchable);

    boolean isShufflable();

    void setShufflable(boolean shufflable);

    boolean isNegligible();

    void setNegligible(boolean negligible);

}
