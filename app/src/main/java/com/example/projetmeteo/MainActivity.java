package com.example.projetmeteo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Database database = new Database(this);
    AppelsHttp api = new AppelsHttp(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selection_ville = findViewById(R.id.selection_ville);
        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        selection_ville.setOnClickListener(View->{
            Intent myIntent = new Intent(this, CitySelection.class);
            startActivity(myIntent);
        });
    }
}