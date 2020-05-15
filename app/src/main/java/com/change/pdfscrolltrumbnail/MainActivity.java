package com.change.pdfscrolltrumbnail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.change.pdfscrolltrumbnail.vrlinkage.VrLinkageActivity;
import com.change.pdfscrolltrumbnail.vvlinkage.ViewPagerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_vrlinkage, btn_vvlinkage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_vrlinkage = findViewById(R.id.btn_vrlinkage);
        btn_vvlinkage = findViewById(R.id.btn_vvlinkage);
        btn_vrlinkage.setOnClickListener(this);
        btn_vvlinkage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_vrlinkage:
                Intent intent1 = new Intent(this, VrLinkageActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_vvlinkage:
                Intent intent2 = new Intent(this, ViewPagerActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
