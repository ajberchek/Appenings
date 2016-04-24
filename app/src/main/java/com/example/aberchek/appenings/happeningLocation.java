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

    public boolean hasSubstring(String ToSearch)
    {
        if(address != null && address.toLowerCase().contains(ToSearch.toLowerCase()))
        {
            return true;
        }
        else if(link != null && link.toLowerCase().contains(ToSearch.toLowerCase()))
        {
            return true;
        }
        return false;
    }

}
