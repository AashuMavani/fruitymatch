package com.gamenative.fruitymatch.fruit_game.layer.tile.type;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_TileState;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.effect.piece.Fruit_StarfishPieceEffect;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_FruitType;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_StarfishTile extends Fruit_SolidTile {

    private final Fruit_StarfishPieceEffect mStarfishPieceEffect;

    private boolean mIsStarfish = true;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_StarfishTile(Fruit_TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture, Fruit_FruitType.NONE);
        mStarfishPieceEffect = new Fruit_StarfishPieceEffect(engine, Fruit_Textures.STARFISH);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isStarfish() {
        return mIsStarfish;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void popTile() {
        if (mIsStarfish) {
            return;
        }
        super.popTile();
    }

    @Override
    public void playTileEffect() {
        if (mIsStarfish) {
            playStarfishEffect();
            mIsStarfish = false;
            return;
        }
        super.playTileEffect();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void popStarfishTile() {
        // Important to not reuse popTile() or matchTile()
        mTileState = Fruit_TileState.MATCH;
    }

    private void playStarfishEffect() {
        mStarfishPieceEffect.activate(getCenterX(), getCenterY());
        Fruit_Sounds.COLLECT_STARFISH.play();
    }
    //========================================================

}
