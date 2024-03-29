package com.change.pdfscrolltrumbnail.vvlinkage.adapter;


import android.content.res.Configuration;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.change.pdfscrolltrumbnail.vvlinkage.ListFragment;
import com.change.pdfscrolltrumbnail.vvlinkage.bean.LinkageImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 1级的 viewPager Adapter，控制Activity画面内的滑动（滑动ListFragment）
 */
public class BaseViewPagerAdapter extends FragmentPagerAdapter {

    List<LinkageImageBean> mDatas;
    FragmentManager fm;
    int orientation;


    public BaseViewPagerAdapter(FragmentManager fm, List<LinkageImageBean> datas, String[] mtitles, int orientation) {
        super(fm);
        this.fm = fm;
        this.mDatas = datas;
        this.orientation = orientation;
    }


    @Override
    public Fragment getItem(int position) {
        //优化fragment效率， 添加tag
        Fragment fragment = null;
        for (int i = 0; i < fm.getFragments().size(); i++) {
            if (fm.getFragments().get(i) instanceof ListFragment && TextUtils.equals(((ListFragment) fm.getFragments().get(i)).getFragmentTag(), position + "" + orientation)) {
                fragment = fm.getFragments().get(i);
            }
        }
        Fragment oldFragment = null;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            for (int i = 0; i < fm.getFragments().size(); i++) {
                if (fm.getFragments().get(i) instanceof ListFragment && TextUtils.equals(((ListFragment) fm.getFragments().get(i)).getFragmentTag(), position + "" + Configuration.ORIENTATION_LANDSCAPE)) {
                    oldFragment = fm.getFragments().get(i);
                }
            }
        } else {
            for (int i = 0; i < fm.getFragments().size(); i++) {
                if (fm.getFragments().get(i) instanceof ListFragment && TextUtils.equals(((ListFragment) fm.getFragments().get(i)).getFragmentTag(), position + "" + Configuration.ORIENTATION_PORTRAIT)) {
                    oldFragment = fm.getFragments().get(i);
                }
            }
        }
        //如果栈内找到旧的Fragment，出栈
        if (oldFragment != null) {
            fm.beginTransaction().remove(oldFragment).commit();
        }
        //构造Fragment的时候，加标签tag构造，为了优化效率做准备，规避数据源太多的时候切换卡顿的问题
        if (fragment != null) {
            return fragment;
        } else {
            return ListFragment.newInstance((ArrayList<String>) mDatas.get(position).getImgList(), position + "" + orientation);
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
