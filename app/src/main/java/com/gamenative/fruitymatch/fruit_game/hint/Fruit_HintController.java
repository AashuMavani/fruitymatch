package com.gamenative.fruitymatch.fruit_game.hint;

import com.gamenative.fruitymatch.fruit_algorithm.Fruit_Match3Algorithm;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Preferences;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Textures;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameWorld;
import com.gamenative.fruitymatch.fruit_game.effect.Fruit_TextEffect;
import com.gamenative.fruitymatch.fruit_game.hint.finder.Fruit_HintFinderManager;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_Tile;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.gamenative.fruitymatch.fruit_level.Fruit_TutorialType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.Entity;
import com.nativegame.natyengine.entity.timer.Timer;
import com.nativegame.natyengine.entity.timer.TimerEvent;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_HintController extends Entity implements EventListener, TimerEvent.TimerEventListener {

    private static final long HINT_TIMEOUT = 4000;
    private static final long SHUFFLE_TIMEOUT = 2000;
    private static final long SHUFFLE_SOUND_TIMEOUT = 1000;
    private static final long SLIDE_SOUND_TIMEOUT = 300;

    private final Fruit_Tile[][] mTiles;
    private final int mTotalRow;
    private final int mTotalCol;
    private final Fruit_HintFinderManager mHintFinder;
    private final Fruit_HintModifier mHintModifier;
    private final Fruit_TextEffect mShuffleText;
    private final Timer mHintTimer;
    private final Timer mShuffleTimer;
    private final Timer mSoundTimer;
    private final boolean mHintEnable;

    private List<Fruit_Tile> mHintTiles;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_HintController(Engine engine, Fruit_TileSystem tileSystem) {
        super(engine);
        mTiles = tileSystem.getChild();
        mTotalRow = tileSystem.getTotalRow();
        mTotalCol = tileSystem.getTotalColumn();
        mHintFinder = new Fruit_HintFinderManager();
        mHintModifier = new Fruit_HintModifier(engine);
        mShuffleText = new Fruit_TextEffect(engine, Fruit_Textures.TEXT_SHUFFLE);
        mHintTimer = new Timer(engine);
        mHintTimer.addTimerEvent(new TimerEvent(this, HINT_TIMEOUT));
        mShuffleTimer = new Timer(engine);
        mShuffleTimer.addTimerEvent(new TimerEvent(this, SHUFFLE_TIMEOUT));
        mSoundTimer = new Timer(engine);
        mSoundTimer.addTimerEvent(new TimerEvent(this, SHUFFLE_SOUND_TIMEOUT));
        mSoundTimer.addTimerEvent(new TimerEvent(this, SLIDE_SOUND_TIMEOUT));
        mHintEnable = Fruit_Preferences.PREF_SETTING.getBoolean(Fruit_Preferences.KEY_HINT, true);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onStart() {
        mSoundTimer.start();
    }

    @Override
    public void onEvent(Event event) {
        switch ((Fruit_GameEvent) event) {
            case START_GAME:
                // Start hint if no tutorial
                if (Fruit_Level.LEVEL_DATA.getTutorialType() == Fruit_TutorialType.NONE) {
                    startHint();
                }
                break;
            case STOP_COMBO:
            case REMOVE_BOOSTER:
            case ADD_EXTRA_MOVES:
                startHint();
                break;
            case PLAYER_SWAP:
            case ADD_BOOSTER:
                stopHint();
                break;
            case GAME_WIN:
            case GAME_OVER:
                removeFromGame();
                break;
        }
    }

    @Override
    public void onTimerEvent(long eventTime) {
        if (eventTime == HINT_TIMEOUT) {
            showHintEffect();
        } else if (eventTime == SHUFFLE_TIMEOUT) {
            startHint();
        } else if (eventTime == SHUFFLE_SOUND_TIMEOUT) {
            Fruit_Sounds.TILE_SHUFFLE.play();
        } else if (eventTime == SLIDE_SOUND_TIMEOUT) {
            Fruit_Sounds.TILE_SLIDE.play();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void startHint() {
        mHintTiles = mHintFinder.findHint(mTiles, mTotalRow, mTotalCol);
        if (mHintTiles == null) {
            // Shuffle the tile if hint not found
            shuffleTile();
            return;
        }
        if (mHintEnable) {
            // Start timer and show effect if hint found
            mHintTimer.start();
        }
    }

    private void stopHint() {
        mHintTimer.stop();
        removeHintEffect();
    }

    private void showHintEffect() {
        mHintModifier.activate(mHintTiles);
    }

    private void removeHintEffect() {
        if (mHintModifier.isRunning()) {
            mHintModifier.removeFromGame();
        }
    }

    private void shuffleTile() {
        Fruit_Match3Algorithm.shuffleTile(mTiles, mTotalRow, mTotalCol);
        mSoundTimer.start();
        mShuffleTimer.start();
        mShuffleText.activate(Fruit_GameWorld.WORLD_WIDTH / 2f, Fruit_GameWorld.WORLD_HEIGHT / 2f);
    }
    //========================================================

}
