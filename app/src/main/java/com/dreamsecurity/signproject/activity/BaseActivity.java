package com.dreamsecurity.signproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dreamsecurity.signproject.AppApplication;

public class BaseActivity extends AppCompatActivity {

    private AppApplication appApplication;
    protected Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appApplication = (AppApplication)getApplicationContext();
        handler = new Handler();
    }

    public AppApplication getAppApplication() {
        return appApplication;
    }

    protected void setImmersiveMode(){
        getWindow().getDecorView().setSystemUiVisibility(0
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    protected void showToast(String text){
        Toast.makeText(this , text , Toast.LENGTH_SHORT).show();
    }

    protected String getEditToString(EditText editText){
        return editText.getText().toString().trim();
    }

}
