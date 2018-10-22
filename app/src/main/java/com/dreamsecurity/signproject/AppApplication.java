package com.dreamsecurity.signproject;

import android.app.Application;

public class AppApplication extends Application {

    private String id;

    public static final String DOMAIN = "http://10.10.30.190:3000";
    // 푸시 관련 설정 키
    public static final String PUSH_TOKEN = "push_token";
    public static final String PUSH_CHECK = "push_check";

    // 회원 정보 관련 키
    public static final String EMAIL = "email";

    //잊어버린 패스워드 관련 키
    public static final String FORGOT = "forgot";
    public static final String SIGN = "sign";
    public static final String PASSWORD_SAVE = "pw_save";

    public static final String ID = "id";
    public static final String PW = "pw";



    // 처음 시작 작동 트리거
    public static final String FIRST_START = "first_start";

    public String getId() {
        return new String(id);
    }

    public void setId(String id) {
        this.id = id;
    }
}
