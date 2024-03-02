package com.gamenative.fruitymatch.fruit_ui.fruit_dialog;

import android.view.View;
import android.widget.TextView;

import com.gamenative.fruitymatch.R;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Preferences;
import com.gamenative.fruitymatch.fruit_asset.Fruit_Sounds;
import com.gamenative.fruitymatch.fruit_level.Fruit_Level;
import com.nativegame.natyengine.audio.music.MusicManager;
import com.nativegame.natyengine.audio.sound.SoundManager;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameButton;
import com.nativegame.natyengine.util.ResourceUtils;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class Fruit_PauseDialog extends Fruit_BaseDialog implements View.OnClickListener {

    private int mSelectedId = R.id.btn_resume;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public Fruit_PauseDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.fruit_dialog_pause);
        setContainerView(R.layout.fruit_dialog_container);
        setEnterAnimationId(R.anim.fruit_enter_from_center);
        setExitAnimationId(R.anim.fruit_exit_to_center);

        // Init text
        TextView txtLevel = (TextView) findViewById(R.id.txt_level);
        txtLevel.setText(ResourceUtils.getString(activity, R.string.txt_level, Fruit_Level.LEVEL_DATA.getLevel()));

        // Init button
        GameButton btnMusic = (GameButton) findViewById(R.id.btn_music);
        btnMusic.popUp(200, 300);
        btnMusic.setOnClickListener(this);

        GameButton btnSound = (GameButton) findViewById(R.id.btn_sound);
        btnSound.popUp(200, 400);
        btnSound.setOnClickListener(this);

        GameButton btnQuit = (GameButton) findViewById(R.id.btn_quit);
        btnQuit.popUp(200, 500);
        btnQuit.setOnClickListener(this);

        GameButton btnResume = (GameButton) findViewById(R.id.btn_resume);
        btnResume.setOnClickListener(this);

        updateMusicButton();
        updateSoundButton();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_quit) {
            quitGame();
        } else if (mSelectedId == R.id.btn_resume) {
            resumeGame();
        }
    }

    @Override
    public void onClick(View view) {
        Fruit_Sounds.BUTTON_CLICK.play();
        int id = view.getId();
        if (id == R.id.btn_music) {
            toggleMusic();
            updateMusicButton();
        } else if (id == R.id.btn_sound) {
            toggleSound();
            updateSoundButton();
        } else if (id == R.id.btn_quit) {
            mSelectedId = id;
            dismiss();
        } else if (id == R.id.btn_resume) {
            mSelectedId = id;
            dismiss();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void toggleMusic() {
        MusicManager musicManager = mParent.getMusicManager();
        boolean enable = musicManager.isAudioEnable();
        musicManager.setAudioEnable(!enable);
        // Save to Preference
        Fruit_Preferences.PREF_SETTING.putBoolean(Fruit_Preferences.KEY_MUSIC, !enable);
    }

    private void toggleSound() {
        SoundManager soundManager = mParent.getSoundManager();
        boolean enable = soundManager.isAudioEnable();
        soundManager.setAudioEnable(!enable);
        // Save to Preference
        Fruit_Preferences.PREF_SETTING.putBoolean(Fruit_Preferences.KEY_SOUND, !enable);
    }

    private void updateMusicButton() {
        boolean enable = mParent.getMusicManager().isAudioEnable();
        GameButton btnMusic = (GameButton) findViewById(R.id.btn_music);
        if (enable) {
            btnMusic.setBackgroundResource(R.drawable.fruit_ui_btn_music_on);
        } else {
            btnMusic.setBackgroundResource(R.drawable.fruit_ui_btn_music_off);
        }
    }

    private void updateSoundButton() {
        boolean enable = mParent.getSoundManager().isAudioEnable();
        GameButton btnSound = (GameButton) findViewById(R.id.btn_sound);
        if (enable) {
            btnSound.setBackgroundResource(R.drawable.fruit_ui_btn_sound_on);
        } else {
            btnSound.setBackgroundResource(R.drawable.fruit_ui_btn_sound_off);
        }
    }

    public void quitGame() {
    }

    public void resumeGame() {
    }
    //========================================================

}
