package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_item.prize.Fruit_Prize;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_NewPrizeDialog extends Fruit_BaseDialog implements View.OnClickListener {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_NewPrizeDialog(GameActivity activity, Fruit_Prize prize) {
        super(activity);
        setContentView(R.layout.fruit_dialog_new_prize);
        setContainerView(R.layout.fruit_dialog_container);

        // Init prize image
        ImageView imagePrize = (ImageView) findViewById(R.id.image_prize);
        imagePrize.setImageResource(prize.getDrawableId());

        // Init prize bg image
        ImageView imagePrizeBg = (ImageView) findViewById(R.id.image_prize_bg);
        Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_logo_rotate);
        imagePrizeBg.startAnimation(animation);

        // Init button
        GameButton btnNext = (GameButton) findViewById(R.id.btn_next);
        btnNext.popUp(200, 300);
        btnNext.setOnClickListener(this);

        Fruit_Sounds.GAME_WIN.play();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_next) {
            dismiss();
        }
    }
    //========================================================

}

