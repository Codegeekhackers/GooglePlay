package com.lan.googleplay.ui.activity;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lan.googleplay.R;
import com.lan.googleplay.lib.PagerSlidingTab;
import com.lan.googleplay.ui.Apdater.MainAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawlayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar tb_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setActionBar();
    }

    private void setActionBar() {
        tb_main.setLogo(R.mipmap.ic_launcher);
        tb_main.setTitle(getString(R.string.app_name));

        setSupportActionBar(tb_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);//设置Icon可见按钮可以被点击

        drawerToggle = new ActionBarDrawerToggle(this, drawlayout, tb_main, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.syncState();
        drawlayout.setDrawerListener(drawerToggle);
    }

    private void initViews() {
        drawlayout = (DrawerLayout) findViewById(R.id.drawlayout);
        PagerSlidingTab pagerSlidingTab = (PagerSlidingTab) findViewById(R.id.pagerSlidingTab);
        tb_main = (Toolbar) findViewById(R.id.tb_main);

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        pagerSlidingTab.setViewPager(viewpager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
