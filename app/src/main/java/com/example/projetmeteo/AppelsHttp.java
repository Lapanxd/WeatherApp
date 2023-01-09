package com.example.projetmeteo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AppelsHttp {
    private RequestQueue requestQueue;

    public AppelsHttp(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public void httpRequest(String city){
        String url = "https://www.prevision-meteo.ch/services/json/" + city;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject current_condition = jsonObject.getJSONObject("current_condition");
                                String condition = current_condition.getString("condition");
                                Log.i("APP", condition);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }
}
