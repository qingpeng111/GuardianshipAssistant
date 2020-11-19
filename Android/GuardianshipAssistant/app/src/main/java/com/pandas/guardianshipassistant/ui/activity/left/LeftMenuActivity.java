package com.pandas.guardianshipassistant.ui.activity.left;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.ui.fragment.FragmentLeftDownload;
import com.pandas.guardianshipassistant.ui.fragment.FragmentLeftHome;
import com.pandas.guardianshipassistant.ui.fragment.FragmentLeftSend;
import com.pandas.guardianshipassistant.ui.fragment.FragmentLeftShare;
import com.pandas.guardianshipassistant.ui.fragment.FragmentLeftTools;
import com.pandas.guardianshipassistant.ui.fragment.FragmentLeftUpdate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftMenuActivity extends AppCompatActivity {

    @BindView(R.id.left_menu_activity_button)
    Button leftMenuActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_menu);
        ButterKnife.bind(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

//        transaction.add(R.id.fragment_left,new FragmentLeftUpdate());


        Intent intent = getIntent();
        String left_menu_item=intent.getStringExtra("left_menu_item");
        setResult(RESULT_CANCELED,intent);
        System.out.println("left_menu_item："+left_menu_item);
        switch (left_menu_item){
            case "left_menu_home":
                transaction.add(R.id.fragment_left_menu,new FragmentLeftHome());
                transaction.commit();
                break;
            case "left_menu_update":
                transaction.add(R.id.fragment_left_menu,new FragmentLeftUpdate());
                transaction.commit();
                break;
            case "left_menu_download":
                transaction.add(R.id.fragment_left_menu,new FragmentLeftDownload());
                transaction.commit();
                break;
            case "left_menu_tools":
                transaction.add(R.id.fragment_left_menu,new FragmentLeftTools());
                transaction.commit();
                break;
            case "left_menu_share":
                transaction.add(R.id.fragment_left_menu,new FragmentLeftShare());
                transaction.commit();
                break;
            case "left_menu_send":
                transaction.add(R.id.fragment_left_menu,new FragmentLeftSend());
                transaction.commit();
                break;
        }


        //点击按钮，返回上一次的界面
        leftMenuActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String left_menu_item=intent.getStringExtra("left_menu_item");
                setResult(RESULT_CANCELED,intent);
                System.out.println("left_menu_item："+left_menu_item);
                finish();
            }

        });


    }
}
