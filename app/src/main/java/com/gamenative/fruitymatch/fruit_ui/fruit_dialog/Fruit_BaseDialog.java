package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameDialog;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public abstract class Fruit_BaseDialog extends GameDialog {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    protected Fruit_BaseDialog(GameActivity activity) {
        super(activity);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onShow() {
        Fruit_Sounds.DIALOG_SLIDE.play();
    }

    @Override
    protected void onDismiss() {
        Fruit_Sounds.DIALOG_SLIDE.play();
    }
    //========================================================

}
