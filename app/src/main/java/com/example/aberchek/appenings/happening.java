package com.example.aberchek.appenings;

/**
 * Created by aberchek on 4/23/16.
 */


//There is the option to redirect the user to

public class happening {
    private String Title;           //Comes from the json "summary" key
    private String subscriptionID;
    private String Link;            //Comes from teh json "link" key
    private String TimeDate;            //Comes from the json "formattedDate" key
    private happeningLocation loc;  //Comes from the json "location" key
    private happeningContact contact; //Comes from teh json "contact" key
    private String[] categories;        //comes from the "categories" key
    private String description;         //This is the "description" key
    private String cost;                //This is the "cost" key


    public happening(String title, String subscriptionID, String link, String timeDate, happeningLocation loc, happeningContact contact, String[] categories, String description, String cost) {
        Title = title;
        this.subscriptionID = subscriptionID;
        Link = link;
        TimeDate = timeDate;
        this.loc = loc;
        this.contact = contact;
        this.categories = categories;
        this.description = description;
        this.cost = cost;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public String getLink() {
        return Link;
    }

    public String getTimeDate() {
        return TimeDate;
    }

    public happeningLocation getLoc() {
        return loc;
    }

    public happeningContact getContact() {
        return contact;
    }

    public String[] getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public String getCost() {
        return cost;
    }
}
