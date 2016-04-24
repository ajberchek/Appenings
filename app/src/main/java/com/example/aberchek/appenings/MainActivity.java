package com.example.aberchek.appenings;

import android.content.Context;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<happening> data =  new ArrayList<happening>();
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> dateTime = new ArrayList<>();


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
            data = happBuild.buildHappeningArr();

            searcher searcher = new searcher();

            ArrayList<happening> hasFood = searcher.getValidSearch("FOOD",listOfHappenings);

            boolean test = true;
            

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListView lv = (ListView) findViewById(R.id.listView);



        for (int i = 0; i < data.size(); i++) {
            titles.add(data.get(i).getTitle());
            dateTime.add(data.get(i).getTimeDate());
        }
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, titles));
        lv.setAdapter(new MyListAdapter(this, R.layout.list_item, dateTime));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "List item was clicked at " + position, Toast.LENGTH_SHORT).show();
            }
        });



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
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);

                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                viewHolder.dateTime = (TextView)convertView.findViewById(R.id.dateTimeText);

                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                }
            });

            mainViewholder.title.setText(titles.get(position));
            mainViewholder.dateTime.setText(dateTime.get(position));
            //mainViewholder.title.setText(getItem(position));



            return convertView;
        }


        public class ViewHolder {
            TextView title;
            Button button;
            TextView dateTime;
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




}
