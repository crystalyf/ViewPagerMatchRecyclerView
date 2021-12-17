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

/**
 * 具体的viewpager嵌套+Recyclerview联动画面
 */
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
        BaseViewPagerAdapter baseViewPagerAdapter = new BaseViewPagerAdapter
                (getSupportFragmentManager(), linkageBeanList, new String[]{},getResources().getConfiguration().orientation);
        mViewPager.setAdapter(baseViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.i(TAG, "onPageSelected: position=" + position);
            }
        });
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}
