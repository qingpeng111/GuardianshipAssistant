package com.pandas.securityassistant;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.pandas.securityassistant.ui.bottom.baohu.BaohuFragment;
import com.pandas.securityassistant.ui.bottom.faxian.FaxianFragment;
import com.pandas.securityassistant.ui.bottom.liaotian.LiaotianFragment;
import com.pandas.securityassistant.ui.bottom.tongxunlu.TongxunluFragment;
import com.pandas.securityassistant.ui.bottom.wode.WodeFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.bottomNavigationView)
//    BottomNavigationView navBottom;

    private AppBarConfiguration mAppBarConfiguration,mAppBarConfiguration1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_update, R.id.nav_download,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        //底部
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
//        mAppBarConfiguration1 = new AppBarConfiguration.Builder(
//                R.id.menu_bottom_liaotian, R.id.menu_bottom_tongxunlu, R.id.menu_bottom_baohu,
//                R.id.menuE_bottom_faxian, R.id.menu_bottom_wode)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController1 = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController1, mAppBarConfiguration1);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController1);

        initBottomNavigation();
        initData();
    }

    private int lastIndex;
    List<Fragment> mFragments;
    private BottomNavigationView mBottomNavigationView;

    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new BaohuFragment());
        mFragments.add(new FaxianFragment());
        mFragments.add(new LiaotianFragment());
        mFragments.add(new TongxunluFragment());
        mFragments.add(new WodeFragment());
        // 初始化展示MessageFragment
        setFragmentPosition(0);
        System.out.println("------------------initData");
    }

    public void initBottomNavigation() {
        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
//           解决当item大于三个时，非平均布局问题

//       BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_bottom_liaotian:
                        setFragmentPosition(0);
                        break;
                    case R.id.nav_bottom_tongxunlu:
                        setFragmentPosition(1);
                        break;
                    case R.id.nav__bottom_baohu:
                        setFragmentPosition(2);
                        break;
                    case R.id.nav_bottom_faxian:
                        setFragmentPosition(3);
                        break;
                    case R.id.nav_bottom_wode:
                        setFragmentPosition(4);
                        break;
                    default:
                        break;
                }
                // 这里注意返回true,否则点击失效
                return true;
            }
        });
        System.out.println("------------------initBottomNavigation");
    }


    private void setFragmentPosition(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.nav_host_fragment, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
        System.out.println("--------------------setFragmentPosition");
    }























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        System.out.println("调用：onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        System.out.println("调用：onSupportNavigateUp");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
