package com.gamenative.fruitymatch.fruit_game.layer.sand;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_SandInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_SandType getType(char c) {
        char lower = Character.toLowerCase(c);
        switch (lower) {
            case 'n':
                return Fruit_SandType.CENTER;
            case 'u':
                return Fruit_SandType.TOP;
            case 'd':
                return Fruit_SandType.DOWN;
            case 'l':
                return Fruit_SandType.LEFT;
            case 'r':
                return Fruit_SandType.RIGHT;
            case 'q':
                return Fruit_SandType.TOP_LEFT;
            case 'w':
                return Fruit_SandType.TOP_RIGHT;
            case 'a':
                return Fruit_SandType.DOWN_LEFT;
            case 's':
                return Fruit_SandType.DOWN_RIGHT;
            case 't':
                return Fruit_SandType.TOP_MARGIN;
            case 'b':
                return Fruit_SandType.DOWN_MARGIN;
            case 'z':
                return Fruit_SandType.LEFT_MARGIN;
            case 'x':
                return Fruit_SandType.RIGHT_MARGIN;
            case 'h':
                return Fruit_SandType.HORIZONTAL;
            case 'v':
                return Fruit_SandType.VERTICAL;
            case 'o':
                return Fruit_SandType.SOLE;
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