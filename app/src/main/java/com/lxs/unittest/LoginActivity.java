package com.lxs.unittest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText userName;
    private EditText passWord;
    private Button loginBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.login_username);
        passWord = (EditText) findViewById(R.id.login_password);
        loginBtn = (Button) findViewById(R.id.login_btn);
        resetBtn = (Button) findViewById(R.id.lgoin_reset_btn);
        loginBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                if (checkInput()) {
                    Intent intent = new Intent(LoginActivity.this, TestActivity.class);
                    intent.putExtra("userName", userName.getText().toString());
                    intent.putExtra("passWord", passWord.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "用户名或密码不可为空！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lgoin_reset_btn:
                userName.setText(null);
                passWord.setText(null);
                break;
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(userName.getText()) || TextUtils.isEmpty(passWord.getText())) {
            return false;
        }
        return true;
    }
}
