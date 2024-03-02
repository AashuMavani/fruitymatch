package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_MoreMoveDialog extends Fruit_BaseDialog implements View.OnClickListener {

    private int mSelectedId = R.id.btn_cancel;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_MoreMoveDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_more_move);
        setContainerView(R.layout.fruit_dialog_container);
        setEnterAnimationId(R.anim.fruit_enter_from_center);
        setExitAnimationId(R.anim.fruit_exit_to_center);

        // Init image
        GameImage imageExtraMove = (GameImage) findViewById(R.id.image_extra_move);
        imageExtraMove.popUp(200, 300);

        // Init text
        GameText txtExtraMove = (GameText) findViewById(R.id.txt_extra_move);
        txtExtraMove.popUp(200, 500);

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
            showAd();
        } else if (mSelectedId == R.id.btn_cancel) {
            quit();
        }
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            mSelectedId = id;
            dismiss();
        } else if (id == R.id.btn_watch_ad) {
            mSelectedId = id;
            dismiss();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void showAd() {
    }

    public void quit() {
    }
    //========================================================

}
