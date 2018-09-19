package com.tricheer.launcherg.engine;

import android.app.WallpaperManager;
import android.content.Context;
import android.support.annotation.RawRes;
import android.util.SparseIntArray;

import com.tricheer.launcherg.R;

import java.io.IOException;

/**
 * Wallpaper setting class
 *
 * @author Jun.Wang
 */
public class WallpaperUtil {

    private static final SparseIntArray mSaWallpaerResIDs = new SparseIntArray();

    static {
        mSaWallpaerResIDs.put(0, R.drawable.display_wallpaper_00);
        mSaWallpaerResIDs.put(1, R.drawable.display_wallpaper_01);
        mSaWallpaerResIDs.put(2, R.drawable.display_wallpaper_02);
    }

    public static void init(Context context) {
        int currWallpaperIdx = PreferUtils.getCurrWallPaperIdx(false, -1);
        if (currWallpaperIdx <= -1) {
            int len = mSaWallpaerResIDs.size();
            if (len > 0) {
                setWallpaper(context, mSaWallpaerResIDs.get(0));
                PreferUtils.getCurrWallPaperIdx(true, 0);
            }
        }
    }

    public static void setNext(Context context) {
        //Check empty
        int len = mSaWallpaerResIDs.size();
        if (len == 0) {
            return;
        }

        //Check outOfIndex
        int currWallpaperIdx = PreferUtils.getCurrWallPaperIdx(false, -1);
        currWallpaperIdx++;
        if (currWallpaperIdx > len - 1) {
            return;
        }

        //Set selected wallpaper
        setWallpaper(context, mSaWallpaerResIDs.get(currWallpaperIdx));
        PreferUtils.getCurrWallPaperIdx(true, currWallpaperIdx);
    }

    public static void setPrev(Context context) {
        //Check empty
        int len = mSaWallpaerResIDs.size();
        if (len == 0) {
            return;
        }

        //Check outOfIndex
        int currWallpaperIdx = PreferUtils.getCurrWallPaperIdx(false, -1);
        currWallpaperIdx--;
        if (currWallpaperIdx < 0) {
            return;
        }

        //Set selected wallpaper
        setWallpaper(context, mSaWallpaerResIDs.get(currWallpaperIdx));
        PreferUtils.getCurrWallPaperIdx(true, currWallpaperIdx);
    }

    private static void setWallpaper(Context context, @RawRes int resid) {
        try {
            WallpaperManager manager = WallpaperManager.getInstance(context);
            manager.setResource(resid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
