package com.example.projetmeteo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class weatherInfoFragment extends Fragment {

    public final static String c = "";
    public final static String temperature = "temp";
    public final static String condition = "cond";

    Context mContext;

    public weatherInfoFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://www.prevision-meteo.ch/services/json/" + getArguments().getString(c);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject current_condition = jsonObject.getJSONObject("current_condition");
                                String condition = current_condition.getString("condition");
                                String temp = String.valueOf(current_condition.getInt("tmp"));

                                ((TextView)view.findViewById(R.id.condition)).setText(condition);
                                ((TextView)view.findViewById(R.id.temperature)).setText(temp);

                                if(condition.equals("Ensoleillé") ||
                                        condition.equals("Eclaircies")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.day_clear);
                                }

                                if(condition.equals("Faiblement nuageux") ||
                                        condition.equals("Fortement nuageux") ||
                                        condition.equals("Faibles passages nuageux") ||
                                        condition.equals("Ciel voilé") ||
                                        condition.equals("Développement nuageux")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.day_cloud);
                                }

                                if(condition.equals("Nuit bien dégagée") ||
                                        condition.equals("Nuit claire et stratus") ||
                                        condition.equals("Nuit claire")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.night_clear);
                                }

                                if(condition.equals("Nuit légèrement voilée") ||
                                        condition.equals("Nuit nuageuse") ||
                                        condition.equals("Nuit avec dévelopement nuageux")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.night_cloud);
                                }

                                if(condition.equals("Averses de pluie faible") ||
                                        condition.equals("Nuit avec averses") ||
                                        condition.equals("Averses de pluie modéré") ||
                                        condition.equals("Averses de pluie forte") ||
                                        condition.equals("Pluie faible") ||
                                        condition.equals("Pluie forte") ||
                                        condition.equals("Pluie modérée") ||
                                        condition.equals("Couvert avec averses")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.rain);
                                }

                                if(condition.equals("Pluie et neige mêlée forte") ||
                                        condition.equals("Pluie et neige mêlée modérée") ||
                                        condition.equals("Pluie et neige mêlée faible") ||
                                        condition.equals("Neige forte") ||
                                        condition.equals("Neige modérée") ||
                                        condition.equals("Neige faible") ||
                                        condition.equals("Nuit avec averses de neige faible") ||
                                        condition.equals("Averses de neige faible")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.snow);
                                }

                                if(condition.equals("Fortement orageux") ||
                                        condition.equals("Orage modéré") ||
                                        condition.equals("Nuit faiblement orageuse") ||
                                        condition.equals("Faiblement orageux")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.storm);
                                }

                                if(condition.equals("Brouillard") ||
                                        condition.equals("Stratus") ||
                                        condition.equals("Stratus se dissipant")) {
                                    ((ImageView)getActivity().findViewById(R.id.imageView)).setImageResource(R.drawable.fog);
                                }

                                JSONObject day0 = jsonObject.getJSONObject("fcst_day_0");
                                JSONObject day0hourly_data = day0.getJSONObject("hourly_data");
                                JSONObject day0h13 = day0hourly_data.getJSONObject("13H00");
                                String day0Name = day0.getString("day_short");
                                String day0tmin = String.valueOf(day0.getInt("tmin"));
                                String day0tmax = String.valueOf(day0.getInt("tmax"));
                                String day0condition = day0h13.getString("CONDITION");
                                String day0image = day0h13.getString("ICON");
                                ((TextView)view.findViewById(R.id.jour1)).setText(day0Name);
                                ((TextView)view.findViewById(R.id.temp_jour1)).setText(day0tmin + "° / " + day0tmax + "°");
                                Picasso.get().load(day0image).into(((ImageView)view.findViewById(R.id.day1image)));

                                JSONObject day1 = jsonObject.getJSONObject("fcst_day_1");
                                JSONObject day1hourly_data = day1.getJSONObject("hourly_data");
                                JSONObject day1h13 = day1hourly_data.getJSONObject("13H00");
                                String day1Name = day1.getString("day_short");
                                String day1tmin = String.valueOf(day1.getInt("tmin"));
                                String day1tmax = String.valueOf(day1.getInt("tmax"));
                                String day1condition = day1h13.getString("CONDITION");
                                String day1image = day1h13.getString("ICON");
                                ((TextView)view.findViewById(R.id.jour2)).setText(day1Name);
                                ((TextView)view.findViewById(R.id.temp_jour2)).setText(day1tmin + "° / " + day1tmax + "°");
                                Picasso.get().load(day1image).into(((ImageView)view.findViewById(R.id.day2image)));

                                JSONObject day2 = jsonObject.getJSONObject("fcst_day_2");
                                JSONObject day2hourly_data = day2.getJSONObject("hourly_data");
                                JSONObject day2h13 = day2hourly_data.getJSONObject("13H00");
                                String day2Name = day2.getString("day_short");
                                String day2tmin = String.valueOf(day2.getInt("tmin"));
                                String day2tmax = String.valueOf(day2.getInt("tmax"));
                                String day2condition = day2h13.getString("CONDITION");
                                String day2image = day2h13.getString("ICON");
                                ((TextView)view.findViewById(R.id.jour3)).setText(day2Name);
                                ((TextView)view.findViewById(R.id.temp_jour3)).setText(day2tmin + "° / " + day2tmax + "°");
                                Picasso.get().load(day2image).into(((ImageView)view.findViewById(R.id.day3image)));

                                JSONObject day3 = jsonObject.getJSONObject("fcst_day_3");
                                JSONObject day3hourly_data = day3.getJSONObject("hourly_data");
                                JSONObject day3h13 = day3hourly_data.getJSONObject("13H00");
                                String day3Name = day3.getString("day_short");
                                String day3tmin = String.valueOf(day3.getInt("tmin"));
                                String day3tmax = String.valueOf(day3.getInt("tmax"));
                                String day3condition = day3h13.getString("CONDITION");
                                String day3image = day3h13.getString("ICON");
                                ((TextView)view.findViewById(R.id.jour4)).setText(day3Name);
                                ((TextView)view.findViewById(R.id.temp_jour4)).setText(day3tmin + "° / " + day3tmax + "°");
                                Picasso.get().load(day3image).into(((ImageView)view.findViewById(R.id.day4image)));

                                JSONObject day4 = jsonObject.getJSONObject("fcst_day_4");
                                String day4Name = day4.getString("day_short");
                                JSONObject day4h13 = day3hourly_data.getJSONObject("13H00");
                                String day4tmin = String.valueOf(day4.getInt("tmin"));
                                String day4tmax = String.valueOf(day4.getInt("tmax"));
                                String day4condition = day4h13.getString("CONDITION");
                                String day4image = day4h13.getString("ICON");
                                ((TextView)view.findViewById(R.id.jour5)).setText(day4Name);
                                ((TextView)view.findViewById(R.id.temp_jour5)).setText(day4tmin + "° / " + day4tmax + "°");
                                Picasso.get().load(day4image).into(((ImageView)view.findViewById(R.id.day5image)));

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
        queue.add(stringRequest);
    }
}