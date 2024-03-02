package com.gamenative.fruitymatch.fruit_game.layer.shell;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ShellInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_ShellType getType(char c) {
        switch (c) {
            case 'V':
                return Fruit_ShellType.SMALL_VERTICAL;
            case 'H':
                return Fruit_ShellType.SMALL_HORIZONTAL;
            case 'M':
                return Fruit_ShellType.MEDIUM;
            case 'L':
                return Fruit_ShellType.LARGE;
            default:
                throw new IllegalArgumentException("ShellType not found!");
        }
    }
    //========================================================

}
