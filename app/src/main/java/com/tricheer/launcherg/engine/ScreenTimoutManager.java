package com.tricheer.launcherg.engine;

/**
 * System screen timeout manager
 *
 * @author Jun.Wang
 */
public class ScreenTimoutManager {

    private static boolean isSetting = false;
    private static int firstNum = 0;
    private static int secondNum = 0;

    public interface ScreenTimoutDelegate {
        public void onScreenTimeoutChanged();
    }

    /**
     * Initialize
     * <p>e.g.(57 -> firstNum=5 & secondNum=7)</p>
     *
     * @param timeout:int
     */
    public static void initTimeout(int timeout) {
        reset();
        firstNum = timeout / 10;
        secondNum = timeout % 10;
    }

    /**
     * Set timeout when inputting keycode
     *
     * @param keyVal:int[0~9]
     */
    public static void setTimout(int keyVal) {
        if (isSetting) {
            isSetting = false;
            secondNum = keyVal;
        } else {
            isSetting = true;
            firstNum = keyVal;
            secondNum = 0;
        }
    }

    public static String getScreenTimeout() {
        return firstNum + "" + secondNum;
    }

    private static void reset() {
        isSetting = false;
        firstNum = 0;
        secondNum = 0;
    }
}
