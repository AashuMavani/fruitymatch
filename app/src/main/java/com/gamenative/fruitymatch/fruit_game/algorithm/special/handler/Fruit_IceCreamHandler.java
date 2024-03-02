package com.gamenative.fruitymatch.fruit_game.algorithm.special.handler;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_game.effect.lightning.Fruit_LightningEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.lightning.Fruit_LightningGlitterEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_IceCreamPieceEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileInitializer;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_IceCreamHandler extends Fruit_BaseSpecialTileHandler {

    private static final int MAX_LIGHTNING_NUM = 10;

    private final Fruit_LightningEffectSystem mLightningEffectSystem;
    private final Fruit_LightningGlitterEffectSystem mLightningGlitterEffectSystem;
    private final Fruit_IceCreamPieceEffectSystem mIceCreamPieceEffectSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_IceCreamHandler(Engine engine) {
        super(engine);
        mLightningEffectSystem = new Fruit_LightningEffectSystem(engine, MAX_LIGHTNING_NUM);
        mLightningGlitterEffectSystem = new Fruit_LightningGlitterEffectSystem(engine, MAX_LIGHTNING_NUM);
        mIceCreamPieceEffectSystem = new Fruit_IceCreamPieceEffectSystem(engine, 1);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void handleSpecialTile(Fruit_Tile[][] tiles, Fruit_Tile tile, int row, int col) {
        // Generate a random fruit mType
        Fruit_FruitType targetType = Fruit_TileInitializer.getRandomType();

        // Pop the same type tile
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                // We make sure not pop multiple times
                if (t.getTileType() == targetType && t.getTileState() == Fruit_TileState.IDLE) {
                    // We pop this tile and obstacle around
                    t.matchTile();
                    // Add lightning effect from color tile to target tile
                    playLightningEffect(tile, t);
                }
            }
        }

        playTileEffect(tile);
    }

    @Override
    protected void playTileEffect(Fruit_Tile tile) {
        super.playTileEffect(tile);
        mIceCreamPieceEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void playLightningEffect(Fruit_Tile colorTile, Fruit_Tile targetTile) {
        // Calculate distance and angle between two tiles
        float distanceX = colorTile.getX() - targetTile.getX();
        float distanceY = colorTile.getY() - targetTile.getY();
        int distance = (int) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        int angle = getAngle(distanceX, distanceY);
        // Add one lightning and glitter
        mLightningEffectSystem.activate(colorTile.getX() + colorTile.getWidth() / 2f,
                colorTile.getY() + colorTile.getHeight() / 2f, distance, angle);
        mLightningGlitterEffectSystem.activate(targetTile.getX() + targetTile.getWidth() / 2f,
                targetTile.getY() + targetTile.getHeight() / 2f);
    }

    private int getAngle(float distanceX, float distanceY) {
        //  dx+  | dx-
        //  dy+  | dy+
        // ------|------
        //  dx+  | dx-
        //  dy-  | dy-
        double angleInRads = Math.atan2(Math.abs(distanceY), Math.abs(distanceX));
        int angle = (int) Math.toDegrees(angleInRads);
        if (distanceX >= 0) {
            if (distanceY >= 0) {
                return 90 + angle;
            } else {
                return 90 - angle;
            }
        } else {
            if (distanceY >= 0) {
                return -90 - angle;
            } else {
                return -90 + angle;
            }
        }
    }
    //========================================================

}
