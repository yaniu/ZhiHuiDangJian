package com.lfc.zhihuidangjianapp.ui.activity.fgt.dept.act;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lfc.zhihuidangjianapp.R;
import com.lfc.zhihuidangjianapp.app.MyApplication;
import com.lfc.zhihuidangjianapp.base.BaseActivity;
import com.lfc.zhihuidangjianapp.bean.TabEntity;
import com.lfc.zhihuidangjianapp.net.http.HttpService;
import com.lfc.zhihuidangjianapp.net.http.ResponseObserver;
import com.lfc.zhihuidangjianapp.net.http.RetrofitFactory;
import com.lfc.zhihuidangjianapp.ui.activity.adapter.FragPagerAdapter;
import com.lfc.zhihuidangjianapp.ui.activity.fgt.dept.fragment.Fgt_Weekend_Report;
import com.lfc.zhihuidangjianapp.ui.activity.model.OrganizationalLife;
import com.lfc.zhihuidangjianapp.ui.activity.model.OrganizationalLifeDetail;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @date: 2019-08-06
 * @autror: guojian
 * @description:
 */
public class Act_Weekend_Report extends BaseActivity {

    private ImageView create;

    private CommonTabLayout tab;

    private ViewPager viewPager;

    private String[] mTitles = {"党委","党总支", "党支部", "党员"};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private List<Fragment> fragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weekend_report;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        findViewById(R.id.imgBack).setOnClickListener(back->finish());
        initImmersionBar(0);
        create = findViewById(R.id.create);
        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
            fragments.add(new Fgt_Weekend_Report());
        }
        tab.setTabData(mTabEntities);
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        FragPagerAdapter fpa = new FragPagerAdapter(getSupportFragmentManager());
        fpa.setFragmentList(fragments);
        viewPager.setAdapter(fpa);

        setEvent();
    }

    private void setEvent() {
        create.setOnClickListener(ceate->{
            startActivity(new Intent(this, Act_Write_Weekend_Log.class));
//            RetrofitFactory.getDefaultRetrofit().create(HttpService.class)
//                    .queryOrganizationalLifeDetail(map, MyApplication.getLoginBean().getToken())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new ResponseObserver<OrganizationalLifeDetail>(getActivity()) {
//
//                        @Override
//                        protected void onNext(OrganizationalLifeDetail response) {
//                            Log.e("onNext= ", response.toString());
//                            if(response==null)return;
//                            OrganizationalLife dynamic = response.getOrganizationalLife();
//                            tvTitle.setText(dynamic.getTitle());
//                            tvAuthor.setText(dynamic.getAuthor());
//                            tvContent.setText(Html.fromHtml(dynamic.getComment()));
//                        }
//
//                        @Override
//                        protected void onError(Throwable e) {
//                            super.onError(e);
//                            Log.e("Throwable= ", e.getMessage());
//                        }
//                    }.actual());
        });
    }

    @Override
    protected void initData() {

    }
}
