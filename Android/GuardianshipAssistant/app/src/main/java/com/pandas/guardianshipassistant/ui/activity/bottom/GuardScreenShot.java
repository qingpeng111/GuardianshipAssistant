package com.pandas.guardianshipassistant.ui.activity.bottom;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.ui.methods.MyDialog;
import com.pandas.guardianshipassistant.ui.methods.ScreenShotListenManager;

public class GuardScreenShot extends AppCompatActivity {
    private Context mContext;
    private ScreenShotListenManager screenShotListenManager;
    private boolean isHasScreenShotListener = false;
    private String path;
    private ImageView screenShotIv;
    private ProgressBar progressBar;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_screen_shot);
        System.out.println();
        screenShotListenManager = ScreenShotListenManager.newInstance(this);

        System.out.println("-------------------3------------------");
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("-------------------4------------------");
        startScreenShotListen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("-------------------5-----------------");
        stopScreenShotListen();
    }

    /**
     * 监听
     */
    private void startScreenShotListen() {
        if (!isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.setListener(new ScreenShotListenManager.OnScreenShotListener() {
                @Override
                public void onShot(String imagePath) {
                    System.out.println("-----------------1--------------------");
                    path = imagePath;
                    Log.d("msg", "BaseActivity -> onShot: " + "获得截图路径：" + imagePath);

                    MyDialog ksDialog = MyDialog.getInstance()
                            .init(GuardScreenShot.this, R.layout.dialog_layout)
                            .setCancelButton("取消", null)
                            .setPositiveButton("生成新图片", new MyDialog.OnClickListener() {
                                @Override
                                public void OnClick(View view) {
                                    Bitmap screenShotBitmap = screenShotListenManager.createScreenShotBitmap(mContext, path);
                                    System.out.println("-----------------2--------------------");
                                    // 此处只要分享这个合成的Bitmap图片就行了
                                    // 为了演示，故写下面代码
                                    screenShotIv.setImageBitmap(screenShotBitmap);
                                }
                            });

                    screenShotIv = (ImageView) ksDialog.getView(R.id.iv);
                    progressBar = (ProgressBar) ksDialog.getView(R.id.avLoad);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            Glide.with(mContext).load(path).into(screenShotIv);

                        }
                    }, 1500);
                }
            });
            screenShotListenManager.startListen();
            isHasScreenShotListener = true;
        }
    }

    /**
     * 停止监听
     */
    private void stopScreenShotListen() {
        if (isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.stopListen();
            System.out.println("-------------------6------------------");
            isHasScreenShotListener = false;
        }
    }

}
