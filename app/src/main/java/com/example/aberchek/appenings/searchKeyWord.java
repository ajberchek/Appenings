package com.example.aberchek.appenings;

/**
 * Created by aberchek on 4/24/16.
 */
public class searchKeyWord {

    private String[] value;
    private String key;
    private boolean isSelected;


    public searchKeyWord( String key, String[] value) {
        this.value = value;
        this.key = key;
    }

    public String[] getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
