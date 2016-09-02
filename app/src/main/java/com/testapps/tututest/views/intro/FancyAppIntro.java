package com.testapps.tututest.views.intro;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.testapps.tututest.R;
import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.views.MainActivity;

import javax.inject.Inject;

public class FancyAppIntro extends AppIntro {

    private static final String KEY_SWITCH_NOTIFY_INDEX = "SAVED_SWITCH_NOTIFY_INDEX";

    @Inject SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TutuTestApp.get(this).getAppComponent().inject(this);

        int showSplashScreen = sharedPreferences.getInt(KEY_SWITCH_NOTIFY_INDEX, 1);
        if (showSplashScreen == 0) {
            finishThisAndRunMain();
        }

        /** Welcome */
        addSlide(AppIntroFragment.newInstance(
                "Welcome",
                getString(R.string.app_name),
                R.drawable.web_icon,
                ContextCompat.getColor(this, R.color.colorPrimary)));

        addSlide(AppIntroFragment.newInstance(
                "Fancy",
                "Fully cool material design",
                R.drawable.design_slide,
                ContextCompat.getColor(this, R.color.colorPrimaryDark)));

        // Hide Skip/Done button.
        showSkipButton(false);
        setFadeAnimation();
        setProgressButtonEnabled(true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SavePreferences(KEY_SWITCH_NOTIFY_INDEX,1);
        finishThisAndRunMain();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void SavePreferences(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private void finishThisAndRunMain() {
        Intent intent = new Intent(FancyAppIntro.this,
                MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        this.finish();
    }
}

