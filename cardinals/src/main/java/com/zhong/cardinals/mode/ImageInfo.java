package com.zhong.cardinals.mode;

import java.io.Serializable;

/**
 * 手机上图片的数据模型
 */
public class ImageInfo implements Serializable {
    private long imageId;
    private String imagePath;
    private String thumbnailsPath;
    private boolean isSelected = false;

    public String getThumbnailsPath() {
        return thumbnailsPath;
    }

    public void setThumbnailsPath(String thumbnailsPath) {
        this.thumbnailsPath = thumbnailsPath;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
