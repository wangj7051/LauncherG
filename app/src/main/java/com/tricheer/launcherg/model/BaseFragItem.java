package com.tricheer.launcherg.model;

import java.io.Serializable;

/**
 * Base Fragment Item class
 */
public class BaseFragItem implements Serializable {
    /**
     * Serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * Item Name
     */
    public String name = "";

    /**
     * Target Page Class
     */
    public Class<?> cls;

    /**
     * Class TAG
     */
    private String tag = "";

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
