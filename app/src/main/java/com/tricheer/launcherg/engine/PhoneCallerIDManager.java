package com.tricheer.launcherg.engine;

/**
 * Phone send caller id Manager
 *
 * @author Jun.Wang
 */
public class PhoneCallerIDManager {

    /**
     * System Font Delegate
     */
    public interface PhoneCallerIDDelegate {
        public void onSendCallerIdON(boolean isON);
    }
}
