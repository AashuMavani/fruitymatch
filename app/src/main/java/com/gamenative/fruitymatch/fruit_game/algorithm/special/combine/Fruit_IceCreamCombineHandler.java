package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_game.effect.lightning.Fruit_LightningEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.lightning.Fruit_LightningGlitterEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_IceCreamPieceEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_IceCreamCombineHandler extends Fruit_BaseSpecialCombineHandler {

    private static final int MAX_LIGHTNING_NUM = 20;

    private final Fruit_LightningEffectSystem mLightningEffectSystem;
    private final Fruit_LightningGlitterEffectSystem mLightningGlitterEffectSystem;
    private final Fruit_IceCreamPieceEffectSystem mIceCreamPieceEffectSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_IceCreamCombineHandler(Engine engine) {
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
    protected void playTileEffect(Fruit_Tile colorTile, Fruit_Tile fruitTile) {
        super.playTileEffect(colorTile, fruitTile);
        mIceCreamPieceEffectSystem.activate(colorTile.getCenterX(), colorTile.getCenterY());
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    protected void playLightningEffect(Fruit_Tile colorTile, Fruit_Tile targetTile) {
        // Calculate distance and angle between two tiles
        float distanceX = colorTile.getX() - targetTile.getX();
        float distanceY = colorTile.getY() - targetTile.getY();
        int distance = (int) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        int angle = getAngle(distanceX, distanceY);
        // Add one lightning and glitter
        mLightningEffectSystem.activate(colorTile.getCenterX(), colorTile.getCenterY(), distance, angle);
        mLightningGlitterEffectSystem.activate(targetTile.getCenterX(), targetTile.getCenterY());
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
