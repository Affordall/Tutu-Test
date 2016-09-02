package com.testapps.tututest.views;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Switch;

import com.testapps.tututest.R;
import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.common.BaseActivity;
import com.testapps.tututest.di.components.TutuTestAppComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseActivity {

    private static int checkedNotifyIndex;
    private static final String KEY_SWITCH_NOTIFY_INDEX = "SAVED_SWITCH_NOTIFY_INDEX";

    @Inject SharedPreferences sharedPreferences;

    @BindView(R.id.switch_intro_screen) Switch mSwitchIntro;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setUpSupportActionBar();
        initValues();
        LoadPreferences();
    }

    @Override
    protected void setupComponent(TutuTestAppComponent appComponent) {
        TutuTestApp.get(this).getAppComponent().inject(this);
    }

    private void setUpSupportActionBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.action_settings));
    }

    private void initValues() {
        mSwitchIntro.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkedNotifyIndex = 1;
            } else {
                checkedNotifyIndex = 0;
            }
            SavePreferences(KEY_SWITCH_NOTIFY_INDEX, checkedNotifyIndex);
        });
    }

    private void SavePreferences(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private void LoadPreferences() {
        int savedSwitchIndex = sharedPreferences.getInt(
                KEY_SWITCH_NOTIFY_INDEX, 1);
        if (savedSwitchIndex == 1) {
            mSwitchIntro.setChecked(true);
        } else {
            mSwitchIntro.setChecked(false);
        }

    }
}