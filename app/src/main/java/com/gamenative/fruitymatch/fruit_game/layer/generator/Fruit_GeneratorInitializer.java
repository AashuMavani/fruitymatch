package com.gamenative.fruitymatch.fruit_game.layer.generator;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.layer.generator.type.Fruit_ColumnStripedGenerator;
import com.gamenative.fruitymatch.fruit_game.layer.generator.type.Fruit_ExplosiveGenerator;
import com.gamenative.fruitymatch.fruit_game.layer.generator.type.Fruit_RowColumnStripedGenerator;
import com.gamenative.fruitymatch.fruit_game.layer.generator.type.Fruit_RowStripedGenerator;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_GeneratorInitializer {

    //--------------------------------------------------------
    // Static methods
    //--------------------------------------------------------
    public static Fruit_Generator getGenerator(Engine engine, char c) {
        switch (c) {
            case 'R':
                return new Fruit_RowStripedGenerator(engine, Fruit_Textures.GENERATOR_ROW_STRIPED);
            case 'C':
                return new Fruit_ColumnStripedGenerator(engine, Fruit_Textures.GENERATOR_COLUMN_STRIPED);
            case 'M':
                return new Fruit_RowColumnStripedGenerator(engine, Fruit_Textures.GENERATOR_ROW_COLUMN_STRIPED);
            case 'E':
                return new Fruit_ExplosiveGenerator(engine, Fruit_Textures.GENERATOR_EXPLOSIVE);
            default:
                throw new IllegalArgumentException("Generator not found!");
        }
    }
    //========================================================

}
