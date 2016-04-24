package com.example.aberchek.appenings;

/**
 * Created by aberchek on 4/23/16.
 */
public class happeningContact {
    private String name = null;
    private String phone = null;
    private String link = null;

    public happeningContact(String name, String phone, String link) {
        this.name = name;
        this.phone = phone;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLink() {
        return link;
    }

    public boolean hasSubstring(String ToSearch)
    {
        if(name != null && name.toLowerCase().contains(ToSearch.toLowerCase()))
        {
            return true;
        }
        else if(phone != null && phone.toLowerCase().contains(ToSearch.toLowerCase()))
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
