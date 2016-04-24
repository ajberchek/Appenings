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
    private HashMap<String,String[]> toSearchForMap = new HashMap<String,String[]>();



    private String[] FoodSearches = {"Food","Ribs","barbeque","bbq","grill","ice cream","kettle corn"};
    private String[] engineeringSearches = {"Robots","engineering","mechanical","computer"};
    private String[] concertSearches = {"Rapper","Concert","cowcella","Cowchella"};

    public searcher()
    {
        toSearchForMap.put("FOOD",FoodSearches);
        toSearchForMap.put("ENGR",engineeringSearches);
        toSearchForMap.put("CONCERT",concertSearches);
    }

    public void readFileAndAdd(String filepath)
    {


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
        String[] toSearchFor = toSearchForMap.get(toSearchForKey);

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
