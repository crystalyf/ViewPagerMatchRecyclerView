package com.change.pdfscrolltrumbnail.vvlinkage;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.change.pdfscrolltrumbnail.R;
import com.change.pdfscrolltrumbnail.vvlinkage.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fenrir-xjc on 2020/05/15.
 */
public class ListFragment extends BaseFragment {

    static final String key = "key";
    public static final String TAG = "xujun";
    ViewPager mViewPager;
    TextView tv_page;

    //当前Fragment渲染的所有数据源
    ArrayList<? extends String> imgList;
    private List<Fragment> mFragments;
    private BaseFragmentAdapter mBaseAdapter;
    ScrollView mNoHorizontalScrollView;
    FrameLayout fl_child;

    public static ListFragment newInstance(ArrayList<String> imgList) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        //Fragment附属参数
        bundle.putStringArrayList(key, imgList);
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    protected void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mNoHorizontalScrollView = (ScrollView) view.findViewById(R.id.NoHorizontalScrollView);
        tv_page = view.findViewById(R.id.tv_page);
        fl_child = view.findViewById(R.id.fl_child);
        //set FrameLayout height
        Resources resources = this.getResources();
        fl_child.getLayoutParams().height = resources.getDisplayMetrics().heightPixels;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void fetchData() {
        int scrollY = mNoHorizontalScrollView.getScrollY();
    }

    /**
     * 在界面切换的时候会调用这个方法
     * true 表示可见
     * FALSE 表示不可见
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {//表示界面可见
            if (mNoHorizontalScrollView != null) {// 之所以判断是否为空，
            }
        }
    }

    @Override
    protected void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tv_page.setText(String.format("%d/" + imgList.size(), position + 1));
            }
        });
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            imgList = arguments.getStringArrayList(key);
            tv_page.setText(String.format("%d/" + imgList.size(), 1));
        }
        mFragments = new ArrayList<>();
        for (int i = 0; i < imgList.size(); i++) {
            ImageFragment imageFragment = ImageFragment.newInstance(imgList.get(i));
            mFragments.add(imageFragment);
        }
        mBaseAdapter = new BaseFragmentAdapter(getChildFragmentManager()
                , mFragments);
        mViewPager.setAdapter(mBaseAdapter);
        //     是为了确保mNoHorizontalScrollView他的子孙不能获得焦点
        mNoHorizontalScrollView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
    }

    public void onSelected() {

    }
}
