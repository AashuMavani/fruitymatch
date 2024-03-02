package com.gamenative.fruitymatch.fruit_game.layer.honey;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HoneyInitialize {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_HoneyType getType(char c) {
        switch (c) {
            case 'n':
                return Fruit_HoneyType.CENTER;
            case 'u':
                return Fruit_HoneyType.TOP;
            case 'd':
                return Fruit_HoneyType.DOWN;
            case 'l':
                return Fruit_HoneyType.LEFT;
            case 'r':
                return Fruit_HoneyType.RIGHT;
            case 'q':
                return Fruit_HoneyType.TOP_LEFT;
            case 'w':
                return Fruit_HoneyType.TOP_RIGHT;
            case 'a':
                return Fruit_HoneyType.DOWN_LEFT;
            case 's':
                return Fruit_HoneyType.DOWN_RIGHT;
            case 't':
                return Fruit_HoneyType.TOP_MARGIN;
            case 'b':
                return Fruit_HoneyType.DOWN_MARGIN;
            case 'z':
                return Fruit_HoneyType.LEFT_MARGIN;
            case 'x':
                return Fruit_HoneyType.RIGHT_MARGIN;
            case 'h':
                return Fruit_HoneyType.HORIZONTAL;
            case 'v':
                return Fruit_HoneyType.VERTICAL;
            case 'o':
                return Fruit_HoneyType.SOLE;
            default:
                throw new IllegalArgumentException("HoneyType not found!");
        }
    }
    //========================================================

}

