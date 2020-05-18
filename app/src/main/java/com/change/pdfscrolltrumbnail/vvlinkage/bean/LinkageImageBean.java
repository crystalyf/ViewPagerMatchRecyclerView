package com.change.pdfscrolltrumbnail.vvlinkage.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Fenrir-xingjunchao on 2020/5/14.
 * <p>
 * ListBaseView画面加载所需的bean
 **/
public class LinkageImageBean implements Serializable {
    private List<String> imgList;

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
