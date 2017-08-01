package com.zhong.cardinals.mode;

import java.io.File;

/**
 * Created by zhong on 2017/2/9.
 */

public class Share {
    private String title;
    private String text;
    private String targeturl;
    private String image;
    private File file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTargeturl() {
        return targeturl;
    }

    public void setTargeturl(String targeturl) {
        this.targeturl = targeturl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
