package com.change.pdfscrolltrumbnail.vrlinkage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.change.pdfscrolltrumbnail.R;

import java.util.ArrayList;

/**
 * Created by louliang on 2018/05/15.
 * 主页条目列表
 */

public class ListAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<String> list;

    public ListAdapter(Context context, ArrayList<String> picList) {
        this.mContext = context;
        this.list = picList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listbase, null, false);
        ImageView iv = convertView.findViewById(R.id.iv);
        String url = list.get(position);
        Glide.with(mContext)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d("Wain", "加载失败 errorMsg:" + (e != null ? e.getMessage() : "null"));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("Wain", "成功  Drawable Name:" + resource.getClass().getCanonicalName());
                        return false;
                    }
                })
                .into(iv);
        return convertView;
    }
}
