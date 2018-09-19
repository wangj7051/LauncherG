package com.tricheer.launcherg;

import android.app.Application;

import com.tricheer.launcherg.engine.LanguageManager;
import com.tricheer.launcherg.engine.SysVolManager;
import com.tricheer.launcherg.engine.WallpaperUtil;
import com.tricheer.launcherg.engine.PreferUtils;
import com.tricheer.launcherg.utils.SysPowerManager;

/**
 * Launcher Application Class
 *
 * @author Jun.Wang
 */
public class LauncherApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        PreferUtils.initPreference(this);
        WallpaperUtil.init(this);
        LanguageManager.initSys(this);
        SysPowerManager.init(this);
        SysVolManager.instance().init(this);
    }
}
