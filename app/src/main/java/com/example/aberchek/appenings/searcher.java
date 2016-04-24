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


    private String[] FoodSearches = {"Food","Ribs","barbeque","bbq","grill","ice cream","kettle corn"};
    private String[] engineeringSearches = {"Robots","engineering","mechanical","computer"};
    private String[] concertSearches = {"Rapper","Concert","cowcella","Cowchella"};

    public searcher()
    {
        toSearchForMap.put("FOOD",new searchKeyWord("FOOD",FoodSearches));
        toSearchForMap.put("ENGR",new searchKeyWord("ENGR", engineeringSearches));
        toSearchForMap.put("CONCERT",new searchKeyWord("CONCERT",concertSearches));
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


}
