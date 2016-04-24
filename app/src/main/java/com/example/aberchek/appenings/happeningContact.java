package com.example.aberchek.appenings;

/**
 * Created by aberchek on 4/23/16.
 */
public class happeningContact {
    private String name;
    private String phone;
    private String link;

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

}
