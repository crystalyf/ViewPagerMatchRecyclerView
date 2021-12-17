package com.change.pdfscrolltrumbnail.vvlinkage.adapter;


import android.content.res.Configuration;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.change.pdfscrolltrumbnail.vvlinkage.ImageFragment;

import java.util.List;

/**
 * 2级的 viewPager Adapter，控制ListFragment画面内的横向列表滑动（滑动ImageFragment）
 */
public class BaseViewPagerAdapterChild extends FragmentPagerAdapter {

    List<String> mDatas;
    FragmentManager fm;
    int orientation;


    public BaseViewPagerAdapterChild(FragmentManager fm, List<String> datas) {
        super(fm);
        this.fm = fm;
        this.mDatas = datas;
    }


    @Override
    public Fragment getItem(int position) {
        //待优化
        //优化fragment效率， 添加tag
        Fragment fragment = null;
        for (int i = 0; i < fm.getFragments().size(); i++) {
            if (fm.getFragments().get(i) instanceof ImageFragment && TextUtils.equals(((ImageFragment) fm.getFragments().get(i)).getFragmentTag(), position + "" + orientation)) {
                fragment = fm.getFragments().get(i);
            }
        }
        Fragment oldFragment = null;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            for (int i = 0; i < fm.getFragments().size(); i++) {
                if (fm.getFragments().get(i) instanceof ImageFragment && TextUtils.equals(((ImageFragment) fm.getFragments().get(i)).getFragmentTag(), position + "" + Configuration.ORIENTATION_LANDSCAPE)) {
                    oldFragment = fm.getFragments().get(i);
                }
            }
        } else {
            for (int i = 0; i < fm.getFragments().size(); i++) {
                if (fm.getFragments().get(i) instanceof ImageFragment && TextUtils.equals(((ImageFragment) fm.getFragments().get(i)).getFragmentTag(), position + "" + Configuration.ORIENTATION_PORTRAIT)) {
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
            return ImageFragment.newInstance(mDatas.get(position), position + "" + orientation);
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
