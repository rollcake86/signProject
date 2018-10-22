package com.dreamsecurity.signproject.activity.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dreamsecurity.signproject.AppApplication;

public class BaseFragment extends Fragment {

    protected AppApplication appApplication;
    protected Handler handler;


    protected void showToast(String text){
        Toast.makeText(getContext() , text , Toast.LENGTH_SHORT).show();
    }

    protected String getEditToString(EditText editText){
        return editText.getText().toString().trim();
    }




}
