package com.change.pdfscrolltrumbnail.vvlinkage.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.change.pdfscrolltrumbnail.R;
import com.change.pdfscrolltrumbnail.vvlinkage.bean.LinkageImageBean;

import java.util.ArrayList;

/**
 * Created by fenrir-xjc on 2020/05/14.
 */
public class VvListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<LinkageImageBean> list;
    private OnItemClickListener mOnItemClickListener;

    public VvListAdapter(Context context, ArrayList<LinkageImageBean> picList) {
        this.mContext = context;
        this.list = picList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout =  LayoutInflater.from(mContext).inflate(R.layout.item_listbase, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            String url = list.get(position).getImgList().get(0);
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
                    .into(((MyHolder)holder).iv);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView,position);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public MyHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
