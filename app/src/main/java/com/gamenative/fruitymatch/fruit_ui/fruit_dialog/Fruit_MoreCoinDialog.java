package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;

import com.gamenative.fruitymatch.Fruit_MainActivity;
import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_ad.Fruit_AdManager;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_database.Fruit_DatabaseHelper;
import com.gamenative.fruitymatch.fruit_item.Fruit_Item;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_MoreCoinDialog extends Fruit_BaseDialog implements View.OnClickListener, Fruit_AdManager.AdRewardListener {

    private static final int REWARD_COIN = 50;

    private int mSelectedId;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_MoreCoinDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_more_coin);
        setContainerView(R.layout.fruit_dialog_container);
        setEnterAnimationId(R.anim.fruit_enter_from_center);
        setExitAnimationId(R.anim.fruit_exit_to_center);

        // Init image
        GameImage imageCoin = (GameImage) findViewById(R.id.image_coin);
        imageCoin.popUp(200, 300);

        // Init text
        GameText txtCoin = (GameText) findViewById(R.id.txt_coin);
        txtCoin.popUp(200, 500);

        // Init button
        GameButton btnWatchAd = (GameButton) findViewById(R.id.btn_watch_ad);
        btnWatchAd.popUp(200, 700);
        btnWatchAd.setOnClickListener(this);

        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_watch_ad) {
            showRewardAd();
        }
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            dismiss();
        } else if (id == R.id.btn_watch_ad) {
            mSelectedId = id;
            dismiss();
        }
    }

    @Override
    public void onEarnReward() {
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(mParent);
        // Update coin from db
        int saving = databaseHelper.getItemCount(Fruit_Item.COIN);
        databaseHelper.updateItemCount(Fruit_Item.COIN, saving + REWARD_COIN);
        updateCoin();
    }

    @Override
    public void onLossReward() {
        // We do nothing
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void showRewardAd() {
        Fruit_AdManager adManager = ((Fruit_MainActivity) mParent).getAdManager();
        adManager.setListener(this);
        boolean isConnect = adManager.showRewardAd();
        // Show error dialog if no internet connect
        if (!isConnect) {
            Fruit_ErrorDialog dialog = new Fruit_ErrorDialog(mParent) {
                @Override
                public void retry() {
                    adManager.requestAd();
                    showRewardAd();
                }
            };
            mParent.showDialog(dialog);
        }
    }

    public void updateCoin() {
    }
    //========================================================

}
