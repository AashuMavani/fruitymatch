package com.gamenative.fruitymatch.fruit_game.algorithm.special.combine;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Colors;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameLayer;
import com.gamenative.fruitymatch.fruit_game.effect.combine.Fruit_IceCreamCombineBeamEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.combine.Fruit_IceCreamCombineEffectSystem;
import com.gamenative.fruitymatch.fruit_game.effect.combine.Fruit_IceCreamCombineRingEffectSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_SpecialType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.particle.ParticleSystem;
import com.nativegame.natyengine.entity.particle.SpriteParticleSystem;
import com.nativegame.natyengine.entity.shape.primitive.Plane;
import com.nativegame.natyengine.entity.timer.Timer;
import com.nativegame.natyengine.entity.timer.TimerEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_DoubleIceCreamCombineHandler extends Fruit_BaseSpecialCombineHandler implements TimerEvent.TimerEventListener {

    private static final long START_DELAY = 2000;

    private final Fruit_IceCreamCombineEffectSystem mIceCreamCombineEffectSystem;
    private final Fruit_IceCreamCombineBeamEffectSystem mIceCreamCombineBeamEffectSystem;
    private final Fruit_IceCreamCombineRingEffectSystem mIceCreamCombineRingEffectSystem;
    private final ParticleSystem mBlueLightBgParticleSystem;
    private final ParticleSystem mWhiteLightBgParticleSystem;
    private final ParticleSystem mRingLightParticleSystem;
    private final Plane mShadowBg;
    private final Timer mTimer;

    private final List<Fruit_Tile> mPopTiles = new ArrayList<>();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_DoubleIceCreamCombineHandler(Engine engine) {
        super(engine);
        mIceCreamCombineEffectSystem = new Fruit_IceCreamCombineEffectSystem(engine, 1);
        mIceCreamCombineBeamEffectSystem = new Fruit_IceCreamCombineBeamEffectSystem(engine, 10);
        mIceCreamCombineRingEffectSystem = new Fruit_IceCreamCombineRingEffectSystem(engine, 3);
        mBlueLightBgParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_BG_BLUE, 1)
                .setDuration(2000)
                .setScale(4, 2)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
        mWhiteLightBgParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.LIGHT_BG, 1)
                .setDuration(2000)
                .setScale(2, 1)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER + 2);
        mRingLightParticleSystem = new SpriteParticleSystem(engine, Fruit_Textures.FLASH_RING_BLUE, 1)
                .setDuration(2600)
                .setScale(0, 30, 2000)
                .setAlpha(255, 55, 2000)
                .setLayer(Fruit_GameLayer.EFFECT_LAYER);
        mShadowBg = new Plane(engine, Fruit_Colors.BLACK_80);
        mShadowBg.setLayer(Fruit_GameLayer.EFFECT_LAYER);
        mTimer = new Timer(engine);
        mTimer.addTimerEvent(new TimerEvent(this, START_DELAY));
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public long getStartDelay() {
        return START_DELAY;
    }

    @Override
    public boolean checkSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        // Check are both tiles explosion special tile
        if (tileA.getSpecialType() == Fruit_SpecialType.ICE_CREAM
                && tileB.getSpecialType() == Fruit_SpecialType.ICE_CREAM) {
            // We make sure the origin special tiles not being detected
            tileA.setTileState(Fruit_TileState.MATCH);
            tileB.setTileState(Fruit_TileState.MATCH);
            handleSpecialCombine(tiles, tileA, tileB, row, col);
            return true;
        }

        return false;
    }

    @Override
    protected void playTileEffect(Fruit_Tile tileA, Fruit_Tile tileB) {
        mShadowBg.addToGame();
        super.playTileEffect(tileA, tileB);
        float x = (tileA.getCenterX() + tileB.getCenterX()) / 2;
        float y = (tileA.getCenterY() + tileB.getCenterY()) / 2;
        mIceCreamCombineBeamEffectSystem.activate(x, y);
        mIceCreamCombineRingEffectSystem.activate(x, y);
        mBlueLightBgParticleSystem.oneShot(x, y, 1);
        mWhiteLightBgParticleSystem.oneShot(x, y, 1);
        mRingLightParticleSystem.oneShot(x, y, 1);
        mIceCreamCombineEffectSystem.activate(x, y);
        tileA.hideTile();
        tileB.hideTile();
        // Important to hide these tiles here, since the Algorithm hasn't been added yet
    }

    @Override
    public void onTimerEvent(long eventTime) {
        mShadowBg.removeFromGame();
        mEngine.dispatchEvent(Fruit_GameEvent.SHAKE_GAME);
        Fruit_Sounds.ICE_CREAM_EXPLODE.play();
        int size = mPopTiles.size();
        for (int i = 0; i < size; i++) {
            Fruit_Tile tile = mPopTiles.get(i);
            tile.popTile();
        }
        mPopTiles.clear();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void handleSpecialCombine(Fruit_Tile[][] tiles, Fruit_Tile tileA, Fruit_Tile tileB, int row, int col) {
        // Pop all the tiles
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Fruit_Tile t = tiles[i][j];
                // We make sure not pop multiple times
                if (t.getTileState() == Fruit_TileState.IDLE) {
                    mPopTiles.add(t);
                }
            }
        }

        playTileEffect(tileA, tileB);
        mTimer.start();
    }
    //========================================================

}
