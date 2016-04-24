package com.example.aberchek.appenings;

import java.util.Arrays;

/**
 * Created by aberchek on 4/23/16.
 */


//There is the option to redirect the user to

public class happening {
    private String Title = null;           //Comes from the json "summary" key
    private String subscriptionID = null;
    private String Link = null;            //Comes from teh json "link" key
    private String TimeDate = null;            //Comes from the json "formattedDate" key
    private happeningLocation loc = null;  //Comes from the json "location" key
    private happeningContact contact = null; //Comes from teh json "contact" key
    private String[] categories = null;        //comes from the "categories" key
    private String description = null;         //This is the "description" key
    private String cost = null;                //This is the "cost" key


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

    public happening()
    {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        happening happening = (happening) o;

        if (Title != null ? !Title.equals(happening.Title) : happening.Title != null) return false;
        if (subscriptionID != null ? !subscriptionID.equals(happening.subscriptionID) : happening.subscriptionID != null)
            return false;
        if (Link != null ? !Link.equals(happening.Link) : happening.Link != null) return false;
        if (TimeDate != null ? !TimeDate.equals(happening.TimeDate) : happening.TimeDate != null)
            return false;
        if (loc != null ? !loc.equals(happening.loc) : happening.loc != null) return false;
        if (contact != null ? !contact.equals(happening.contact) : happening.contact != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(categories, happening.categories)) return false;
        if (description != null ? !description.equals(happening.description) : happening.description != null)
            return false;
        return cost != null ? cost.equals(happening.cost) : happening.cost == null;

    }

    @Override
    public int hashCode() {
        int result = Title != null ? Title.hashCode() : 0;
        result = 31 * result + (subscriptionID != null ? subscriptionID.hashCode() : 0);
        result = 31 * result + (Link != null ? Link.hashCode() : 0);
        result = 31 * result + (TimeDate != null ? TimeDate.hashCode() : 0);
        result = 31 * result + (loc != null ? loc.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(categories);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    public boolean hasSubstring(String subStrToSearch)
    {
        if(Title != null && Title.toLowerCase().contains(subStrToSearch.toLowerCase()))
        {
            return true;
        }
        else if(subscriptionID != null && subscriptionID.toLowerCase().contains(subStrToSearch.toLowerCase()))
        {
            return true;
        }
        else if(Link != null && Link.toLowerCase().contains(subStrToSearch.toLowerCase()))
        {
            return true;
        }
        else if(TimeDate != null && TimeDate.toLowerCase().contains(subStrToSearch.toLowerCase()))
        {
            return true;
        }
        else if(loc != null && loc.hasSubstring(subStrToSearch))
        {
            return true;
        }
        else if(contact != null && contact.hasSubstring(subStrToSearch))
        {
            return true;
        }
        else if(categories != null)
        {
            for(String s : categories)
            {
                if(s.toLowerCase().contains(subStrToSearch.toLowerCase()))
                {
                    return true;
                }
            }
        }
        else if(description != null && description.toLowerCase().contains(subStrToSearch.toLowerCase()))
        {
            return true;
        }
        else if(cost != null && cost.toLowerCase().contains(subStrToSearch.toLowerCase()))
        {
            return true;
        }

        return false;


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
