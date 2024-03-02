package com.gamenative.fruitymatch.fruit_game.layer.entrypoint;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_EntryPointInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_EntryPointType getType(char c) {
        switch (c) {
            case 'A':
                return Fruit_EntryPointType.ARROW;
            default:
                throw new IllegalArgumentException("EntryPointType not found!");
        }
    }
    //========================================================

}
