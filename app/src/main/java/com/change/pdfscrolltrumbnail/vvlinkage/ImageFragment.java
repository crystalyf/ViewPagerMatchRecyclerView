package com.change.pdfscrolltrumbnail.vvlinkage;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.change.pdfscrolltrumbnail.R;

/**
 * Created by Fenrir-xingjunchao on 2020/5/18.
 * <p>
 * 装载每个滑动图片的Fragment
 */
public class ImageFragment extends Fragment {

    ImageView imageView;
    public static final String KEY = "imageUrl";
    static final String fragmentKey = "fragmentKey";
    String fragmentTag = "";

    //显示图片的url
    private static String imgUrl = "";

    public String getFragmentTag() {
        return fragmentTag;
    }

    public static ImageFragment newInstance(String imgUrl, String fragmentTag) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(fragmentKey, fragmentTag);
        bundle.putString(KEY, imgUrl);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.fragment_image, null);
        imageView = view.findViewById(R.id.imageView);
        Bundle arguments = getArguments();
        if (arguments != null) {
            imgUrl = arguments.getString(KEY);
        }
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(container.getContext()).load(imgUrl).into(imageView);
        }
        return view;
    }
}
