package com.pandas.guardianshipassistant.ui.activity.rigthtop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.ui.fragment.FragmentRightAddFriend;
import com.pandas.guardianshipassistant.ui.fragment.FragmentRightGroupChat;
import com.pandas.guardianshipassistant.ui.fragment.FragmentRightHelpAndFeedback;
import com.pandas.guardianshipassistant.ui.fragment.FragmentRightScan;
import com.pandas.guardianshipassistant.ui.fragment.FragmentRightSearch;
import com.pandas.guardianshipassistant.ui.fragment.FragmentRightSettings;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RightTopMenuActivity extends AppCompatActivity {

    @BindView(R.id.right_menu_activity_button)
    Button rightMenuActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_menu);
        ButterKnife.bind(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

//        transaction.add(R.id.fragment_left,new FragmentLeftUpdate());


        Intent intent = getIntent();
        String right_menu_item=intent.getStringExtra("right_menu_item");
        setResult(RESULT_CANCELED,intent);
        System.out.println("right_menu_item："+right_menu_item);
        switch (right_menu_item){
            case "toolbar_menu_search":
                transaction.add(R.id.fragment_right_menu,new FragmentRightSearch());
                transaction.commit();
                break;
            case "toolbar_menu_add_friends":
                transaction.add(R.id.fragment_right_menu,new FragmentRightAddFriend());
                transaction.commit();
                break;
            case "toolbar_menu_group_chat":
                transaction.add(R.id.fragment_right_menu,new FragmentRightGroupChat());
                transaction.commit();
                break;
            case "toolbar_menu_scan":
                transaction.add(R.id.fragment_right_menu,new FragmentRightScan());
                transaction.commit();
                break;
            case "toolbar_menu_settings":
                transaction.add(R.id.fragment_right_menu,new FragmentRightSettings());
                transaction.commit();
                break;
            case "toolbar_menu_help_feedback":
                transaction.add(R.id.fragment_right_menu,new FragmentRightHelpAndFeedback());
                transaction.commit();
                break;
        }


        //点击按钮，返回上一次的界面
        rightMenuActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String right_menu_item=intent.getStringExtra("right_menu_item");
                setResult(RESULT_CANCELED,intent);
                System.out.println("left_menu_item："+right_menu_item);
                finish();
            }

        });


    }
}
