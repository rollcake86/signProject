package com.dreamsecurity.signproject.activity;

import android.content.Intent;
import android.os.Bundle;

import com.dreamsecurity.signproject.R;
import com.rollcake.indi.indiplace.utils.AppkeyManager;

import static com.dreamsecurity.signproject.AppApplication.FIRST_START;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        setImmersiveMode();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(AppkeyManager.Companion.getKey(IntroActivity.this , FIRST_START , true)){
                    startActivity(new Intent(IntroActivity.this , HelpActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(IntroActivity.this , LoginActivity.class));
                    finish();
                }


            }
        }, 2000);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setImmersiveMode();
    }
}
