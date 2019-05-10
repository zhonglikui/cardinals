package com.zhong.cardinals.mode;

import java.io.Serializable;

/**
 * Created by zhong on 2016/11/24.
 */

public class UpdateAppBean implements Serializable {
    private long id;

    private String title;
    private long totalSize;
    private long downloadedSize;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getDownloadedSize() {
        return downloadedSize;
    }

    public void setDownloadedSize(long downloadedSize) {
        this.downloadedSize = downloadedSize;
    }

}
