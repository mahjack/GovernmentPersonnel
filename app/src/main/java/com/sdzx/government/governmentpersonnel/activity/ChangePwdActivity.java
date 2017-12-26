package com.sdzx.government.governmentpersonnel.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdzx.government.governmentpersonnel.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
public class ChangePwdActivity extends Activity{
    @Bind(R.id.tv_ymm)
    EditText tv_ymm;
    @Bind(R.id.tv_xmm)
    EditText tv_xmm;
    @Bind(R.id.tv_cxmm)
    EditText tv_cxmm;
    @Bind(R.id.button_change)
    Button button_change;
    @Bind(R.id.header_back)
    TextView header_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        // 隐藏键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ButterKnife.bind(this);
        final SharedPreferences mSharedPreferences= getSharedPreferences("zxkj", Activity.MODE_PRIVATE);
        final String pwd=mSharedPreferences.getString("PASSWORD", "");
        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInput()){
                    if (pwd.equals(tv_ymm.getText().toString())){
                        if ((tv_xmm.getText().toString()).equals(tv_cxmm.getText().toString())){
                            Toast.makeText(ChangePwdActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                            mEditor.putString("PASSWORD", tv_xmm.getText().toString());
                            mEditor.commit();
                            finish();
                        }else{
                            Toast.makeText(ChangePwdActivity.this, "两次新密码不相同", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ChangePwdActivity.this, "旧密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    /**
     * 检查帐号密码是否为空
     * @return
     */
    private boolean checkInput() {
        if (tv_ymm.getText().toString() == null || tv_ymm.getText().toString().equals("")) {
            Toast.makeText(ChangePwdActivity.this, "请输入原密码！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (tv_xmm.getText().toString() == null || tv_xmm.getText().toString().equals("")) {
            Toast.makeText(ChangePwdActivity.this, "请输入新密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (tv_cxmm.getText().toString() == null || tv_cxmm.getText().toString().equals("")) {
            Toast.makeText(ChangePwdActivity.this, "请输入重复密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
