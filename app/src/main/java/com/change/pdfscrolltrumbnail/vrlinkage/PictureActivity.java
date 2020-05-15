package com.change.pdfscrolltrumbnail.vrlinkage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.change.pdfscrolltrumbnail.R;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by louliang on 2018/05/31.
 * 附件缩略图转换为大图
 */

public class PictureActivity extends AppCompatActivity {
    private int position;
    private ViewPager mViewPager;
    private RecyclerView recyclerview;
    private HorizontalAdapter horizontalAdapter;
    private PhotoView photoview;
    private ViewPagerAdapter myImagPagerAdapter;
    private ArrayList<ImageView> imageViews;
    private ArrayList<String> smallList;
    private ArrayList<String> bigList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        initData();
    }

    protected void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        position = extras.getInt("position");
        smallList = extras.getStringArrayList("smallList");
        bigList = extras.getStringArrayList("bigList");
        mViewPager = findViewById(R.id.view_pager);
        recyclerview = findViewById(R.id.recyclerview);
        int size = smallList.size();
        imageViews = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            photoview = new PhotoView(this);
            imageViews.add(photoview);
            photoview.isEnabled();
            photoViewDismiss();
        }
    }

    protected void initData() {
        //创建viewpager的adapter
        myImagPagerAdapter = new ViewPagerAdapter(PictureActivity.this, imageViews, bigList);
        mViewPager.setAdapter(myImagPagerAdapter);
        mViewPager.setCurrentItem(position);
        //创建底部横向滚动的adapter
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PictureActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        horizontalAdapter = new HorizontalAdapter(PictureActivity.this, smallList);
        recyclerview.setAdapter(horizontalAdapter);
        initViewPager();
        //初始化设置数据，联动作用
        recyclerview.scrollToPosition(this.position);
        horizontalAdapter.setBg(this.position);
    }


    private void initViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                recyclerview.smoothScrollToPosition(position);
                photoViewDismiss();
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
                mViewPager.setCurrentItem(position);
            }
        });
    }


    private void photoViewDismiss() {
        photoview.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                finish();
            }

            public void onOutsidePhotoTap() {
            }
        });
    }

}
