package com.maixianda.copy.study.support_library_demo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件,对应的是左上角的点击滑动出抽屉菜单的按钮
                mDrawerLayout.openDrawer(mNavigationView);//显示抽屉菜单
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {
        //region 绑定控件
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        //endregion 绑定控件

        //region 初始化ToolBar
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_dialog_alert);//设置左上角的按钮图片
        actionBar.setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头, true有小箭头，并且图标可以点击
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(false);
        // 使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，
        // 否则，显示应用程序图标，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME

        //endregion 初始化ToolBar

        //region 设置左侧菜单的菜单点击响应(也就是对navigationView添加item的监听事件)
        mNavigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        //endregion 设置左侧菜单的菜单点击响应(也就是对navigationView添加item的监听事件)

        //region 设置默认打开左侧菜单栏(对NavigationView添加item的监听事件)
        //mDrawerLayout.openDrawer(mNavigationView);
        //endregion 设置默认打开左侧菜单栏(对NavigationView添加item的监听事件)
        List<String> titles = new ArrayList<>();
        titles.add("details");
        titles.add("share");
        titles.add("agenda");
        //region 初始化TabLaout(添加选项卡)
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        //endregion 初始化TabLaout(添加选项卡)

        //region 初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new InfoDetailsFragment());
        fragments.add(new ShareFragment());
        fragments.add(new AgendaFragment());
        // TODO: 2016/6/14 15:28 编写各种fragment
        //endregion 初始化ViewPager的数据集
        //region 创建ViewPager的adapter
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        mViewPager.setAdapter(adapter);
        //endregion 创建ViewPager的adapter
        mTabLayout.setupWithViewPager(mViewPager);
        //mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.menu_agenda:
                            mViewPager.setCurrentItem(2);
                            break;
                        case R.id.menu_info_details:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.menu_share:
                            mViewPager.setCurrentItem(1);
                            break;
                    }
                    //关闭DrawerLayout回到主界面选中的tab的fragment页
                    //当点击了菜单中的项后把侧边栏动画关闭成为点开菜单的样子
                    mDrawerLayout.closeDrawer(mNavigationView);
                    return false;//返回true或者false不知道区别
                }
            };
}
