package com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.app.MyApplication;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.net.http.HttpService;
import com.lfc.zhihuidangjianapp.net.http.ResponseObserver;
import com.lfc.zhihuidangjianapp.net.http.RetrofitFactory;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.dept.fragment.Fgt_Dept_detail;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.home.act.fgt.Fgt_PartyBuilDingMatrix;
import com.lfc.zhihuidangjianapp.ui.activity.model.AppConfigLists;
import com.lfc.zhihuidangjianapp.ui.activity.model.Depts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 党建矩阵
 */
public class Act_PartyBuildingMatrix extends BaseActivity implements View.OnClickListener {

    private final String[] mTitles = {"党委", "党总支", "党支部"};

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    SlidingTabLayout tabLayout_4;

    private int deptNumber = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_party_building_matrix;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        findViewById(R.id.imgBack).setOnClickListener(this);
        tabLayout_4 = findViewById(R.id.tl_4);
    }

    MyPagerAdapter mAdapter;

    @Override
    protected void initData() {
        mFragments.clear();
        mFragments.add(getDetailFragment("DT000001"));
        mFragments.add(getDetailFragment("10002"));
        mFragments.add(Fgt_PartyBuilDingMatrix.getInstance("10001"));

        ViewPager vp = findViewById(R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tabLayout_4.setViewPager(vp, mTitles);

    }

    private Fgt_Dept_detail getDetailFragment(String deptNumber){
        Fgt_Dept_detail fgtDeptDetail = new Fgt_Dept_detail();
        Bundle bundle = new Bundle();
        bundle.putString("deptNumber", deptNumber);
        fgtDeptDetail.setArguments(bundle);
        return fgtDeptDetail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
