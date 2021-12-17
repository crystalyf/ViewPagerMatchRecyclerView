package com.change.pdfscrolltrumbnail.vvlinkage;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.change.pdfscrolltrumbnail.R;
import com.change.pdfscrolltrumbnail.vrlinkage.HorizontalAdapter;
import com.change.pdfscrolltrumbnail.vvlinkage.adapter.BaseViewPagerAdapter;
import com.change.pdfscrolltrumbnail.vvlinkage.adapter.BaseViewPagerAdapterChild;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fenrir-xjc on 2020/05/15.
 */
public class ListFragment extends BaseFragment {

    static final String key = "dataKey";
    public static final String TAG = "xujun";
    ViewPager viewPagerChild;
    //当前Fragment渲染的所有数据源
    ArrayList<String> imgList;
    private List<Fragment> mFragments;
    private BaseViewPagerAdapterChild mBaseAdapter;
    ScrollView mNoHorizontalScrollView;
    FrameLayout fl_child;

    private RecyclerView recyclerview;
    private HorizontalAdapter horizontalAdapter;

    static final String fragmentKey = "fragmentKey";
    String fragmentTag = "";

    public String getFragmentTag(){
        return fragmentTag;
    }

    public static ListFragment newInstance(ArrayList<String> imgList, String fragmentTag) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        //Fragment附属参数
        bundle.putString(fragmentKey, fragmentTag);
        bundle.putStringArrayList(key, imgList);
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    protected void initView(View view) {
        viewPagerChild = (ViewPager) view.findViewById(R.id.viewPagerChild);
        mNoHorizontalScrollView = (ScrollView) view.findViewById(R.id.NoHorizontalScrollView);
        recyclerview = view.findViewById(R.id.recyclerview);
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
        viewPagerChild.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
              //  tv_page.setText(String.format("%d/" + imgList.size(), position + 1));
            }
        });
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            imgList = arguments.getStringArrayList(key);
          //  tv_page.setText(String.format("%d/" + imgList.size(), 1));
        }
        mBaseAdapter = new BaseViewPagerAdapterChild(getChildFragmentManager()
                , imgList);
        viewPagerChild.setAdapter(mBaseAdapter);
        //     是为了确保mNoHorizontalScrollView他的子孙不能获得焦点
        mNoHorizontalScrollView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        //创建底部横向滚动的adapter
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        horizontalAdapter = new HorizontalAdapter(getActivity(), imgList);
        recyclerview.setAdapter(horizontalAdapter);
        viewPagerChild.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                recyclerview.smoothScrollToPosition(position);
                horizontalAdapter.setBg(position);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        horizontalAdapter.setOnItemClickListener(new HorizontalAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                viewPagerChild.setCurrentItem(position);
            }
        });
    }

}
