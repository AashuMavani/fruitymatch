package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_item.product.Fruit_Product;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_NewBoosterDialog extends Fruit_BaseDialog implements View.OnClickListener {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_NewBoosterDialog(GameActivity activity, Fruit_Product product) {
        super(activity);
        setContentView(R.layout.fruit_dialog_new_booster);
        setContainerView(R.layout.fruit_dialog_container);

        // Init booster image
        ImageView imageBooster = (ImageView) findViewById(R.id.image_booster);
        imageBooster.setImageResource(product.getDrawableId());

        // Init booster bg image
        ImageView imageBoosterBg = (ImageView) findViewById(R.id.image_booster_bg);
        Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_logo_rotate);
        imageBoosterBg.startAnimation(animation);

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
