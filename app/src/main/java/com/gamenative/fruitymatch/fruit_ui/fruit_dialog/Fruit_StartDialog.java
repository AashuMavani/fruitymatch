package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;
import android.widget.TextView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.gamenative.fruitymatch.fruit_level.Fruit_TargetType;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameImage;
import com.nativegame.natyengine.ui.GameText;
import com.nativegame.natyengine.util.ResourceUtils;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_StartDialog extends Fruit_BaseDialog {

    private static final long TIME_TO_LIVE = 1500;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_StartDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_start);
        setContainerView(R.layout.fruit_dialog_container_game);
        setEnterAnimationId(R.anim.fruit_enter_from_top);
        setExitAnimationId(R.anim.fruit_exit_to_bottom);

        // Init text
        TextView txtLevel = (TextView) findViewById(R.id.txt_level);
        txtLevel.setText(ResourceUtils.getString(activity, R.string.txt_level, Fruit_Level.LEVEL_DATA.getLevel()));

        initTargetImage();
        initTargetText();

        // Dismiss the dialog after 1500ms
        getContentView().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, TIME_TO_LIVE);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void initTargetImage() {
        List<Fruit_TargetType> targetTypes = Fruit_Level.LEVEL_DATA.getTargetTypes();
        // Init target image from TargetType
        GameImage imageTargetA = (GameImage) findViewById(R.id.image_target_01);
        GameImage imageTargetB = (GameImage) findViewById(R.id.image_target_02);
        GameImage imageTargetC = (GameImage) findViewById(R.id.image_target_03);
        switch (targetTypes.size()) {
            case 1:
                imageTargetB.setImageResource(targetTypes.get(0).getDrawableId());
                imageTargetA.setVisibility(View.GONE);
                imageTargetB.setVisibility(View.VISIBLE);
                imageTargetC.setVisibility(View.GONE);
                break;
            case 2:
                imageTargetA.setImageResource(targetTypes.get(0).getDrawableId());
                imageTargetC.setImageResource(targetTypes.get(1).getDrawableId());
                imageTargetA.setVisibility(View.VISIBLE);
                imageTargetB.setVisibility(View.GONE);
                imageTargetC.setVisibility(View.VISIBLE);
                break;
            case 3:
                imageTargetA.setImageResource(targetTypes.get(0).getDrawableId());
                imageTargetB.setImageResource(targetTypes.get(1).getDrawableId());
                imageTargetC.setImageResource(targetTypes.get(2).getDrawableId());
                imageTargetA.setVisibility(View.VISIBLE);
                imageTargetB.setVisibility(View.VISIBLE);
                imageTargetC.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initTargetText() {
        List<Integer> targetTypes = Fruit_Level.LEVEL_DATA.getTargetCounts();
        // Init target image from TargetType
        GameText txtTargetA = (GameText) findViewById(R.id.txt_target_01);
        GameText txtTargetB = (GameText) findViewById(R.id.txt_target_02);
        GameText txtTargetC = (GameText) findViewById(R.id.txt_target_03);
        switch (targetTypes.size()) {
            case 1:
                txtTargetB.setText(String.valueOf(targetTypes.get(0)));
                txtTargetA.setVisibility(View.GONE);
                txtTargetB.setVisibility(View.VISIBLE);
                txtTargetC.setVisibility(View.GONE);
                break;
            case 2:
                txtTargetA.setText(String.valueOf(targetTypes.get(0)));
                txtTargetC.setText(String.valueOf(targetTypes.get(1)));
                txtTargetA.setVisibility(View.VISIBLE);
                txtTargetB.setVisibility(View.GONE);
                txtTargetC.setVisibility(View.VISIBLE);
                break;
            case 3:
                txtTargetA.setText(String.valueOf(targetTypes.get(0)));
                txtTargetB.setText(String.valueOf(targetTypes.get(1)));
                txtTargetC.setText(String.valueOf(targetTypes.get(2)));
                txtTargetA.setVisibility(View.VISIBLE);
                txtTargetB.setVisibility(View.VISIBLE);
                txtTargetC.setVisibility(View.VISIBLE);
                break;
        }
    }
    //========================================================

}
