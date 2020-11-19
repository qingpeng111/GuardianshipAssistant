package com.pandas.guardianshipassistant.utils;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {
    /**
     * 在子线程当中使用Toast方法
     *
     * @param context
     * @param text
     */
    public static void showToastSafe(final Context context, final String text){
        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(context,text,Toast.LENGTH_LONG).show();

                Toast mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                LinearLayout layout = (LinearLayout) mToast.getView();
                TextView tv = (TextView) layout.getChildAt(0);
// 设置字体大小
                tv.setTextSize(45);
// 设置显示位置

                mToast.show();

            }
        });
    }

}
