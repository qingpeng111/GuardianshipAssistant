package com.pandas.guardianshipassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.android.material.navigation.NavigationView;
import com.pandas.guardianshipassistant.R;
import com.pandas.guardianshipassistant.config.ConstantConfig;
import com.pandas.guardianshipassistant.ui.activity.left.LeftMenuActivity;
import com.pandas.guardianshipassistant.ui.activity.rigthtop.RightTopMenuActivity;
import com.pandas.guardianshipassistant.ui.adapter.ViewPagerAdapter;
import com.pandas.guardianshipassistant.ui.fragment.FragmentBottomChat;
import com.pandas.guardianshipassistant.ui.fragment.FragmentBottomContacts;
import com.pandas.guardianshipassistant.ui.fragment.FragmentBottomFind;
import com.pandas.guardianshipassistant.ui.fragment.FragmentBottomGuard;
import com.pandas.guardianshipassistant.ui.fragment.FragmentBottomMy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.left_navigation_view)
    NavigationView leftNavigationView;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.tool_bar_name)
    TextView toolBarName;


    private View headerView;
    private List<Fragment> fragments;
    private String[] mBottomTitle = new String[]{"聊天","联系人","监护","发现","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //toolbar
        initDrawerLayout();
        initToobarMenu();

        // bottom
        initViewpager();
        initBottomNavigationBar();


    }

    //用于监听有toolbar menu
    private void initToobarMenu() {
        // 监听右上角toolbar的menu,然后跳转到不同的fragment
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {

                    case R.id.toolbar_menu_search:
                        intent = new Intent(MainActivity.this, RightTopMenuActivity.class);
                        intent.putExtra("right_menu_item", "toolbar_menu_search");
                        startActivityForResult(intent, 3);
                        Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.toolbar_menu_add_friends:
                        intent = new Intent(MainActivity.this, RightTopMenuActivity.class);
                        intent.putExtra("right_menu_item", "toolbar_menu_add_friends");
                        startActivityForResult(intent, 3);
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.toolbar_menu_group_chat:
                        intent = new Intent(MainActivity.this, RightTopMenuActivity.class);
                        intent.putExtra("right_menu_item", "toolbar_menu_group_chat");
                        startActivityForResult(intent, 3);
                        Toast.makeText(MainActivity.this, "群聊", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.toolbar_menu_scan:
                        intent = new Intent(MainActivity.this, RightTopMenuActivity.class);
                        intent.putExtra("right_menu_item", "toolbar_menu_scan");
                        startActivityForResult(intent, 3);
                        Toast.makeText(MainActivity.this, "扫一扫", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.toolbar_menu_settings:
                        intent = new Intent(MainActivity.this, RightTopMenuActivity.class);
                        intent.putExtra("right_menu_item", "toolbar_menu_settings");
                        startActivityForResult(intent, 3);
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.toolbar_menu_help_feedback:
                        intent = new Intent(MainActivity.this, RightTopMenuActivity.class);
                        intent.putExtra("right_menu_item", "toolbar_menu_help_feedback");
                        startActivityForResult(intent, 3);
                        Toast.makeText(MainActivity.this, "帮助与反馈", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    //初始化BottomNavigationBar需要配套用的viewpager
    private void initViewpager() {
        viewPager.setOffscreenPageLimit(5);

        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentBottomChat());
        fragments.add(new FragmentBottomContacts());
        fragments.add(new FragmentBottomGuard());
        fragments.add(new FragmentBottomFind());
        fragments.add(new FragmentBottomMy());
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);

        int curFragment=ConstantConfig.SELECTED;
//        //         跳转过来之后重新设置
//        String returnFragmentId = getIntent().getStringExtra("returnFragmentId");
//        if(returnFragmentId=="0"){
//            curFragment=0;
//        }else{
//            curFragment=1;
//        }

        viewPager.setCurrentItem(curFragment);//设置登陆之后选中的界面

    }

    //初始化BottomNavigationBar
    private void initBottomNavigationBar() {
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.setBarBackgroundColor(R.color.white)
                           .setActiveColor(R.color.blue)
                           .setInActiveColor(R.color.gray);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_bottom_chat, mBottomTitle[0])).setInActiveColor(R.color.gray);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_bottom_contact, mBottomTitle[1])).setInActiveColor(R.color.gray);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_bottom_guard, mBottomTitle[2])).setInActiveColor(R.color.gray);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_bottom_find, mBottomTitle[3])).setInActiveColor(R.color.gray);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_bottom_my, mBottomTitle[4])).setInActiveColor(R.color.gray);
        bottomNavigationBar.setFirstSelectedPosition(ConstantConfig.SELECTED);
        bottomNavigationBar.initialise();

    }


    //抽屉界面
    private void initDrawerLayout() {
        //点击头像
        headerView = leftNavigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "头像点击了", Toast.LENGTH_LONG).show();
            }
        });
        //点击左侧menu的item跳转到不同的fragment,
        leftNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {

                    case R.id.left_menu_home:
                        intent = new Intent(MainActivity.this, LeftMenuActivity.class);
                        intent.putExtra("left_menu_item", "left_menu_home");
                        startActivityForResult(intent, 3);
                        break;
                    case R.id.left_menu_update:
                        intent = new Intent(MainActivity.this, LeftMenuActivity.class);
                        intent.putExtra("left_menu_item", "left_menu_update");
                        startActivityForResult(intent, 3);
                        break;
                    case R.id.left_menu_download:
                        intent = new Intent(MainActivity.this, LeftMenuActivity.class);
                        intent.putExtra("left_menu_item", "left_menu_download");
                        startActivityForResult(intent, 3);
                        break;
                    case R.id.left_menu_tools:
                        intent = new Intent(MainActivity.this, LeftMenuActivity.class);
                        intent.putExtra("left_menu_item", "left_menu_tools");
                        startActivityForResult(intent, 3);
                        break;
                    case R.id.left_menu_share:
                        intent = new Intent(MainActivity.this, LeftMenuActivity.class);
                        intent.putExtra("left_menu_item", "left_menu_share");
                        startActivityForResult(intent, 3);
                        break;
                    case R.id.left_menu_send:
                        intent = new Intent(MainActivity.this, LeftMenuActivity.class);
                        intent.putExtra("left_menu_item", "left_menu_send");
                        startActivityForResult(intent, 3);
                        break;
                }
                return false;
            }
        });

        //整合tooBar和drawerLayout
        toolBar.inflateMenu(R.menu.right_toolbar_menu);//右边的三个点

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.open, R.string.closed);
        drawerToggle.syncState();
//        System.out.println("开始-----------------------------");
        drawerLayout.addDrawerListener(drawerToggle);
//        System.out.println("结束-------------------");


    }

    //重写BottomNavigationBar三个方法
    @Override
    public void onTabSelected(int position) {
       // 设置当前frgmengt
        viewPager.setCurrentItem(position);
        //更改toolbar标题
        toolBarName.setText(mBottomTitle[position]);

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    //重写Viewpager的 addOnPageChangeListener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
