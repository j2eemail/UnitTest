package com.lxs.unittest;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/5/3.
 */

public class TestLoginActivity extends ActivityUnitTestCase<LoginActivity> {

    private LoginActivity loginActivity;
    private Intent mIntent;
    private EditText userName;
    private EditText passWord;
    private Button loginBtn;
    private Button resetBtn;

    public TestLoginActivity() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mIntent = new Intent(getInstrumentation().getTargetContext(), LoginActivity.class);
        startActivity(mIntent, null, null);
        loginActivity = getActivity();
        userName = (EditText) loginActivity.findViewById(R.id.login_username);
        passWord = (EditText) loginActivity.findViewById(R.id.login_password);
        loginBtn = (Button) loginActivity.findViewById(R.id.login_btn);
        resetBtn = (Button) loginActivity.findViewById(R.id.lgoin_reset_btn);
    }

    /**
     * 测试Activity对象及组件是否为空
     */
    public void testIsNull() {
        assertNotNull(loginActivity);
        assertNotNull(userName);
        assertNotNull(passWord);
        assertNotNull(loginBtn);
        assertNotNull(resetBtn);
    }

    public void inputTxt() {
        userName.setText("lxs");
        passWord.setText("123");
    }

    public void testIsTxt() {
        inputTxt();
        assertEquals("lxs", userName.getText().toString());
        assertEquals("123", passWord.getText().toString());
    }

    //测试重置按钮
    public void testReset() {
        inputTxt();
        resetBtn.performClick();
        //验证重置按钮的实现功能，是否点击后内容为空
        assertEquals("", userName.getText().toString());
        assertEquals("", passWord.getText().toString());
    }

    //测试登录按钮
    public void testLogin() {
        inputTxt();
        loginBtn.performClick();

        Intent intent = getStartedActivityIntent();
        intent.putExtra("userName", userName.getText().toString());
        intent.putExtra("passWord", passWord.getText().toString());
        // 去判断是否为空,如果为空就说明跳转失败
        assertNotNull("Intent was null", intent);
        // 这一句是判断你在跳转后有没调finish()
        assertTrue(isFinishCalled());
    }
}