package com.change.pdfscrolltrumbnail.vvlinkage;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.change.pdfscrolltrumbnail.R;
import com.change.pdfscrolltrumbnail.vvlinkage.adapter.BaseViewPagerAdapter;
import com.change.pdfscrolltrumbnail.vvlinkage.bean.LinkageImageBean;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager mViewPager;
    private List<LinkageImageBean> linkageBeanList;
    public static final String TAG = "xujun";

    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        initdata();
    }

    private void initdata() {
        linkageBeanList = (List<LinkageImageBean>) getIntent().getSerializableExtra("imgList");
        mFragments = new ArrayList<>();
        for (int i = 0; i < linkageBeanList.size(); i++) {
            ListFragment listFragment = ListFragment.newInstance((ArrayList<String>) linkageBeanList.get(i).getImgList());
            mFragments.add(listFragment);

        }
        BaseViewPagerAdapter baseViewPagerAdapter = new BaseViewPagerAdapter
                (getSupportFragmentManager(), mFragments, new String[]{});
        mViewPager.setAdapter(baseViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                ((ListFragment) mFragments.get(position)).onSelected();
                Log.i(TAG, "onPageSelected: position=" + position);
            }
        });
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}
