package com.gamenative.fruitymatch.fruit_game.booster;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_game.algorithm.special.handler.Fruit_ColumnStripedTileHandler;
import com.gamenative.fruitymatch.fruit_game.algorithm.special.handler.Fruit_RowStripedTileHandler;
import com.gamenative.fruitymatch.fruit_game.effect.booster.Fruit_HammerEffect;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.gamenative.fruitymatch.fruit_game.layer.tile.type.Fruit_EmptyTile;
import com.nativegame.natyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HammerController extends Fruit_BoosterController {

    private final Fruit_RowStripedTileHandler mRowStripedTileHandler;
    private final Fruit_ColumnStripedTileHandler mColumnStripedTileHandler;
    private final Fruit_HammerEffect mHammerEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_HammerController(Engine engine, Fruit_TileSystem tileSystem) {
        super(engine, tileSystem);
        mRowStripedTileHandler = new Fruit_RowStripedTileHandler(engine);
        mColumnStripedTileHandler = new Fruit_ColumnStripedTileHandler(engine);
        mHammerEffect = new Fruit_HammerEffect(engine, Fruit_Textures.HAMMER);
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
        mHammerEffect.activate(Fruit_GameWorld.WORLD_WIDTH / 2f, Fruit_GameWorld.WORLD_HEIGHT / 2f,
                touchDownTile.getX(), touchDownTile.getY());
        Fruit_Sounds.TILE_SLIDE.play();
    }

    @Override
    protected void onRemoveBooster(Fruit_Tile[][] tiles, Fruit_Tile touchDownTile, Fruit_Tile touchUpTile, int row, int col) {
        mRowStripedTileHandler.handleSpecialTile(tiles, touchDownTile, row, col);
        mColumnStripedTileHandler.handleSpecialTile(tiles, touchDownTile, row, col);
        dispatchEvent(Fruit_GameEvent.PLAYER_USE_BOOSTER);
    }
    //========================================================

}
