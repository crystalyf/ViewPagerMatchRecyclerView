package com.change.pdfscrolltrumbnail.vvlinkage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.change.pdfscrolltrumbnail.R;
import com.change.pdfscrolltrumbnail.vvlinkage.bean.LinkageImageBean;
import com.change.pdfscrolltrumbnail.vvlinkage.adapter.VvListAdapter;

import java.util.ArrayList;

/**
 * Main button 点击之后进入此画面
 *
 * viewpager嵌套+Recyclerview联动之前的画面
 */
public class VvLinkageActivity extends AppCompatActivity {

    private RecyclerView rv_list;
    //图片所用资源list
    ArrayList<LinkageImageBean> imgBeanList = new ArrayList<>();
    VvListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //共用viewPager+Recycler封装的布局
        setContentView(R.layout.activity_vv_linkage);
        initView();
    }

    private void initView() {
        rv_list = findViewById(R.id.rv_list);
        ArrayList<String> imgList1 = new ArrayList<>();
        LinkageImageBean lbean1 = new LinkageImageBean();
        imgList1.add("http://img1.imgtn.bdimg.com/it/u=1760283799,1689150510&fm=26&gp=0.jpg");
        lbean1.setImgList(imgList1);
        imgBeanList.add(lbean1);

        LinkageImageBean lbean2 = new LinkageImageBean();
        ArrayList<String> imgList2 = new ArrayList<>();
        imgList2.add("http://img5.imgtn.bdimg.com/it/u=1089874897,1268118658&fm=26&gp=0.jpg");
        imgList2.add("http://img1.imgtn.bdimg.com/it/u=1760283799,1689150510&fm=26&gp=0.jpg");
        imgList2.add("http://img1.imgtn.bdimg.com/it/u=1806680339,1258257998&fm=26&gp=0.jpg");
        imgList2.add("http://img5.imgtn.bdimg.com/it/u=3238317745,514710292&fm=26&gp=0.jpg");
        imgList2.add("http://img1.imgtn.bdimg.com/it/u=2658872482,510079581&fm=26&gp=0.jpg");

        imgList2.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=188173295,510375359&fm=26&gp=0.jpg");
        imgList2.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3225163326,3627210682&fm=26&gp=0.jpg");
        imgList2.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3104686528,572431609&fm=26&gp=0.jpg");
        lbean2.setImgList(imgList2);
        imgBeanList.add(lbean2);

        ArrayList<String> imgList3 = new ArrayList<>();
        LinkageImageBean lbean3 = new LinkageImageBean();
        imgList3.add("http://img1.imgtn.bdimg.com/it/u=1760283799,1689150510&fm=26&gp=0.jpg");
        lbean3.setImgList(imgList3);
        imgBeanList.add(lbean3);

        ArrayList<String> imgList4 = new ArrayList<>();
        LinkageImageBean lbean4 = new LinkageImageBean();
        imgList4.add("http://img1.imgtn.bdimg.com/it/u=1760283799,1689150510&fm=26&gp=0.jpg");
        imgList4.add("http://img5.imgtn.bdimg.com/it/u=3238317745,514710292&fm=26&gp=0.jpg");
        lbean4.setImgList(imgList4);
        imgBeanList.add(lbean4);

        ArrayList<String> imgList5 = new ArrayList<>();
        LinkageImageBean lbean5 = new LinkageImageBean();
        imgList5.add("http://img5.imgtn.bdimg.com/it/u=3238317745,514710292&fm=26&gp=0.jpg");
        lbean5.setImgList(imgList5);
        imgBeanList.add(lbean5);

        //每个插图集的集合(ListAdapter负责渲染每个bean的第一张图显示在此画面)
        adapter = new VvListAdapter( VvLinkageActivity.this, imgBeanList);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(adapter);
        adapter.setOnItemClickListener(new VvListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(VvLinkageActivity.this, ViewPagerActivity.class);
                intent.putExtra("imgList", imgBeanList);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

}
