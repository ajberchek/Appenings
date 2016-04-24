package com.example.aberchek.appenings;

import android.content.Context;
import android.net.Uri;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<happening> global_data =  new ArrayList<happening>();
    private ArrayList<happening> filtered_data = new ArrayList<happening>();
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> dateTime = new ArrayList<>();
    private ArrayList<String> urlLink = new ArrayList<String>();
    private ArrayList<String> cost = new ArrayList<String>();
    private ArrayList<String > sideBar = new ArrayList<String>();
    private searcher search = new searcher();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataPuller dp = new DataPuller();
        dp.execute();

        ((Button)findViewById(R.id.foodBtn)).setOnClickListener(this);
        ((Button)findViewById(R.id.engrBtn)).setOnClickListener(this);
        ((Button)findViewById(R.id.sportBtn)).setOnClickListener(this);





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

            searcher searcher = new searcher();

            ArrayList<happening> hasFood = searcher.getValidSearch("FOOD",listOfHappenings);
            ArrayList<happening> hasENGR = searcher.getValidSearch("ENGR", listOfHappenings);
            ArrayList<happening> hasConcert = searcher.getValidSearch("CONCERT", listOfHappenings);

            /*
            HashMap<String, searchKeyWord> toSearch = searcher.getToSearchForMap();
            toSearch.get("FOOD").setSelected(true);
            toSearch.get("ENGR").setSelected(true);
            toSearch.get("CONCERT").setSelected(true);

            searcher.setToSearchForMap(toSearch);

            ArrayList<happening> allHapppeningToSearch = searcher.searchAllSelected(listOfHappenings);

            boolean test = true;

            allHapppeningToSearch = searcher.searchAllSelected(listOfHappenings);
            */
        } catch (JSONException e) {
            e.printStackTrace();
        }
        filtered_data = global_data;
        ListView lv = (ListView) findViewById(R.id.listView);
        Button fd = (Button) findViewById(R.id.foodBtn);




        filler();



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    goToURI(urlLink.get(position));

            }
        });


    }

    public void onClick(View v)
    {
        if(v == (Button)findViewById(R.id.foodBtn))
        {
            HashMap<String,searchKeyWord> hm = search.getToSearchForMap();
            hm.get("FOOD").toggleSelected();
            search.setToSearchForMap(hm);
            filtered_data = search.searchAllSelected(global_data);
            titles.clear();
            dateTime.clear();
            urlLink.clear();
            cost.clear();
            filler();
        }
        else if(v == (Button)findViewById(R.id.engrBtn))
        {
            HashMap<String,searchKeyWord> hm = search.getToSearchForMap();
            hm.get("ENGR").toggleSelected();
            search.setToSearchForMap(hm);
            filtered_data = search.searchAllSelected(global_data);
            titles.clear();
            dateTime.clear();
            urlLink.clear();
            cost.clear();
            filler();
        }
        else if(v == (Button)findViewById(R.id.sportBtn))
        {
            HashMap<String,searchKeyWord> hm = search.getToSearchForMap();
            hm.get("SPORT").toggleSelected();
            search.setToSearchForMap(hm);
            filtered_data = search.searchAllSelected(global_data);
            titles.clear();
            dateTime.clear();
            urlLink.clear();
            cost.clear();
            filler();
        }
        else if(v == (Button)findViewById(R.id.allBtn))
        {
            HashMap<String,searchKeyWord> hm = search.getToSearchForMap();
            hm.get("ALL").toggleSelected();
            search.setToSearchForMap(hm);
            filtered_data = search.searchAllSelected(global_data);
            titles.clear();
            dateTime.clear();
            urlLink.clear();
            cost.clear();
            filler();
        }

    }

    public void filler(){
        ListView lv = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < filtered_data.size(); i++) {
            titles.add(filtered_data.get(i).getTitle());
            dateTime.add(filtered_data.get(i).getTimeDate());
            urlLink.add(filtered_data.get(i).getLink());
            cost.add(filtered_data.get(i).getCost());
        }

        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, titles));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, dateTime));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, urlLink));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, cost));

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
            ViewHolder mainViewholder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.titleText);
                viewHolder.dateTime = (TextView)convertView.findViewById(R.id.dateTimeText);
                viewHolder.cost = (TextView)convertView.findViewById(R.id.costText);

                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.title.setText(titles.get(position) + "\n");
            mainViewholder.dateTime.setText(dateTime.get(position));
            mainViewholder.cost.setText(cost.get(position));




            return convertView;
        }




        public class ViewHolder {
            TextView title;
            TextView dateTime;
            TextView cost;
            ListView filter;
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

    public void goToURI(String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
        try {
            startActivity(browserIntent);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



}
