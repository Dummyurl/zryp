package com.shunmai.zryp.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.shunmai.zryp.base.BaseActivity;
import com.shunmai.zryp.ui.home.child.UserInfoFragment;
import com.shunmai.zryp.ui.home.child.HomePageFragment;
import com.shunmai.zryp.ui.home.child.SearchFragment;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding databinding;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initWindow(this);
        initContentFrament();
        initTabView();

    }

    private void initTabView() {
        BottomNavigationView navigationView = databinding.navigationHome;
        navigationView.setItemHorizontalTranslationEnabled(false);
        navigationView.setItemIconTintList(null);
//        databinding.flHome;
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1: {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(0));
                        transaction.show(fragments.get(0));
                        transaction.hide(fragments.get(1));
                        transaction.hide(fragments.get(2));
                        transaction.commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.tab2: {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(1));
                        transaction.hide(fragments.get(0));
                        transaction.show(fragments.get(1));
                        transaction.hide(fragments.get(2));
                        transaction.commitAllowingStateLoss();
                        return true;
                    }
//                    case R.id.tab3: {
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(2));
//                        transaction.commitAllowingStateLoss();
//                        return true;
//                    }
                    case R.id.tab4: {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(2));
                        transaction.hide(fragments.get(0));
                        transaction.hide(fragments.get(1));
                        transaction.show(fragments.get(2));
                        transaction.commitAllowingStateLoss();
                        return true;
                    }
                }
                return false;
            }
        });

    }

    private void initContentFrament() {
        fragments = new ArrayList<>();
        fragments.add(new HomePageFragment());
        fragments.add(new SearchFragment());
        fragments.add(new UserInfoFragment());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_home, fragments.get(0));
        transaction.add(R.id.fl_home, fragments.get(1));
        transaction.add(R.id.fl_home, fragments.get(2));
        transaction.hide(fragments.get(1));
        transaction.hide(fragments.get(2));
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
