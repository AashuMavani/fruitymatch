package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import com.gamenative.fruitymatch.Fruit_MainActivity;
import com.gamenative.fruitymatch.R;
import com.nativegame.natyengine.ui.GameActivity;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_LossDialog extends Fruit_BaseDialog {

    private static final long TIME_TO_LIVE = 1500;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_LossDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_loss);
        setContainerView(R.layout.fruit_dialog_container_game);
        setEnterAnimationId(R.anim.fruit_enter_from_top);
        setExitAnimationId(R.anim.fruit_exit_to_bottom);

        // Reduce one live
        ((Fruit_MainActivity) mParent).getLivesTimer().reduceLive();

        // Dismiss the dialog after 1500ms
        getContentView().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, TIME_TO_LIVE);
    }
    //========================================================

}
