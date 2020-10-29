package com.zhong.cardinals.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.URLUtil;

import androidx.annotation.DrawableRes;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallbacks;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.zhong.cardinals.util.CloseUtil;
import com.zhong.cardinals.util.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.facebook.common.internal.ByteStreams.copy;

/**
 * Created by zhong on 2015/9/23.
 * Fresco工具类
 */
public class FrescoManager {
    private static DraweeController controller;

    private FrescoManager() {
    }

    public static FrescoManager getInstance() {
        return Singleton.instance;
    }

    /**
     * 改变图片的尺寸
     *
     * @param imageInfo 获取的图片信息
     * @param maxWidth  可显示的最大宽度
     * @param view      图片对象
     */
    private static void changeImageSize(ImageInfo imageInfo, int maxWidth, SimpleDraweeView view) {
        int height = imageInfo.getHeight();
        int width = imageInfo.getWidth();
        float scaleWidth = (float) maxWidth / width;
        int realHeight = (int) (height * scaleWidth);
        int realWidth = (int) (width * scaleWidth);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = realHeight;
        params.width = realWidth;
        view.setLayoutParams(params);
    }

    /**
     * 将图片插入缓存
     *
     * @param key         保存文件的可以
     * @param inputStream 图片转化成的InputStream
     * @return 是否插入成功
     */
    public static boolean insertImageToCache(String key, InputStream inputStream) {
        try {
            CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(key), null);
            ImagePipelineFactory.getInstance().getMainFileCache().insert(cacheKey, WriterCallbacks.from(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseUtil.close(inputStream);
        }
        return true;

    }

    /**
     * 初始化配置
     *
     * @param context Context对象
     */
    public void init(Context context) {

        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setResizeAndRotateEnabledForNetwork(true)
                .build();
        Fresco.initialize(context, imagePipelineConfig);
    }

    /**
     * 加载头像
     *
     * @param view View
     * @param url  图片路径
     */
    public void displayPortrait(SimpleDraweeView view, final String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Uri uri;
        if (URLUtil.isNetworkUrl(url)) {

            uri = Uri.parse(url);
        } else {
            uri = Uri.fromFile(new File(url));
        }

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .setResizeOptions(new ResizeOptions(100, 100))
                .setLocalThumbnailPreviewsEnabled(true)
                .build();
        controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(new ControllerListener<ImageInfo>() {
                    @Override
                    public void onSubmit(String id, Object callerContext) {

                    }

                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        Logger.i("下载成功：" + imageInfo.getWidth() + " ; " + imageInfo.getHeight() + " ; " + imageInfo.getQualityInfo().isOfGoodEnoughQuality());
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {

                    }

                    @Override
                    public void onIntermediateImageFailed(String id, Throwable throwable) {
                        Logger.i("IntermediateImageFailed :" + url + " ; " + throwable.getMessage());
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        Logger.i("fail :" + url + " ; " + throwable.getMessage());
                    }

                    @Override
                    public void onRelease(String id) {

                    }
                })
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
                .setControllerListener(new ControllerListener<ImageInfo>() {
                    @Override
                    public void onSubmit(String id, Object callerContext) {

                    }

                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        // Logger.i("onFinalImageSet :" + url + " ; " + imageInfo.getQualityInfo().isOfGoodEnoughQuality());
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                        //  Logger.i("onIntermediateImageSet :" + url + " ; " + imageInfo.getQualityInfo().isOfGoodEnoughQuality());
                    }

                    @Override
                    public void onIntermediateImageFailed(String id, Throwable throwable) {
                        //  Logger.i("onIntermediateImageFailed :" + url + " ; " + throwable.getMessage());
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        //  Logger.i("failure :" + url + " ; " + throwable.getMessage());
                    }

                    @Override
                    public void onRelease(String id) {

                    }
                })
                .setOldController(view.getController())
                .build();
        view.setController(controller);
    }

    public void display(SimpleDraweeView view, @DrawableRes int resId) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithResourceId(resId).build();
        controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
                .setOldController(view.getController())
                .setAutoPlayAnimations(true)
                .build();
        view.setController(controller);
    }

    public void display(SimpleDraweeView view, @DrawableRes int resId, Postprocessor postprocessor) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithResourceId(resId)
                .setPostprocessor(postprocessor).setProgressiveRenderingEnabled(true)
                .build();
        controller = Fresco.newDraweeControllerBuilder().setImageRequest(request)
                .setOldController(view.getController())
                .setAutoPlayAnimations(true)
                .build();
        view.setController(controller);
    }


    /**
     * 多图加载
     *
     * @param view        View
     * @param lowImageUrl 低分辨率图片
     * @param imageUrl    高分辨率图片
     */

    public void display(SimpleDraweeView view, String lowImageUrl, String imageUrl) {
        controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(imageUrl))
                .setLowResImageRequest(ImageRequest.fromUri(lowImageUrl))
                .setOldController(view.getController())
                .setAutoPlayAnimations(true)
                .build();
        view.setController(controller);
    }

    /**
     * @param view View
     * @param url  网络路径或者本地路径
     */

    public void display(SimpleDraweeView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri;
            if (URLUtil.isNetworkUrl(url)) {
                uri = Uri.parse(url);
            } else {
                uri = Uri.fromFile(new File(url));
            }
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .setRotationOptions(RotationOptions.autoRotateAtRenderTime())
                    .build();

            controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setTapToRetryEnabled(true)
                    .setOldController(view.getController())
                    .build();
            view.setController(controller);

        }

    }


    /**
     * @param view     View
     * @param url      图片路径
     * @param maxWidth 最大宽度
     */

    public void display(final SimpleDraweeView view, String url, final int maxWidth) {
        if (!TextUtils.isEmpty(url)) {
            if (maxWidth == 0) {
                display(view, url);
            } else if (maxWidth > 0) {

                Uri uri;
                if (URLUtil.isNetworkUrl(url)) {
                    uri = Uri.parse(url);
                } else {
                    uri = Uri.fromFile(new File(url));
                }
                ImageRequest request = ImageRequestBuilder
                        .newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        //.setResizeOptions(new ResizeOptions(100, 100))
                        .setLocalThumbnailPreviewsEnabled(true)
                        .setRotationOptions(RotationOptions.autoRotateAtRenderTime())
                        .build();

                controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setControllerListener(new ControllerListener<ImageInfo>() {
                            @Override
                            public void onSubmit(String id, Object callerContext) {

                            }

                            @Override
                            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                                view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                                changeImageSize(imageInfo, maxWidth, view);
                            }

                            @Override
                            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {

                            }

                            @Override
                            public void onIntermediateImageFailed(String id, Throwable throwable) {

                            }

                            @Override
                            public void onFailure(String id, Throwable throwable) {

                            }

                            @Override
                            public void onRelease(String id) {

                            }
                        })
                        .setOldController(view.getController())
                        .build();
                view.setController(controller);

            }
        }


    }

    public void display(final SimpleDraweeView view, String url, final int maxWidth, final int maxHeight) {
        if (!TextUtils.isEmpty(url)) {
            if (maxWidth == 0 || maxHeight == 0) {
                display(view, url);
            } else if (maxWidth > 0) {
                ControllerListener listener = new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);

                        ViewGroup.LayoutParams params = view.getLayoutParams();
                        params.height = maxHeight;
                        params.width = maxWidth;
                        view.setLayoutParams(params);
                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        super.onFailure(id, throwable);
                    }
                };
                Uri uri;
                if (URLUtil.isNetworkUrl(url)) {
                    uri = Uri.parse(url);
                } else {
                    uri = Uri.fromFile(new File(url));
                }
                ImageRequest request = ImageRequestBuilder
                        .newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        //.setResizeOptions(new ResizeOptions(100, 100))
                        .setLocalThumbnailPreviewsEnabled(true)
                        .setRotationOptions(RotationOptions.autoRotateAtRenderTime())
                        .build();

                controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setControllerListener(listener)
                        .setOldController(view.getController())
                        .build();
                view.setController(controller);

            }
        }


    }


    public void display(final SimpleDraweeView view, String url, final LoadCallbackListener callback) {
        Uri uri;
        if (URLUtil.isNetworkUrl(url)) {
            uri = Uri.parse(url);
        } else {
            uri = Uri.fromFile(new File(url));
        }
        ControllerListener listener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                callback.onFinish();
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
            }
        };
        controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .setOldController(view.getController())
                .setControllerListener(listener)
                .build();
        view.setController(controller);

    }

    public void display(SimpleDraweeView view, String url, Postprocessor postprocessor) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri;
            if (URLUtil.isNetworkUrl(url)) {
                uri = Uri.parse(url);
            } else {
                uri = Uri.fromFile(new File(url));
            }
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setPostprocessor(postprocessor)
                    .setProgressiveRenderingEnabled(true)
                    //.setResizeOptions(new ResizeOptions(100, 100))
                    .setLocalThumbnailPreviewsEnabled(true)
                    .setRotationOptions(RotationOptions.autoRotateAtRenderTime())
                    .build();

            controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(view.getController())
                    .build();
            view.setController(controller);

        }

    }

    public Bitmap getBitmap(String httpUrl) {
        if (URLUtil.isNetworkUrl(httpUrl)) {
            InputStream in = null;
            BufferedOutputStream out = null;
            ByteArrayOutputStream dataStream = null;
            try {
                in = new BufferedInputStream(new URL(httpUrl).openStream(), 1024);
                dataStream = new ByteArrayOutputStream();
                out = new BufferedOutputStream(dataStream, 1024);
                copy(in, out);
                out.flush();
                byte[] data = dataStream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                CloseUtil.close(in);
                CloseUtil.close(out);
                CloseUtil.close(dataStream);
            }
        } else {
            return null;
        }

    }

    /**
     * @param uri 需要查找的uri
     * @return uri对应的图片是否在缓存中
     */
    public boolean isCache(Uri uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        return imagePipeline.isInBitmapMemoryCache(uri);
    }


    /**
     * 清除缓存中的一条url
     *
     * @param uri 需要被清除的url
     */
    public void evictUri(Uri uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.evictFromMemoryCache(uri);
        imagePipeline.evictFromDiskCache(uri);
        imagePipeline.evictFromCache(uri);

    }

    /**
     * 清除所有缓存
     */
    public void clear() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
        imagePipeline.clearCaches();
    }

    /**
     * @param url 指定的url
     * @return 获取制定url的图片，需要保存为xx.jpg格式。
     */
    public File getFileFromDiskCache(String url) {
        File localFile = null;
        if (!TextUtils.isEmpty(url)) {
            CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(url), null);
            BinaryResource resource;
            if (ImagePipelineFactory.getInstance().getMainFileCache().hasKeySync(cacheKey)) {
                resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            } else if (ImagePipelineFactory.getInstance().getSmallImageFileCache().hasKey(cacheKey)) {
                resource = ImagePipelineFactory.getInstance().getSmallImageFileCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            }
        }
        return localFile;
    }

    public interface LoadCallbackListener {
        void onFinish();
    }

    private static final class Singleton {
        private static final FrescoManager instance = new FrescoManager();
    }


}
