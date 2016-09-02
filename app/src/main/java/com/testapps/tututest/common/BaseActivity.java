package com.testapps.tututest.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.di.components.TutuTestAppComponent;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(TutuTestApp.get(this).getAppComponent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    protected abstract void setupComponent(TutuTestAppComponent appComponent);
}
