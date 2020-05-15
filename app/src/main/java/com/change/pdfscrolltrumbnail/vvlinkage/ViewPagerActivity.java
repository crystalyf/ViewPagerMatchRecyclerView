package com.change.pdfscrolltrumbnail.vvlinkage;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.change.pdfscrolltrumbnail.R;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager mViewPager;
    public static  final String TAG="xujun";

    private final String[] mTitles=new String[]{
            "首页","微博","相册","我的"
    };
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        initdata();
    }

    private void initdata() {
        mFragments = new ArrayList<>();
        for(int i=0;i<mTitles.length;i++){
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);

        }
        BaseViewPagerAdapter baseViewPagerAdapter = new BaseViewPagerAdapter
                (getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(baseViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                ((ListFragment)mFragments.get(position)).onSelected();
                Log.i(TAG, "onPageSelected: position=" +position);
            }
        });
    }

    private void initView() {
        mViewPager=(ViewPager)findViewById(R.id.viewPager);
    }
}
