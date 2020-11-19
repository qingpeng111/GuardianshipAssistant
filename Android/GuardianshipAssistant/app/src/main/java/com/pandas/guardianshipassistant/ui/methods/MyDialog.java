package com.pandas.guardianshipassistant.ui.methods;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;

import com.pandas.guardianshipassistant.R;

/**
 * Created by <lzh> on 2017/8/3.
 */

public class MyDialog {
    private Dialog dialog;
    private Context context;
    private static MyDialog MyDialog = null;
    private View rootView;

    public static MyDialog getInstance() {
        if (MyDialog == null) {
            synchronized (MyDialog.class) {
                if (MyDialog == null) {
                    MyDialog = new MyDialog();
                    System.out.println("-------------------7------------------");
                }
            }
        }

        return MyDialog;
    }

    private MyDialog() {
    }


    public MyDialog init(Context context, @LayoutRes int resId){
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(resId, null);
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.setContentView(rootView);
        System.out.println("-------------------8------------------");
        return this;
    }


    public MyDialog setPositiveButton(String str, final OnClickListener listener) {
        Button button = (Button) rootView.findViewById(R.id.btn1);
        button.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    dialog.dismiss();
                    System.out.println("-------------------9------------------");
                } else {
                    listener.OnClick(v);
                    System.out.println("-------------------10------------------");
//                    dialog.dismiss();
                }
            }
        });
        return this;
    }

    public MyDialog setCancelButton(String str, final OnClickListener listener) {
        Button button = (Button) rootView.findViewById(R.id.btn2);
        button.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    dialog.dismiss();
                    System.out.println("-------------------11------------------");
                } else {
                    listener.OnClick(v);
                    dialog.dismiss();
                }
            }
        });
        return this;
    }



    public View getView(@IdRes int resId){
        System.out.println("-------------------3------------------");
        return rootView.findViewById(resId);
    }

    public interface OnClickListener {
        void OnClick(View view);
    }
}
