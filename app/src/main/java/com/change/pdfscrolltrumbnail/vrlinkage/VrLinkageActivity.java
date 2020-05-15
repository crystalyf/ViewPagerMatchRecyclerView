package com.change.pdfscrolltrumbnail.vrlinkage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.change.pdfscrolltrumbnail.R;

import java.util.ArrayList;

public class VrLinkageActivity extends AppCompatActivity {

    private ArrayList<String> smallList;
    private ListView listview;
    private ArrayList<String> bigList;
    //图片所用资源list
    private ArrayList<String> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_linkage);

        imgList.add("http://img1.imgtn.bdimg.com/it/u=1760283799,1689150510&fm=26&gp=0.jpg");
        imgList.add("http://img1.imgtn.bdimg.com/it/u=1806680339,1258257998&fm=26&gp=0.jpg");
        imgList.add("http://img5.imgtn.bdimg.com/it/u=1089874897,1268118658&fm=26&gp=0.jpg");
        imgList.add("http://img5.imgtn.bdimg.com/it/u=3238317745,514710292&fm=26&gp=0.jpg");
        imgList.add("http://img1.imgtn.bdimg.com/it/u=2658872482,510079581&fm=26&gp=0.jpg");

        //缩略图小图
        smallList = new ArrayList<>();
        smallList.add(imgList.get(0));
        smallList.add(imgList.get(1));
        smallList.add(imgList.get(2));
        smallList.add(imgList.get(3));
        smallList.add(imgList.get(4));
        //原图
        bigList = new ArrayList<>();
        bigList.add(imgList.get(0));
        bigList.add(imgList.get(1));
        bigList.add(imgList.get(2));
        bigList.add(imgList.get(3));
        bigList.add(imgList.get(4));

        listview = (ListView) findViewById(R.id.listview);
        ListAdapter adapter = new ListAdapter(VrLinkageActivity.this,smallList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VrLinkageActivity.this, PictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                bundle.putStringArrayList("smallList",smallList);
                bundle.putStringArrayList("bigList",bigList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
