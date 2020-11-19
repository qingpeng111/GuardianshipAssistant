package com.pandas.guardianshipassistant.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardDeleteApp;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardFootprint;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardGame;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardMessageRecord;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardNews;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardNovel;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardOnlineReminder;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardRemoteLocation;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardRemoteLockScreen;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardRemoteRecordVideo;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardRemoteSoundRecord;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardRemoteTakePicture;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardRemoteTrace;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardSameScreen;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardScanApp;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardScanFile;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardScreenShot;
import com.pandas.guardianshipassistant.ui.activity.bottom.GuardVideo;
import com.pandas.guardianshipassistant.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;

public class FragmentBottomGuard extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_guard, container, false);

        return view;
    }

    //获取点击的
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        guardJumpActivity();


    }

    private void guardJumpActivity() {
        final Button btnGame = (Button) getActivity().findViewById(R.id.btn_game);
        final Button btnVideo = (Button) getActivity().findViewById(R.id.btn_video);
        final Button btnScanFile = (Button) getActivity().findViewById(R.id.btn_scan_file);
        final Button btnNovel = (Button) getActivity().findViewById(R.id.btn_novel);
        final Button btnNews = (Button) getActivity().findViewById(R.id.btn_news);
        final Button btnRemoteLockScreen = (Button) getActivity().findViewById(R.id.btn_remote_lock_screen);
        final Button btnSoundRecord = (Button) getActivity().findViewById(R.id.btn_sound_record);
        final Button btnMessageRecord = (Button) getActivity().findViewById(R.id.btn_message_record);
        final Button btnOnlineReminder = (Button) getActivity().findViewById(R.id.btn_online_reminder);
        final Button btnScreenShot = (Button) getActivity().findViewById(R.id.btn_screen_shot);
        final Button btnSameScreen = (Button) getActivity().findViewById(R.id.btn_same_screen);
        final Button btnScanApp = (Button) getActivity().findViewById(R.id.btn_scan_app);
        final Button btnDeleteApp = (Button) getActivity().findViewById(R.id.btn_delete_app);
        final Button btnRemoteRecordVideo = (Button) getActivity().findViewById(R.id.btn_remote_record_Video);
        final Button btnRemoteTakePicture = (Button) getActivity().findViewById(R.id.btn_remote_take_picture);
        final Button btnRemoteSoundRecord = (Button) getActivity().findViewById(R.id.btn_remote_sound_record);
        final Button btnRemoteTrace = (Button) getActivity().findViewById(R.id.btn_remote_trace);
        final Button btnRemoteLocation = (Button) getActivity().findViewById(R.id.btn_remote_location);
        final Button btnFootprint = (Button) getActivity().findViewById(R.id.btn_footprint);

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnGame.getText().toString());
                Intent intent = new Intent(getActivity(), GuardGame.class);
                startActivity(intent);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnVideo.getText().toString());
                Intent intent = new Intent(getActivity(), GuardVideo.class);
                startActivity(intent);
            }
        });

        btnScanFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnScanFile.getText().toString());
                Intent intent = new Intent(getActivity(), GuardScanFile.class);
                startActivity(intent);
            }
        });

        btnNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnNovel.getText().toString());
                Intent intent = new Intent(getActivity(), GuardNovel.class);
                startActivity(intent);
            }
        });

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnNews.getText().toString());
                Intent intent = new Intent(getActivity(), GuardNews.class);
                startActivity(intent);
            }
        });

        btnRemoteLockScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnRemoteLockScreen.getText().toString());
                Intent intent = new Intent(getActivity(), GuardRemoteLockScreen.class);
                startActivity(intent);
            }
        });

        btnSoundRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnSoundRecord.getText().toString());
                Intent intent = new Intent(getActivity(), GuardRemoteSoundRecord.class);
                startActivity(intent);
            }
        });

        btnMessageRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnMessageRecord.getText().toString());
                Intent intent = new Intent(getActivity(), GuardMessageRecord.class);
                startActivity(intent);
            }
        });

        btnOnlineReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnOnlineReminder.getText().toString());
                Intent intent = new Intent(getActivity(), GuardOnlineReminder.class);
                startActivity(intent);
            }
        });

        btnScreenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnScreenShot.getText().toString());
                screeShot();
                Intent intent = new Intent(getActivity(), GuardScreenShot.class);
                startActivity(intent);


            }
        });

        btnSameScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnSameScreen.getText().toString());
                Intent intent = new Intent(getActivity(), GuardSameScreen.class);
                startActivity(intent);
            }
        });

        btnScanApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnScanApp.getText().toString());
                Intent intent = new Intent(getActivity(), GuardScanApp.class);
                startActivity(intent);
            }
        });

        btnDeleteApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnDeleteApp.getText().toString());
                Intent intent = new Intent(getActivity(), GuardDeleteApp.class);
                startActivity(intent);
            }
        });

        btnRemoteRecordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnRemoteRecordVideo.getText().toString());
                Intent intent = new Intent(getActivity(), GuardRemoteRecordVideo.class);
                startActivity(intent);
            }
        });

        btnRemoteTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnRemoteTakePicture.getText().toString());
                Intent intent = new Intent(getActivity(), GuardRemoteTakePicture.class);
                startActivity(intent);
            }
        });

        btnRemoteSoundRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnRemoteSoundRecord.getText().toString());
                Intent intent = new Intent(getActivity(), GuardRemoteSoundRecord.class);
                startActivity(intent);
            }
        });

        btnRemoteTrace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnRemoteTrace.getText().toString());
                Intent intent = new Intent(getActivity(), GuardRemoteTrace.class);
                startActivity(intent);
            }
        });

        btnRemoteLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnRemoteLocation.getText().toString());
                Intent intent = new Intent(getActivity(), GuardRemoteLocation.class);
                startActivity(intent);
            }
        });

        btnFootprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastSafe(getContext(), btnFootprint.getText().toString());
                Intent intent = new Intent(getActivity(), GuardFootprint.class);
                startActivity(intent);
            }
        });

        //  这里使用按钮来测试相关方法
        final Button btnTest = (Button) getActivity().findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void screeShot() {
        View dView = getActivity().getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        if (bitmap != null) {//  bitmap: ""
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";// filePath: /storage/emulated/0/screenshot.png
                System.out.println(" 截图 ------------0----------------"+filePath);
                File file = new File(filePath);
                System.out.println(" 截图 ------------1----------------"+file);
                FileOutputStream os = new FileOutputStream(file);
                System.out.println(" 截图  ------------2----------------");
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                System.out.println(" 截图  ------------3----------------");
                os.flush();
                os.close();
            } catch (Exception e) {
            }
        }

    }

    /**
     * 函数功能 ：  测试函数
     * 函数逻辑 ：
     *
     * @param
     * @创建者 ： 青鹏
     * @QQ ： 260498491
     * @date ： 2020/11/17 22:43
     */
    private void test() {
        ToastUtils.showToastSafe(getContext(), "点击测试方法");
//        View dView = getWindow().getDecorView();
//        dView.setDrawingCacheEnabled(true);
//        dView.buildDrawingCache();
//        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
//        if (bitmap != null) {
//            try {
//                // 获取内置SD卡路径
//                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
//                // 图片文件路径
//                String filePath = sdCardPath + File.separator + "screenshot.png";
//                File file = new File(filePath);
//                FileOutputStream os = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
//                os.flush();
//                os.close();
//                ToastUtils.showToastSafe(getContext(),"截屏完成");
//            } catch (Exception e) {
//            }
//        }
    }


}
