package com.example.projetmeteo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Adapter extends FragmentStateAdapter {

    Context mContext;

    public Adapter(Context context, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Database database = new Database(mContext);
        Fragment fragment = new weatherInfoFragment(mContext);
        Bundle args = new Bundle();

        String[] cities = database.getCities();

        args.putString(weatherInfoFragment.c, cities[(position)]);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public int getItemCount() {
        return 4;
    }
}
