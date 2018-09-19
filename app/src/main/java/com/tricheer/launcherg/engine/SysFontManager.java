package com.tricheer.launcherg.engine;

import com.tricheer.launcherg.R;

/**
 * System Font Manager
 *
 * @author Jun.Wang
 */
public class SysFontManager {

    //Font type
    public static final String LARGE = "FONT_LARGE";
    public static final String MIDDLE = "FONT_MIDDLE";
    public static final String SMALL = "FONT_SMALL";
    public static final String VERY_SMALL = "FONT_VERY_SMALL";


    /**
     * System Font Delegate
     */
    public interface SysFontDelegate {
        public void onFontSizeChanged();
    }

    /**
     * Get Launcher style ID
     *
     * @return int
     */
    public static int getFontStyleID() {
        String storedFontSize = PreferUtils.getCurrFontSize(false, "");
        if (SysFontManager.LARGE.equals(storedFontSize)) {
            return R.style.THEME_LARGE;
        } else if (SysFontManager.MIDDLE.equals(storedFontSize)) {
            return R.style.THEME_MIDDLE;
        } else if (SysFontManager.SMALL.equals(storedFontSize)) {
            return R.style.THEME_SMALL;
        } else if (SysFontManager.VERY_SMALL.equals(storedFontSize)) {
            return R.style.THEME_VERY_SMALL;
        }
        return R.style.THEME_MIDDLE;
    }

    /**
     * Get Launcher style ID
     *
     * @return int
     */
    public static int getFontStyleID2() {
        String storedFontSize = PreferUtils.getCurrFontSize(false, "");
        if (SysFontManager.LARGE.equals(storedFontSize)) {
            return R.style.THEME_LARGE_2;
        } else if (SysFontManager.MIDDLE.equals(storedFontSize)) {
            return R.style.THEME_MIDDLE_2;
        } else if (SysFontManager.SMALL.equals(storedFontSize)) {
            return R.style.THEME_SMALL_2;
        } else if (SysFontManager.VERY_SMALL.equals(storedFontSize)) {
            return R.style.THEME_VERY_SMALL_2;
        }
        return R.style.THEME_MIDDLE_2;
    }
}
