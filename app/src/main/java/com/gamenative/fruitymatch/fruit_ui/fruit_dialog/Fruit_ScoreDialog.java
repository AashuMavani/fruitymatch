package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_database.Fruit_DatabaseHelper;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.util.ResourceUtils;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_ScoreDialog extends Fruit_BaseDialog implements View.OnClickListener {

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_ScoreDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_score);
        setContainerView(R.layout.fruit_dialog_container_game);
        setEnterAnimationId(R.anim.fruit_enter_from_left);
        setExitAnimationId(android.R.anim.fade_out);

        // Init text
        TextView txtLevel = (TextView) findViewById(R.id.txt_level);
        txtLevel.setText(ResourceUtils.getString(activity, R.string.txt_level, Fruit_Level.LEVEL_DATA.getLevel()));

        // Init button
        GameButton button = (GameButton) findViewById(R.id.btn_next);
        button.setOnClickListener(this);
        button.popUp(300, 600);

        initScore();
        initStar();
        insertOrUpdateStar();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onDismiss() {
        mParent.navigateBack();
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_next) {
            dismiss();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void initScore() {
        int score = Fruit_Level.LEVEL_DATA.getScore();

        // Update score text
        TextView txtScore = (TextView) findViewById(R.id.txt_final_score);
        ValueAnimator animator = ValueAnimator.ofFloat(score - 150, score);
        animator.setDuration(1500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                txtScore.setText(String.valueOf((int) value));
            }
        });
        animator.start();
        Fruit_Sounds.ADD_SCORE.play();
    }

    private void initStar() {
        int star = Fruit_Level.LEVEL_DATA.getStar();

        // Update star image
        StarAnimationListener listener = new StarAnimationListener();
        if (star >= 1) {
            Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_star_show);
            animation.setAnimationListener(listener);
            animation.setStartOffset(600);
            ImageView view = (ImageView) findViewById(R.id.image_star_01);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
        if (star >= 2) {
            Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_star_show);
            animation.setAnimationListener(listener);
            animation.setStartOffset(900);
            ImageView view = (ImageView) findViewById(R.id.image_star_02);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
        if (star >= 3) {
            Animation animation = AnimationUtils.loadAnimation(mParent, R.anim.fruit_star_show);
            animation.setAnimationListener(listener);
            animation.setStartOffset(1200);
            ImageView view = (ImageView) findViewById(R.id.image_star_03);
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
    }

    private void insertOrUpdateStar() {
        Fruit_DatabaseHelper databaseHelper = Fruit_DatabaseHelper.getInstance(mParent);
        int level = Fruit_Level.LEVEL_DATA.getLevel();
        int star = Fruit_Level.LEVEL_DATA.getStar();
        int oldStar = databaseHelper.getLevelStar(level);

        if (oldStar == -1) {
            // If data doesn't exist, we add one
            databaseHelper.insertLevelStar(star);
        } else {
            // If data exist and new star is bigger, we update
            if (star > oldStar) {
                databaseHelper.updateLevelStar(level, star);
            }
        }

    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    private static class StarAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Fruit_Sounds.ADD_STAR.play();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }
    //========================================================

}
