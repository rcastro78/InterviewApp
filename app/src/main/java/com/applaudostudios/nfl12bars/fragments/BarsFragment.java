package com.applaudostudios.nfl12bars.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.applaudostudios.nfl12bars.AppController;
import com.applaudostudios.nfl12bars.R;
import com.applaudostudios.nfl12bars.adapter.BarAdapter;
import com.applaudostudios.nfl12bars.models.BarVenue;
import com.applaudostudios.nfl12bars.utils.DateUtils;
import com.applaudostudios.nfl12bars.utils.JSONParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by RafaelCastro on 28/5/15.
 */
public class BarsFragment extends Fragment{


    String TAG="BarsFragment";
    String URL="http://s3.amazonaws.com/jon-hancock-phunware/nflapi-static.json";
    final static String ID="id";
    final static String NAME="name";
    final static String ADDRESS="address";
    final static String CITY="city";
    final static String STATE = "state";
    final static String ZIP="zip";
    final static String SCHEDULE="schedule";
    final static String START_DATE="start_date";
    final static String END_DATE="end_date";
    final static String IMAGE_URL="image_url";
    ProgressDialog progressDialog;
    BarAdapter adapter;
    ArrayList<BarVenue> items = new ArrayList<>();
    ListView mLvBars;
    DateUtils dateUtils = new DateUtils();
    private OnListItemSelectedListener listener;

    public interface OnListItemSelectedListener {
        void onItemSelected(BarVenue item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnListItemSelectedListener) {
            listener = (OnListItemSelectedListener) activity;
        } else {
            throw new ClassCastException(
                    activity.toString()
                            + " must implement ItemsListFragment.OnListItemSelectedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bars,
                container, false);
        mLvBars = (ListView)view.findViewById(R.id.lvBars);

        mLvBars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = mLvBars.getItemAtPosition(position);
                BarVenue bar = (BarVenue) object;
                listener.onItemSelected(bar);
            }
        });
        progressDialog = new ProgressDialog(getActivity());

        getResults();

        return view;
    }

    public void getResults() {
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = (JSONObject) response
                                        .get(i);
                                int id = jsonObject.getInt(ID);
                                String name = jsonObject.getString(NAME);
                                String address =jsonObject.getString(ADDRESS)+" "+
                                        jsonObject.getString(CITY)+", "+jsonObject.getString(STATE)+
                                        " "+jsonObject.getString(ZIP);

                                String schedules="";
                                try {
                                    String schedule = jsonObject.getString(SCHEDULE);
                                    JSONArray mJsonArraySchedule = new JSONArray(schedule);

                                    for (int j = 0; j < mJsonArraySchedule.length(); j++) {
                                        JSONObject jsonSchedule = mJsonArraySchedule.getJSONObject(j);
                                        schedules += dateUtils.setStartTime(jsonSchedule.getString(START_DATE)) +" a "+
                                                dateUtils.setEndTime(jsonSchedule.getString(END_DATE))+"\n";

                                    }
                                }catch (Exception e)
                                {
                                    schedules = "";
                                    Log.d(TAG,e.toString());
                                }

                                String imageUrl = jsonObject.getString(IMAGE_URL);
                                items.add(new BarVenue(id,name,address,"","","",imageUrl,schedules));
                            }

                            adapter = new BarAdapter(getActivity(), items);
                            mLvBars.setAdapter(adapter);

                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }


    /*

    public void getResults2()
    {
        progressDialog.setMessage("Loading bars...");
        progressDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        Gson gson = new Gson();
                        Type collectionType = new TypeToken<List<BarVenue>>() {
                        }.getType();

                        ArrayList<BarVenue> bars = gson.fromJson(response.toString(),collectionType);
                        for (int j=0; j<bars.size(); j++)
                        {


                            int id = bars.get(j).getId();
                            String name = bars.get(j).getName();
                            String address =bars.get(j).getAddress()+" "+
                                    bars.get(j).getCity()+", "+bars.get(j).getState() +
                                    " "+bars.get(j).getZip();
                                String schedule = gson.fromJson(bars.get(j).getSchedules(),collectionType);
                                String imageUrl = bars.get(j).getImageUrl();
                            items.add(new BarVenue(id,name,address,"","","", imageUrl,schedule));
                        }
                        try {


                            adapter = new BarAdapter(getActivity(), items);
                            mLvBars.setAdapter(adapter);

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        progressDialog.dismiss();
        AppController.getInstance().addToRequestQueue(req);
    }

*/






}
