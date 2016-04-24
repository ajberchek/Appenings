package com.example.aberchek.appenings;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aberchek on 4/24/16.
 */
public class searcher
{
    private HashMap<String,searchKeyWord> toSearchForMap = new HashMap<String,searchKeyWord>();


    private String[] FoodSearches = {"Food","Ribs","barbeque","bbq","grill","ice cream","kettle corn","Lunch"};
    private String[] research = {"poster"};
    private String[] competitions = {"tournament","competition","game","field","enemy","challenge","match"};
    private String[] engineeringSearches = {"Robots","Maker","engineering","mechanical","computer","Google","environmental","material science","bio sensors"};
    private String[] concertSearches = {"Rapper","Concert","cowcella","Cowchella","music","Dj","dance",""};
    private String[] sportSearches = {"Soccer","Football","Dodgeball","Volleyball","Basketball" };
    private String[] naturalscienceSearches = {"Chemistry","Earth System Science", "Biology","Physics","mathematics", "microbiology","cell","climate change", "water","soil","pollution",};
    private String[] socialscienceSearches = {"humanities","English","Cognitive Science","Anthropology","Economics","history","management","business","political science","Psychology","public health","sociology","spanish"};

    private String[] allString = {""};
    public searcher()
    {

        toSearchForMap.put("FOOD",new searchKeyWord("FOOD",FoodSearches));
        toSearchForMap.put("COMP",new searchKeyWord("COMP",competitions));
        toSearchForMap.put("ENGR",new searchKeyWord("ENGR", engineeringSearches));
        toSearchForMap.put("CONCERT",new searchKeyWord("CONCERT",concertSearches));
        toSearchForMap.put("SPORT", new searchKeyWord("SPORT",sportSearches));
        toSearchForMap.put("NATURALSCIENCE", new searchKeyWord("NATURALSCIENCE",naturalscienceSearches));
        toSearchForMap.put("SOCIALSCIENCE", new searchKeyWord("SOCIALSCIENCE", socialscienceSearches));
        toSearchForMap.put("ALL",new searchKeyWord("ALL",allString));
        toSearchForMap.get("ALL").setSelected(true);
    }

    public HashMap<String, searchKeyWord> getToSearchForMap() {
        return toSearchForMap;
    }

    public void setToSearchForMap(HashMap<String, searchKeyWord> toSearchForMap) {
        this.toSearchForMap = toSearchForMap;
    }

    public ArrayList<String> getAllKeys()
    {
        ArrayList<String> toReturn = new ArrayList<String>();
        String[] elems = (String [])toSearchForMap.keySet().toArray();
        for(int i = 0; i < elems.length; ++i)
        {
            toReturn.add(elems[i]);
        }
        return toReturn;

    }


    public ArrayList<happening> getNoDuplicates(ArrayList<happening> a, ArrayList<happening> b)
    {
        ArrayList<happening> noDuplicates = new ArrayList<happening>();
        for(happening hap : b)
        {
            boolean alreadyThere = false;
            for(happening hapAlready: noDuplicates)
            {
                if (hapAlready.equals(hap))
                {
                    alreadyThere = true;
                    break;
                }
            }
            if(!alreadyThere)
            {
                noDuplicates.add(hap);
            }
        }
        for(happening hap : a)
        {
            boolean alreadyThere = false;
            for(happening hapAlready: noDuplicates)
            {
                if (hapAlready.equals(hap))
                {
                    alreadyThere = true;
                    break;
                }
            }
            if(!alreadyThere)
            {
                noDuplicates.add(hap);
            }
        }
        return noDuplicates;
    }

    public ArrayList<happening> getValidSearch(String toSearchForKey, ArrayList<happening> toSearch)
    {
        String[] toSearchFor = toSearchForMap.get(toSearchForKey).getValue();

        ArrayList<happening> containsKeyword = new ArrayList<happening>();
        for(happening hap : toSearch)
        {
            for(String s : toSearchFor)
            {
                if (hap.hasSubstring(s))
                {
                    containsKeyword.add(hap);
                    break;
                }
            }
        }
        containsKeyword = getNoDuplicates(containsKeyword,new ArrayList<happening>());
        return containsKeyword;
    }


    public ArrayList<happening> searchAllSelected(ArrayList<happening> toSearch)
    {
        if(toSearchForMap.get("ALL").isSelected())
        {
            return toSearch;
        }

        ArrayList<happening> toReturn = new ArrayList<happening>();
        for(String key : toSearchForMap.keySet())
        {
            if(toSearchForMap.get(key).isSelected())
            {
                toReturn = getNoDuplicates(toReturn,getValidSearch(key, toSearch));
            }
        }
        return toReturn;
    }

}
