package com.example.aberchek.appenings;

import android.content.Context;
import android.net.Uri;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<happening> global_data =  new ArrayList<happening>();
    private ArrayList<happening> filtered_data = new ArrayList<happening>();
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> dateTime = new ArrayList<>();
    private ArrayList<String> urlLink = new ArrayList<String>();
    private ArrayList<String> cost = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<String > sideBar = new ArrayList<String>();
    private searcher searcher = new searcher();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataPuller dp = new DataPuller();
        dp.execute();


        JSONObject jArr = dp.getJsonArr();
        while((jArr = dp.getJsonArr()) == null)
        {

        }

        Log.d("Server response",Integer.toString(jArr.length()));
        try {
            JSONObject eventList = jArr.getJSONObject("bwEventList");
            Log.d("Event",eventList.toString());

            JSONArray eventArr = eventList.getJSONArray("events");
            Log.d("EventList",eventArr.toString());

            happeningBuilder happBuild = new happeningBuilder(eventArr);

            ArrayList<happening> listOfHappenings = happBuild.buildHappeningArr();
            global_data = happBuild.buildHappeningArr();



            ArrayList<happening> hasFood = searcher.getValidSearch("FOOD",listOfHappenings);
            ArrayList<happening> hasENGR = searcher.getValidSearch("ENGR", listOfHappenings);
            ArrayList<happening> hasConcert = searcher.getValidSearch("CONCERT", listOfHappenings);


            HashMap<String, searchKeyWord> toSearch = searcher.getToSearchForMap();
            toSearch.get("FOOD").setSelected(true);
            toSearch.get("ENGR").setSelected(true);
            toSearch.get("CONCERT").setSelected(true);

            searcher.setToSearchForMap(toSearch);

            ArrayList<happening> allHapppeningToSearch = searcher.searchAllSelected(listOfHappenings);

            boolean test = true;

            allHapppeningToSearch = searcher.searchAllSelected(listOfHappenings);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        filtered_data = global_data;

        ListView lv = (ListView) findViewById(R.id.listView);
        ListView sideBar_layout = (ListView) findViewById(R.id.left_drawer);



        for (int i = 0; i < filtered_data.size(); i++) {
            titles.add("Title: " + filtered_data.get(i).getTitle());
            dateTime.add("When: " + filtered_data.get(i).getTimeDate());
            urlLink.add(filtered_data.get(i).getLink());

            String costToAdd = filtered_data.get(i).getCost();
            if(costToAdd.equals(""))
            {
                costToAdd = "0";
            }

            cost.add("Cost: " + costToAdd);
            description.add("Description: " + filtered_data.get(i).getDescription());

        }
        keyUpdate();


        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, titles));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, dateTime));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, urlLink));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, cost));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, description));
        lv.setAdapter(new MyListAdapter(this, R.layout.activity_main,sideBar));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //if()
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToURI(urlLink.get(position),description.get(position));
            }
        });


    }
    public void keyUpdate(){

        HashMap<String, searchKeyWord> searchVals = searcher.getToSearchForMap();
        for(String s : searchVals.keySet())
        {
            String key = searchVals.get(s).getKey();

            //Assume someone checks the box
            searchVals.get(s).setSelected(true);
            searchVals.put(s,searchVals.get(s));
            searcher.setToSearchForMap(searchVals);
            sideBar.add(key);
            filtered_data = searcher.searchAllSelected(global_data);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private class MyListAdapter extends ArrayAdapter<String>{

        private int layout;
        private ArrayList<String> mObjects;


        private MyListAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null ;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.titleText);
                viewHolder.dateTime = (TextView)convertView.findViewById(R.id.dateTimeText);
                viewHolder.cost = (TextView)convertView.findViewById(R.id.costText);
                viewHolder.description = (TextView)convertView.findViewById(R.id.descriptionText);

                //viewHolder.sideBar = (TextView)convertView.findViewById(R.id.);


                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.title.setText(titles.get(position));
            mainViewholder.dateTime.setText(dateTime.get(position));
            mainViewholder.cost.setText(cost.get(position));
            //mainViewholder.description.setText(description.get(position));




            return convertView;
        }




        public class ViewHolder {
            TextView title;
            TextView dateTime;
            TextView cost;
            TextView description;
            ListView filter;
            TextView sideBar;

        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Start url stuff

    public void goToURI(String link, String description){
        Log.i("Link",link);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
        try {
            startActivity(browserIntent);
        }
        catch(Exception e)
        {
            //Make a toast of the description
            e.printStackTrace();
        }
    }



}
