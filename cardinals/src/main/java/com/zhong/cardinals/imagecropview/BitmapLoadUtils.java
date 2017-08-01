/*
 * Copyright (c) 2015 Naver Corp.
 * @Author Ohkyun Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhong.cardinals.imagecropview;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;

public class BitmapLoadUtils {

    private static final String TAG = "BitmapLoadUtils";

    public static Bitmap decode(String path, int reqWidth, int reqHeight) {
        return decode(path, reqWidth, reqHeight, false);
    }

    public static Bitmap decode(String path, int reqWidth, int reqHeight, boolean useImageView) {
        if (path == null) {
            return null;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight, useImageView);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        Bitmap decodeSampledBitmap = null;
        boolean isSuccess = false;
        while (!isSuccess) {
            try {
                isSuccess = true;
                decodeSampledBitmap = BitmapFactory.decodeFile(path, options);
            } catch (OutOfMemoryError ex) {
                Log.w(TAG, "BitmapLoadUtils decode OutOfMemoryError");
                options.inSampleSize = options.inSampleSize * 2;
                isSuccess = false;
            }

        }

        ExifInterface exif = getExif(path);
        if (exif == null) {
            return decodeSampledBitmap;
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int rotationInDegrees = exifToDegrees(exifOrientation);
        return rotate(decodeSampledBitmap, rotationInDegrees);

    }

    public static Bitmap rotate(Bitmap bitmap, int degrees) {
        if (degrees != 0 && bitmap != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if (bitmap != converted) {
                    bitmap.recycle();
                    bitmap = converted;
                }
            } catch (OutOfMemoryError ex) {
                // if out of memory, return original bitmap
            }
        }
        return bitmap;
    }


    public static String getPathFromUri(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor == null) {
                return null;
            }
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight, boolean useImageView) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }

            if (useImageView) {
                final int maxTextureSize = GLUtils.getMaxTextureSize();
                while ((height / inSampleSize) > maxTextureSize
                        || (width / inSampleSize) > maxTextureSize) {
                    inSampleSize *= 2;
                }
            }
        }

        return inSampleSize;
    }

    private static ExifInterface getExif(String path) {
        try {
            return new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
}
