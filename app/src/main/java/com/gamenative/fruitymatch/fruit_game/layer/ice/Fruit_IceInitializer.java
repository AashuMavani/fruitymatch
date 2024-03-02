package com.gamenative.fruitymatch.fruit_game.layer.ice;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_IceType getType(char c) {
        char lower = Character.toLowerCase(c);
        switch (lower) {
            case 'n':
                return Fruit_IceType.CENTER;
            case 'u':
                return Fruit_IceType.TOP;
            case 'd':
                return Fruit_IceType.DOWN;
            case 'l':
                return Fruit_IceType.LEFT;
            case 'r':
                return Fruit_IceType.RIGHT;
            case 'q':
                return Fruit_IceType.TOP_LEFT;
            case 'w':
                return Fruit_IceType.TOP_RIGHT;
            case 'a':
                return Fruit_IceType.DOWN_LEFT;
            case 's':
                return Fruit_IceType.DOWN_RIGHT;
            case 't':
                return Fruit_IceType.TOP_MARGIN;
            case 'b':
                return Fruit_IceType.DOWN_MARGIN;
            case 'z':
                return Fruit_IceType.LEFT_MARGIN;
            case 'x':
                return Fruit_IceType.RIGHT_MARGIN;
            case 'h':
                return Fruit_IceType.HORIZONTAL;
            case 'v':
                return Fruit_IceType.VERTICAL;
            case 'o':
                return Fruit_IceType.SOLE;
            default:
                throw new IllegalArgumentException("IceType not found!");
        }
    }

    public static int getLayer(char c) {
        if (Character.isLowerCase(c)) {
            return 1;
        } else {
            return 2;
        }
    }
    //========================================================

}
