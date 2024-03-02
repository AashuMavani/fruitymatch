package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.ui.GameText;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ExitDialog extends Fruit_BaseDialog implements View.OnClickListener {

    private int mSelectedId;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ExitDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_exit);
        setContainerView(R.layout.fruit_dialog_container);
        setEnterAnimationId(R.anim.fruit_enter_from_center);
        setExitAnimationId(R.anim.fruit_exit_to_center);

        // Init text
        GameText txtExit = (GameText) findViewById(R.id.txt_exit);
        txtExit.popUp(200, 300);

        // Init button
        GameButton btnExit = (GameButton) findViewById(R.id.btn_exit);
        btnExit.popUp(200, 500);
        btnExit.setOnClickListener(this);

        GameButton btnCancel = (GameButton) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_exit) {
            exit();
        }
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            dismiss();
        } else if (id == R.id.btn_exit) {
            mSelectedId = id;
            dismiss();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void exit() {
    }
    //========================================================

}
