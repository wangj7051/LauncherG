package com.tricheer.launcherg.engine;

/**
 * Common manager
 * <p>1. [Menu-Display-On-screen ID]</p>
 */
public class AppComManager {
    public static final String ON = "ON";
    public static final String OFF = "OFF";

    public interface AppComDelegate {
        public void refreshOnScreenID(boolean isShow);
    }
}
