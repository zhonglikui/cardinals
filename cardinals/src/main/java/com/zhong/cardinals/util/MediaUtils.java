package com.zhong.cardinals.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.zhong.cardinals.mode.ImageInfo;
import com.zhong.cardinals.mode.MusicInfo;
import com.zhong.cardinals.mode.VideoInfo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2017/4/19.
 */

public class MediaUtils {
    /**
     * 获取手机上所有的视频
     */
    public static List<VideoInfo> getVideos(Context context, OnVideoListener listener) {
        List<VideoInfo> listAll = new ArrayList<>();
        long startTime = System.currentTimeMillis();   //获取开始时间
        String[] videoProjection = new String[]{
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATE_MODIFIED,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.RESOLUTION,
                MediaStore.Video.Media.WIDTH,
                MediaStore.Video.Media.HEIGHT
        };
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null/*videoProjection*/, null, null, null /*MediaStore.Video.Media.DATE_MODIFIED + " desc"*/);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    VideoInfo info = new VideoInfo();
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                    String dateModified = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));
                    String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                    String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                    long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)); // 大小
                    String resolution = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION));
                    int width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH));
                    int height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT));
                    int isPrivate = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.IS_PRIVATE));
                    Logger.i("is private=" + isPrivate + " ; displayName=" + displayName);


                    info.setId(id);
                    info.setTitle(title);
                    info.setPath(path); // 路径
                    info.setDuration(duration);  // 时长
                    info.setDisplayName(displayName);
                    info.setMimeType(mimeType);
                    info.setSize(size);
                    info.setDateModified(dateModified);
                    info.setResolution(resolution);
                    info.setWidth(width);
                    info.setHeight(height);
                    if (!TextUtils.isEmpty(resolution)) {

                    }

                    //获取缩略图

                    long thumStartTime = System.currentTimeMillis();
                    Bitmap bitmap = MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(), id, MediaStore.Video.Thumbnails.MICRO_KIND, null);
                    String thumbPath = null;
                    if (bitmap != null) {
                        try {
                            File cacheFile = new File(SDUtil.getCacheFile(), String.valueOf(id) + ".jpg");
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(cacheFile));
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bufferedOutputStream);
                            bufferedOutputStream.flush();
                            CloseUtil.close(bufferedOutputStream);
                            thumbPath = cacheFile.getAbsolutePath();
                            info.setThumbPath(thumbPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    long thumbEndTime = System.currentTimeMillis(); //获取结束时间
                    // Logger.i("id=" + String.valueOf(id) + "获取到缩略图：" + thumbPath + "耗时：" + (thumbEndTime - thumStartTime) + "ms");
                    if (info.getDuration() >= 3 * 1000 && !TextUtils.isEmpty(displayName) && displayName.endsWith(".mp4") && !TextUtils.isEmpty(thumbPath)) {
                        listAll.add(info);
                        if (listener != null) {
                            listener.onVideoItem(info);
                        }
                        Logger.i("扫描出的有效视频：id=" + id + " ; title=" + title + " ; data=" + path + " ; duration=" + duration
                                        + " ; width=" + width + " ; height=" + height + " ; dateModified=" + dateModified + " ; displayName="
                                        + displayName + " ; mimeType=" + mimeType + " ; size=" + size + " ; resolution=" + resolution
                                        + " ; 获取到缩略图：" + thumbPath + " ; 耗时：" + (thumbEndTime - thumStartTime) + "ms"
                                //扫描出的视频：id=105854;title=谍影重重;data=/storage/emulated/0/谍影重重.mp4;duration=7122967;dateModified=1474026683;displayName=谍影重重.mp4;mimeType=video/mp4;size=2673559436;resolution=1280x536
                        );
                    }


                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                Logger.e("扫描出错", e.getCause());
            } finally {
                cursor.close();
            }

        }
        long endTime = System.currentTimeMillis(); //获取结束时间

        Logger.i("共扫描：" + listAll.size() + "条，耗时" + (endTime - startTime) + "ms");//全部/指定列：24ms ：添加缩略图：129
        return listAll;

    }

    /**
     * 获取手机上所有的图片
     */
    public static List<ImageInfo> getImages(Context context) {
        List<ImageInfo> imageInfos = new ArrayList<>();
        ImageInfo imageInfo;

        ContentResolver mContentResolver = context.getContentResolver();
        Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Images.Media.DATE_ADDED + " desc");
        if (mCursor != null) {


            // Cursor nailCursor = null;
            while (mCursor.moveToNext()) {
                String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                long id = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Images.Media._ID));
                // File file = new File(path);
                // if (!(file.length() / (1024 * 1024) > 1 && path.substring(path.lastIndexOf(".") + 1).equalsIgnoreCase("gif"))) {
                //   String[] nailColumns = {MediaStore.Images.Thumbnails._ID, MediaStore.Images.Thumbnails.DATA, MediaStore.Images.Thumbnails.IMAGE_ID};
         /*   nailCursor = mContentResolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, nailColumns, nailColumns[2] + "=" + id, null, null);
            String nailPath = null;
            if (nailCursor.moveToFirst()) {
                nailPath = nailCursor.getString(nailCursor.getColumnIndex(MediaStore.Images.Thumbnails.DEFAULT_SORT_ORDER));
            }*/


                imageInfo = new ImageInfo();
                imageInfo.setImageId(id);
                imageInfo.setImagePath(path);
                // imageInfo.setThumbnailsPath(nailPath);//
                imageInfos.add(imageInfo);
                //  }

            }
        }
        // CloseUtil.close(nailCursor);
        CloseUtil.close(mCursor);
        return imageInfos;

    }

    /**
     * 获取手机上所有的音乐
     *
     * @param mContext
     * @return
     */
    public static List<MusicInfo> getMusic(Context mContext) {
        long time1 = System.currentTimeMillis();
        List<MusicInfo> musicList = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

        if (cursor != null) {


            MusicInfo musicInfo;


            while (cursor.moveToNext()) {


                musicInfo = new MusicInfo();

                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String display = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String type = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                int size = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                boolean isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)) == 1;
                Logger.i("isMusic: " + (isMusic) + " ; display:" + display + " ; title:" + title + " ; artist:" + artist + " ; path:" + path + " ; type:" + type + " ; album:" + album + " ; duration:" + duration + " ; size:" + size);


                if (isMusic && (display.endsWith(".mp3") || display.endsWith(".aac")) && duration > 30 * 1000) {
                    musicInfo.setTitle(title);
                    musicInfo.setArtist(artist);
                    musicInfo.setPath(path);
                    musicInfo.setType(type);
                    musicInfo.setAlbum(album);
                    musicInfo.setDuration(duration);
                    musicInfo.setSize(size);
                    musicList.add(musicInfo);
                }


            }
        }
        long time2 = System.currentTimeMillis();
        Logger.i("扫描到有效音乐共：" + musicList.size() + " 条耗时：" + (time2 - time1) + "ms");
        return musicList;
    }

    public interface OnVideoListener {
        void onVideoItem(VideoInfo info);
    }
}
