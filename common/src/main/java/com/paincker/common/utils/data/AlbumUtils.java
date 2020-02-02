package com.paincker.common.utils.data;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.SparseArray;

import java.util.ArrayList;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

/**
 * 系统相册图片相关
 */
public class AlbumUtils {

    /**
     * 获取所有相册信息列表
     */
    public static ArrayList<ImageAlbumData> getAlbumDataList(Context context) {

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Images.Media.BUCKET_ID,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.Media.DATA,
                },
                null, null,
                MediaStore.Images.Media.BUCKET_ID + " DESC"
        );

        if (cursor == null) {
            return null;
        }

        ArrayList<ImageAlbumData> list = new ArrayList<ImageAlbumData>();
        SparseArray<ImageAlbumData> map = new SparseArray<ImageAlbumData>();

        int indexId = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
        int indexName = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int indexData = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(indexId);
            ImageAlbumData album = map.get(id);
            if (album == null) {
                album = new ImageAlbumData(id, cursor.getString(indexName),
                        getFileUrl(cursor.getString(indexData)));
                map.put(id, album);
                list.add(album);
            }
            album.addCount();
        }
        cursor.close();
        return list;
    }

    /**
     * 获取指定id的相册中所有图片。根据修改时间排序。
     *
     * @param bucketId 相册id
     */
    public static ArrayList<String> getAlbumImages(Context context, int bucketId) {

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Images.Media.DATA,
                },
                MediaStore.Images.Media.BUCKET_ID + "=" + bucketId,
                null,
                MediaStore.Images.Media.DATE_MODIFIED + " DESC"
        );

        if (cursor == null) {
            return null;
        }

        ArrayList<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String data = cursor.getString(0);
            if (TextUtils.isEmpty(data)) continue;
            list.add(getFileUrl(data));
        }
        cursor.close();

        return list;
    }

    /**
     * 获取所有图片，按修改时间排序
     */
    public static ArrayList<String> getAllImages(Context context) {
        return getRecentImages(context, -1);
    }

    /**
     * 获取最近图片，按修改时间排序。最多返回limit条
     *
     * @param limit 最多获取条数，为0或负数则表示不限制，即全部图片
     */
    public static ArrayList<String> getRecentImages(Context context, int limit) {
        String orderAndLimit = MediaStore.Images.Media.DATE_MODIFIED + " DESC";
        if (limit > 0) {
            orderAndLimit += " LIMIT " + limit;
        }
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Images.Media.DATA,
                },
                null, null,
                orderAndLimit
        );

        if (cursor == null) {
            return null;
        }

        ArrayList<String> list = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String data = cursor.getString(0);
            if (TextUtils.isEmpty(data)) continue;
            list.add(getFileUrl(data));
        }
        cursor.close();

        return list;
    }

    /**
     * 文件路径转换成url
     */
    public static String getFileUrl(String path) {
        return "file://" + path;
    }

    /**
     * 图片相册信息
     */
    public static class ImageAlbumData {

        public static final int ID_ALL_IMG = -1;
        public static final String NAME_ALL_IMG = "全部图片";

        private int mId; // 相册id
        private String mName; // 相册名
        private String mFirstImageURL; // 第一张图片的URL
        private int mImageCount; // 图片数目

        public ImageAlbumData(int id, String name, String firstImage) {
            this(id, name, 0, firstImage);
        }

        private ImageAlbumData(int id, String name, int count, String firstImage) {
            mId = id;
            mName = name;
            mImageCount = count;
            mFirstImageURL = firstImage;
        }

        public static ImageAlbumData createAllImageAlbum(int count, String firstImageURL) {
            return new ImageAlbumData(ID_ALL_IMG, NAME_ALL_IMG, count, firstImageURL);
        }

        public void addCount() {
            ++mImageCount;
        }

        public int getId() {
            return mId;
        }

        public String getName() {
            return mName;
        }

        public int getImageCount() {
            return mImageCount;
        }

        public String getFirstImageURL() {
            return mFirstImageURL;
        }
    }
}
