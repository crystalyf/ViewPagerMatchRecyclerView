package com.change.pdfscrolltrumbnail.vvlinkage.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * @ explain:
 * @ author：xujun on 2016/10/26 11:10
 * @ email：gdutxiaoxu@163.com
 */
public class ChildViewPager extends ViewPager {

    private static final String TAG = "xujun";
    public ChildViewPager(Context context) {
        super(context);
    }

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int curPosition;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                curPosition = this.getCurrentItem();
                int count = this.getAdapter().getCount();
                Log.i(TAG, "curPosition:=" +curPosition);
//                全部由孩子拦截触摸事件
/*                getParent().requestDisallowInterceptTouchEvent(true);*/
                // 当当前页面在最后一页和第0页的时候，由父亲拦截触摸事件
//                if (curPosition == count - 1 || curPosition == 0) {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                } else {//其他情况，由孩子拦截触摸事件(true的话，在孩子控件上下滑动，孩子就拦截了，纵向scrollview就不会响应上下滑动)
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
                getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(ev);
    }
}
