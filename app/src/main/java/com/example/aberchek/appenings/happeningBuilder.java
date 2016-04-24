package com.example.aberchek.appenings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aberchek on 4/23/16.
 */
public class happeningBuilder
{
    private JSONArray jsonArr = null;
    private JSONObject jsonObj = null;

    public happeningBuilder(JSONArray jsonArr) {
        this.jsonArr = jsonArr;
    }

    public happeningBuilder(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }

    public happening buildHappening() {
        try {
            JSONObject locationObjJSON = jsonObj.getJSONObject("location");
            JSONObject contactObjJSON = jsonObj.getJSONObject("contact");


            happeningLocation hl = new happeningLocation(locationObjJSON.getString("address"), locationObjJSON.getString("link"));
            happeningContact hc = new happeningContact(contactObjJSON.getString("name"), contactObjJSON.getString("phone"), contactObjJSON.getString("link"));


            ArrayList<String> categoryArray = new ArrayList<String>();
            JSONArray jsonArray = jsonObj.getJSONArray("categories");
            for(int i = 0, count = jsonArray.length(); i< count; i++)
            {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    categoryArray.add(jsonObject.toString());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            happening toReturn =
                    new happening(jsonObj.getString("summary"),jsonObj.getString("subscriptionId"),jsonObj.getString("link"), jsonObj.getString("formattedDate"),hl,hc,(String[])categoryArray.toArray(),jsonObj.getString("description"),jsonObj.getString("cost"));
            return toReturn;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<happening> buildHappeningArr()
    {
        if(jsonArr == null)
        {
            return null;
        }
        ArrayList<happening> toReturn = new ArrayList<happening>();
        for(int i = 0; i < jsonArr.length(); ++i)
        {
            try {
                jsonObj = jsonArr.getJSONObject(i);
                toReturn.add(buildHappening());
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


}
