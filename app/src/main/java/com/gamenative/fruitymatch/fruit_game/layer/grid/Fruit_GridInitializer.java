package com.gamenative.fruitymatch.fruit_game.layer.grid;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_GridInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_GridType getType(char c) {
        switch (c) {
            case 'n':
                return Fruit_GridType.CENTER;
            case 'u':
                return Fruit_GridType.TOP;
            case 'd':
                return Fruit_GridType.DOWN;
            case 'l':
                return Fruit_GridType.LEFT;
            case 'r':
                return Fruit_GridType.RIGHT;
            case 'q':
                return Fruit_GridType.TOP_LEFT;
            case 'w':
                return Fruit_GridType.TOP_RIGHT;
            case 'a':
                return Fruit_GridType.DOWN_LEFT;
            case 's':
                return Fruit_GridType.DOWN_RIGHT;
            case 't':
                return Fruit_GridType.TOP_MARGIN;
            case 'b':
                return Fruit_GridType.DOWN_MARGIN;
            case 'z':
                return Fruit_GridType.LEFT_MARGIN;
            case 'x':
                return Fruit_GridType.RIGHT_MARGIN;
            case 'h':
                return Fruit_GridType.HORIZONTAL;
            case 'v':
                return Fruit_GridType.VERTICAL;
            case 'o':
                return Fruit_GridType.SOLE;
            default:
                throw new IllegalArgumentException("GridType not found!");
        }
    }
    //========================================================

}
