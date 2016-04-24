package com.example.aberchek.appenings;

import android.app.DownloadManager;
import android.net.Network;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by aberchek on 4/23/16.
 */
public class DataPuller extends AsyncTask<String, Void, Boolean>
{
    private String happeningsLink;

    private JSONObject jsonData;

    public DataPuller()
    {

    }

    @Override
    protected Boolean doInBackground(String... params) {
        return tryGrabAgain();

    }

    public JSONObject getJsonArr()
    {
        return jsonData;
    }


    public boolean tryGrabAgain()
    {

            try
            {
                //Change the below url if the site changes
                String dataUrl = "http://events.ucmerced.edu:7070/feeder/main/eventsFeed.do?f=y&sort=dtstart.utc:asc&fexpr=(categories.href!=%22/public/.bedework/categories/sys/Ongoing%22)%20and%20(entity_type=%22event%22%7Centity_type=%22todo%22)&skinName=list-json&count=100&start=20160201&end=20160501";

                URL url = new URL(dataUrl);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String str;
                StringBuffer response = new StringBuffer();



                while((str=in.readLine()) != null){

                    response.append(str);
                    response.append('\n');
                }


                String responseStr = response.toString();
                jsonData = new JSONObject(responseStr);
                Log.d("Server response",responseStr);

                in.close();
                return true;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } //catch (JSONException e) {
            catch (JSONException e) {
                e.printStackTrace();
            }

        return false;
    }




}
