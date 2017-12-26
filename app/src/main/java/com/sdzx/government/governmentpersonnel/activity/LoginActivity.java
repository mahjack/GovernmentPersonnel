package com.sdzx.government.governmentpersonnel.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sdzx.government.governmentpersonnel.R;
import com.sdzx.government.governmentpersonnel.database.DBHelper;
import com.sdzx.government.governmentpersonnel.permission.PermissionsActivity;
import com.sdzx.government.governmentpersonnel.permission.PermissionsChecker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements  View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private static final String TAG = "LoginActivity";
    private Context mContext;
    // 登录名、密码
    @Bind(R.id.login_username)
    EditText et_login_username;
    @Bind(R.id.login_password)
    EditText et_login_password;
    @Bind(R.id.login_hide_display_password)
    ImageButton login_hide_display_password;
    @Bind(R.id.login_check)
    CheckBox login_check;
    // 登陆
    @Bind(R.id.login_btn)
    Button login_btn;
    @Bind(R.id.btchange) Button btchange;
    private ProgressDialog progressDialog;
    // 对象
//    private ECProgressDialog mPostingDialog;//容联云通讯
    private SharedPreferences mSharedPreferences;

    private boolean mDisplayFlg = false;
    private static final int REQUEST_CODE = 0;
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (MyApp.isPad(this)){
            setContentView(R.layout.activity_login_m);//判断是否是PAD
//        }else {
//            setContentView(R.layout.activity_login_m);
//        }
        // 隐藏键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);
        initConstants();
//        类 类名=new 类(1,"号");
//        int aa=类名.get个数();
        mPermissionsChecker = new PermissionsChecker(this);//权限检测
        initData();
        initEvent();

    }
    @OnClick(R.id.btchange)
    public void setBtchange(View v){
        startActivity(new Intent(LoginActivity.this,ChangePwdActivity.class));
    }


    private void initData() {

        mSharedPreferences= getSharedPreferences("zxkj", Activity.MODE_PRIVATE);
        Boolean isFirstUse = mSharedPreferences.getBoolean("isFirstUse", true);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (isFirstUse) {//第一次使用建表，本地存储
//            db.createTable(PeopleInfo.class);//人员信息表
//            editor = mSharedPreferences.edit();
            editor.putString("USER_NAME", "市政府");
            editor.putString("PASSWORD", "1234");
            editor.putBoolean("isFirstUse",false);
            editor.commit();
        }
        et_login_password.setSelection(0);
//        mSharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);
        // CheckBox
        if (mSharedPreferences.getBoolean("ISCHECKED", false)) {
            // 默认记住密码
            login_check.setChecked(true);
            et_login_username.setText(mSharedPreferences.getString("USER_NAME", ""));
            et_login_password.setText(mSharedPreferences.getString("PASSWORD", ""));
            et_login_password.setSelection((mSharedPreferences.getString("PASSWORD", "")).length());// 设置光标位置在文字最后
            et_login_username.setSelection((mSharedPreferences.getString("USER_NAME", "")).length());
        }
    }

    /**上下文*/
    private void initConstants() {
        mContext = LoginActivity.this;
    }

    private void initEvent() {
        login_hide_display_password.setOnClickListener(this);
        login_check.setOnCheckedChangeListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_hide_display_password:
                if (!mDisplayFlg) {
                    et_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    login_hide_display_password.setSelected(true);
                } else {
                    et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    login_hide_display_password.setSelected(false);
                }
                mDisplayFlg = !mDisplayFlg;
                break;

            case R.id.login_btn:
                if (checkInput()) {
//                    mPostingDialog = new ECProgressDialog(this, R.string.login_posting);//容联云Dialog
                    login();
                }
                break;
        }
    }

    /**
     * 检查帐号密码是否为空
     * @return
     */
    private boolean checkInput() {
        if (et_login_username.getText().toString() == null || et_login_username.getText().toString().equals("")) {
            Toast.makeText(mContext, "请输入用户名！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_login_password.getText().toString() == null || et_login_password.getText().toString().equals("")) {
            Toast.makeText(mContext, "请输入密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void login() {

        String name=mSharedPreferences.getString("USER_NAME","");
        String pwd=mSharedPreferences.getString("PASSWORD", "");
        if (name.equals(et_login_username.getText().toString())&&pwd.equals(et_login_password.getText().toString())){

            if (login_check.isChecked()) {
                SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                mEditor.putString("USER_NAME", et_login_username.getText().toString());
                mEditor.putString("PASSWORD", et_login_password.getText().toString());
                mEditor.commit();
            }
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(mContext, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
        }
//        OkHttpUtils
//                .post()
//                .url(ConstantURL.URL_LOGIN)
//                .addParams("username", et_login_username.getText().toString())
//                .addParams("password", et_login_password.getText().toString())
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        Toast.makeText(mContext, "未知错误！请检查您的网络！", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject=new JSONObject(response);
//                            int state=jsonObject.getInt("state");
//                            if (state==5){
//                                if (login_check.isChecked()) {
//                                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
//                                    mEditor.putString("USER_NAME", et_login_username.getText().toString());
//                                    mEditor.putString("PASSWORD", et_login_password.getText().toString());
//                                    mEditor.commit();
//                                }
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                MyApp.UID=jsonObject.getString("uid");
//                                MyApp.UNAME=jsonObject.getString("name");
//                                finish();
//                            }else if(state==2){
//                                Toast.makeText(mContext, "用户名不存在!", Toast.LENGTH_SHORT).show();
//                            }else if(state==3){
//                                Toast.makeText(mContext, "口令不正确!", Toast.LENGTH_SHORT).show();
//                            }else if(state==4){
//                                Toast.makeText(mContext, "账号被锁定!", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
    }

    /**
     * 检查CheckBox是否选中
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mSharedPreferences.edit().putBoolean("ISCHECKED", true).commit();
        } else {
            mSharedPreferences.edit().putBoolean("ISCHECKED", false).commit();
        }
    }
    private void showProgressDialog(String str) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(str);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == keyCode) {

                }
                return false;
            }
        });
        progressDialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        }


    }
}
