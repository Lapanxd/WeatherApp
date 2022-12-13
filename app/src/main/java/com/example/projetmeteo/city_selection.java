package com.example.projetmeteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class city_selection extends AppCompatActivity {

    DataBase database = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selection);

        EditText cityInput = findViewById(R.id.city_input);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(View -> {
            database.insertCity(cityInput.getText().toString());
            database.getCities();
        });
    }
}