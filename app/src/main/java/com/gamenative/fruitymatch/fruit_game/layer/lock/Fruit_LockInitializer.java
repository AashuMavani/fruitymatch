package com.gamenative.fruitymatch.fruit_game.layer.lock;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LockInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_LockType getType(char c) {
        switch (c) {
            case 'X':
                return Fruit_LockType.CENTER;
            default:
                throw new IllegalArgumentException("LockType not found!");
        }
    }
    //========================================================

}
