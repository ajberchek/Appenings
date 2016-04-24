package com.example.aberchek.appenings;

/**
 * Created by aberchek on 4/23/16.
 */
public class happeningLocation {
    private String address;
    private String link;

    public happeningLocation(String addr, String lnk)
    {
        address = addr;
        link = lnk;
    }

    public String getAddress() {
        return address;
    }

    public String getLink() {
        return link;
    }

}
