package com.gamenative.fruitymatch.fruit_game.booster;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_Match3Algorithm;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_game.effect.booster.Fruit_GloveEffect;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.gamenative.fruitymatch.fruit_game.swap.Fruit_SwapModifier;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_GloveController extends Fruit_BoosterController implements Fruit_SwapModifier.SwapListener {

    private final Fruit_SwapModifier mSwapModifier;
    private final Fruit_GloveEffect mGloveEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_GloveController(Engine engine, Fruit_TileSystem tileSystem) {
        super(engine, tileSystem);
        mSwapModifier = new Fruit_SwapModifier(engine);
        mSwapModifier.setListener(this);
        mGloveEffect = new Fruit_GloveEffect(engine, Fruit_Textures.GLOVE);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected boolean isAddBooster(Fruit_Tile touchDownTile, Fruit_Tile touchUpTile) {
        return touchUpTile != null && touchDownTile.isSwappable() && touchUpTile.isSwappable();
    }

    @Override
    protected void onAddBooster(Fruit_Tile[][] tiles, Fruit_Tile touchDownTile, Fruit_Tile touchUpTile, int row, int col) {
        mGloveEffect.activate(Fruit_GameWorld.WORLD_WIDTH / 2f, Fruit_GameWorld.WORLD_HEIGHT / 2f,
                touchDownTile.getX(), touchDownTile.getY(), touchUpTile.getX(), touchUpTile.getY());
        Fruit_Sounds.TILE_SLIDE.play();
    }

    @Override
    protected void onRemoveBooster(Fruit_Tile[][] tiles, Fruit_Tile touchDownTile, Fruit_Tile touchUpTile, int row, int col) {
        Fruit_Match3Algorithm.swapTile(tiles, touchDownTile, touchUpTile);
        mSwapModifier.activate(touchDownTile, touchUpTile);
    }

    @Override
    public void onSwap(Fruit_Tile tileA, Fruit_Tile tileB) {
        dispatchEvent(Fruit_GameEvent.PLAYER_USE_BOOSTER);
    }
    //========================================================

}
