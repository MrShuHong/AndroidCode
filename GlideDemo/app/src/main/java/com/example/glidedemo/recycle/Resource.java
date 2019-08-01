package com.example.glidedemo.recycle;

import android.graphics.Bitmap;

import com.example.glidedemo.Key;

/**
 * Author: shuhong
 * Date: 2019/7/30 21:56
 * Description:
 */
public class Resource {

    private Bitmap mBitmap;

    /**
     * 引用计数
     * 被使用一次就加1
     */
    private int mAcquired;

    private Key mKey;

    private ResourceListener mListener;

    public void setResourceListener(Key key, ResourceListener resourceListener) {
        this.mKey = key;
        this.mListener = resourceListener;
    }


    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void recycle(){
        if (mAcquired > 0){
            return;
        }

        if (!mBitmap.isRecycled()){
            mBitmap.recycle();
        }
    }

    public void released(){
        if(--mAcquired == 0){
            mListener.onResourceReleased(mKey, this);
        }
    }

    public void acquired(){
        if(mBitmap.isRecycled()){
            throw new IllegalStateException("Acquire a recycled resource");
        }
        ++mAcquired;
    }

    public interface ResourceListener {
        void onResourceReleased(Key key, Resource resource);
    }

}
