package com.example.projetmeteo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CitySelection extends AppCompatActivity {

    Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selection);

        EditText cityInput = findViewById(R.id.cityInput);
        Button backButton = findViewById(R.id.backButton);

        readCities();

        backButton.setOnClickListener(View->{
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        });

        cityInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Log.i("APP", cityInput.getText().toString());
                    database.insertCity(cityInput.getText().toString());
                    readCities();
                    cityInput.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    public class TextButton {
        public String getText() {
            return texte;
        }

        public void setText(String texte) {
            this.texte = texte;
        }

        public boolean isClicked() {
            return isClicked();
        }

        private String texte;
        private boolean isClicked;
    }

    public class CustomAdapter extends ArrayAdapter<TextButton> {

        public CustomAdapter(@NonNull Context context, ArrayList<TextButton> resource) {
            super(context, 0, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextButton textButton = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_item, parent, false);
            }
            TextView cityName = convertView.findViewById(R.id.cityName);
            Button remove = convertView.findViewById(R.id.remove);

            cityName.setText(textButton.getText());
            remove.setOnClickListener(View->{
                database.removeCity(textButton.getText());
                readCities();
            });

            return convertView;
        }
    }

    public void readCities(){
        ListView cities = findViewById(R.id.cities);
        ArrayList<TextButton> tableau = new ArrayList();
        for (int i=0; i<database.readData(); i++) {
            TextButton textButton = new TextButton();
            textButton.setText(database.getCities()[i]);
            tableau.add(textButton);
        }
        CustomAdapter adapter = new CustomAdapter(this, tableau);
        cities.setAdapter(adapter);
    }
}