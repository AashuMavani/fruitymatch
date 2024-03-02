package com.gamenative.fruitymatch.fruit_game.counter;

import android.view.View;
import android.widget.TextView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_database.Fruit_DatabaseHelper;
import com.gamenative.fruitymatch.fruit_game.Fruit_GameEvent;
import com.gamenative.fruitymatch.fruit_game.booster.Fruit_BombController;
import com.gamenative.fruitymatch.fruit_game.booster.Fruit_BoosterController;
import com.gamenative.fruitymatch.fruit_game.booster.Fruit_GloveController;
import com.gamenative.fruitymatch.fruit_game.booster.Fruit_HammerController;
import com.gamenative.fruitymatch.fruit_game.layer.tile.Fruit_TileSystem;
import com.gamenative.fruitymatch.fruit_item.Fruit_Item;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.gamenative.fruitymatch.fruit_level.Fruit_TutorialType;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.entity.runnable.RunnableEntity;
import com.nativegame.natyengine.event.Event;
import com.nativegame.natyengine.event.EventListener;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_BoosterCounter extends RunnableEntity implements View.OnClickListener,
        EventListener, Fruit_BoosterController.BoosterListener {

    private static final String SIGN_INFINITE = "∞";
    private static final int INFINITE = -1;

    private final Fruit_HammerController mHammerController;
    private final Fruit_BombController mBombController;
    private final Fruit_GloveController mGloveController;

    private BoosterState mState;
    private int mHammerCount;
    private int mBombCount;
    private int mGloveCount;
    private boolean mIsEnable = false;
    private boolean mIsAddBooster = false;
    private boolean mIsRemoveBooster = false;

    private enum BoosterState {
        WAITING,
        HAMMER,
        BOMB,
        GLOVE
    }

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_BoosterCounter(GameActivity activity, Engine engine, Fruit_TileSystem tileSystem) {
        super(activity, engine);

        // Init booster controller
        mHammerController = new Fruit_HammerController(engine, tileSystem);
        mBombController = new Fruit_BombController(engine, tileSystem);
        mGloveController = new Fruit_GloveController(engine, tileSystem);
        mHammerController.setListener(this);
        mBombController.setListener(this);
        mGloveController.setListener(this);

        // Init booster count
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(activity);
        Fruit_TutorialType tutorialType = Fruit_Level.LEVEL_DATA.getTutorialType();   // Check is current level has Tutorial
        mHammerCount = tutorialType == Fruit_TutorialType.HAMMER ? INFINITE : databaseHelper.getItemCount(Fruit_Item.HAMMER);
        mBombCount = tutorialType == Fruit_TutorialType.BOMB ? INFINITE : databaseHelper.getItemCount(Fruit_Item.BOMB);
        mGloveCount = tutorialType == Fruit_TutorialType.GLOVE ? INFINITE : databaseHelper.getItemCount(Fruit_Item.GLOVE);

        // Init booster button
        GameButton btnHammer = (GameButton) mActivity.findViewById(R.id.btn_hammer);
        btnHammer.setOnClickListener(this);
        GameButton btnBomb = (GameButton) mActivity.findViewById(R.id.btn_bomb);
        btnBomb.setOnClickListener(this);
        GameButton btnGlove = (GameButton) mActivity.findViewById(R.id.btn_glove);
        btnGlove.setOnClickListener(this);

        // Init booster text
        updateText();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onStart() {
        mState = BoosterState.WAITING;
        setPostRunnable(true);
    }

    @Override
    public void onUpdate(long elapsedMillis) {
        if (mIsAddBooster) {
            addBooster();
            dispatchEvent(Fruit_GameEvent.ADD_BOOSTER);
            mIsAddBooster = false;
        }

        if (mIsRemoveBooster) {
            removeBooster();
            dispatchEvent(Fruit_GameEvent.REMOVE_BOOSTER);
            mState = BoosterState.WAITING;
            mIsRemoveBooster = false;
        }
    }

    @Override
    protected void onUpdateRunnable() {
        updateText();
        unLockButton();
    }

    @Override
    public void onClick(View view) {
        synchronized (this) {
            if (!mIsEnable) {
                return;
            }
            if (mState == BoosterState.WAITING) {
                int id = view.getId();
                if (id == R.id.btn_hammer) {
                    if (mHammerCount == 0) {
                        return;
                    }
                    mState = BoosterState.HAMMER;
                } else if (id == R.id.btn_bomb) {
                    if (mBombCount == 0) {
                        return;
                    }
                    mState = BoosterState.BOMB;
                } else if (id == R.id.btn_glove) {
                    if (mGloveCount == 0) {
                        return;
                    }
                    mState = BoosterState.GLOVE;
                }
                lockButton();
                mIsAddBooster = true;
            } else {
                unLockButton();
                mIsRemoveBooster = true;
            }
            Fruit_Sounds.BUTTON_CLICK.play();
        }
    }

    @Override
    public void onEvent(Event event) {
        switch ((Fruit_GameEvent) event) {
            case START_GAME:
            case STOP_COMBO:
            case ADD_EXTRA_MOVES:
                mIsEnable = true;
                break;
            case PLAYER_SWAP:
                mIsEnable = false;
                break;
            case GAME_WIN:
            case GAME_OVER:
                removeFromGame();
                break;
        }
    }

    @Override
    public void onConsumeBooster() {
        switch (mState) {
            case HAMMER:
                if (mHammerCount != INFINITE) {
                    mHammerCount--;
                    Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(mActivity);
                    databaseHelper.updateItemCount(Fruit_Item.HAMMER, mHammerCount);
                }
                break;
            case BOMB:
                if (mBombCount != INFINITE) {
                    mBombCount--;
                    Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(mActivity);
                    databaseHelper.updateItemCount(Fruit_Item.BOMB, mBombCount);
                }
                break;
            case GLOVE:
                if (mGloveCount != INFINITE) {
                    mGloveCount--;
                    Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(mActivity);
                    databaseHelper.updateItemCount(Fruit_Item.GLOVE, mGloveCount);
                }
                break;
        }
        setPostRunnable(true);
        mState = BoosterState.WAITING;
        mIsEnable = false;
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void addBooster() {
        switch (mState) {
            case HAMMER:
                mHammerController.addToGame();
                break;
            case BOMB:
                mBombController.addToGame();
                break;
            case GLOVE:
                mGloveController.addToGame();
                break;
        }
    }

    private void removeBooster() {
        switch (mState) {
            case HAMMER:
                mHammerController.removeFromGame();
                break;
            case BOMB:
                mBombController.removeFromGame();
                break;
            case GLOVE:
                mGloveController.removeFromGame();
                break;
        }
    }

    private void lockButton() {
        GameButton btnHammer = (GameButton) mActivity.findViewById(R.id.btn_hammer);
        GameButton btnBomb = (GameButton) mActivity.findViewById(R.id.btn_bomb);
        GameButton btnGlove = (GameButton) mActivity.findViewById(R.id.btn_glove);
        switch (mState) {
            case HAMMER:
                btnGlove.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnGlove.setEnabled(false);

                btnBomb.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnBomb.setEnabled(false);
                break;
            case BOMB:
                btnHammer.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnHammer.setEnabled(false);

                btnGlove.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnGlove.setEnabled(false);
                break;
            case GLOVE:
                btnHammer.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnHammer.setEnabled(false);

                btnBomb.addColorFilter(GameButton.DEFAULT_PRESS_COLOR);
                btnBomb.setEnabled(false);
                break;
        }
    }

    private void unLockButton() {
        GameButton btnHammer = (GameButton) mActivity.findViewById(R.id.btn_hammer);
        btnHammer.removeColorFilter();
        btnHammer.setEnabled(true);

        GameButton btnBomb = (GameButton) mActivity.findViewById(R.id.btn_bomb);
        btnBomb.removeColorFilter();
        btnBomb.setEnabled(true);

        GameButton btnGlove = (GameButton) mActivity.findViewById(R.id.btn_glove);
        btnGlove.removeColorFilter();
        btnGlove.setEnabled(true);
    }

    private void updateText() {
        TextView txtHammer = (TextView) mActivity.findViewById(R.id.txt_hammer);
        txtHammer.setText(mHammerCount == INFINITE ? SIGN_INFINITE : String.valueOf(mHammerCount));

        TextView txtBomb = (TextView) mActivity.findViewById(R.id.txt_bomb);
        txtBomb.setText(mBombCount == INFINITE ? SIGN_INFINITE : String.valueOf(mBombCount));

        TextView txtGlove = (TextView) mActivity.findViewById(R.id.txt_gloves);
        txtGlove.setText(mGloveCount == INFINITE ? SIGN_INFINITE : String.valueOf(mGloveCount));
    }
    //========================================================

}
