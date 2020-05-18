package com.change.pdfscrolltrumbnail.vrlinkage;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.change.pdfscrolltrumbnail.R;

import java.util.ArrayList;

/**
 * Created by louliang on 2018/05/19.
 */

public class HorizontalAdapter extends RecyclerView.Adapter {
    ArrayList<String> list;
    Context context;
    OnItemClickListener onItem;
    private int pos;

    public HorizontalAdapter(Activity activity, ArrayList<String> imageViews) {
        list = imageViews;
        context = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(View.inflate(context, R.layout.horistonal_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            final MyHolder myHolder = (MyHolder) holder;
            if (position == pos) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    myHolder.iv.setBackground(context.getDrawable(R.drawable.shap_devider));
                }
            } else {
                myHolder.iv.setBackground(null);
            }
            Glide.with(context)
                    .load(list.get(position))
                    .into(myHolder.iv);
            //设置item点击事件
            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItem.onItemClickListener(v, myHolder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        final ImageView iv;

        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    public void setBg(int posi) {
        pos = posi;
        notifyDataSetChanged();
    }

    //条目点击事件接口回调
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItem = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }
}