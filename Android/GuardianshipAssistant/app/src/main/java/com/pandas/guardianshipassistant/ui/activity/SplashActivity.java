package com.pandas.guardianshipassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.config.ConstantConfig;
import com.pandas.guardianshipassistant.utils.ThreadUtils;


public class SplashActivity extends AppCompatActivity {
//    private Handler handeler=new Handler(){
//        public  void handleMessage(Message msg){
//            //如果当前activaty已经退出，不处理handler的消息
//            if(isFinishing()){
//                return ;
//            }
//            //判断进入界面还是登录界面
//            toMainOrLogin();
//        }
//    };
//
//    private void toMainOrLogin() {
//        new  Thread(){
//            @Override
//            public void run() {
//                if()
//            }
//        }.start();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(ConstantConfig.SPALSHTIME);
//                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);

                startActivity(intent);
                finish();
            }
        });

    }


}
