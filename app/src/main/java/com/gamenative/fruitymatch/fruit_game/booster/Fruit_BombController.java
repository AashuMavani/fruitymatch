package com.gamenative.fruitymatch.fruit_game.booster;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_game.algorithm.special.handler.Fruit_ExplosiveTileHandler;
import com.gamenative.fruitymatch.fruit_game.effect.booster.Fruit_BombEffect;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_EmptyTile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_BombController extends Fruit_BoosterController {

    private final Fruit_ExplosiveTileHandler mExplosiveTileHandler;
    private final Fruit_BombEffect mBombEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_BombController(Engine engine, Fruit_TileSystem tileSystem) {
        super(engine, tileSystem);
        mExplosiveTileHandler = new Fruit_ExplosiveTileHandler(engine);
        mBombEffect = new Fruit_BombEffect(engine, Fruit_Textures.BOMB);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected boolean isAddBooster(Fruit_Tile touchDownTile, Fruit_Tile touchUpTile) {
        return !(touchDownTile instanceof Fruit_EmptyTile);
    }

    @Override
    protected void onAddBooster(Fruit_Tile[][] tiles, Fruit_Tile touchDownTile, Fruit_Tile touchUpTile, int row, int col) {
        mBombEffect.activate(Fruit_GameWorld.WORLD_WIDTH / 2f, Fruit_GameWorld.WORLD_HEIGHT / 2f,
                touchDownTile.getX(), touchDownTile.getY());
        Fruit_Sounds.TILE_SLIDE.play();
    }

    @Override
    protected void onRemoveBooster(Fruit_Tile[][] tiles, Fruit_Tile touchDownTile, Fruit_Tile touchUpTile, int row, int col) {
        mExplosiveTileHandler.handleSpecialTile(tiles, touchDownTile, row, col);
        dispatchEvent(Fruit_GameEvent.PLAYER_USE_BOOSTER);
    }
    //========================================================

}
