package com.example.projetmeteo;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetmeteo.ui.main.SectionsPagerAdapter;
import com.example.projetmeteo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button3);

        button.setOnClickListener(View -> {
            //textView.setText(dataBase.getCities()[0] + dataBase.getCities()[1] + dataBase.getCities()[2] + dataBase.getCities()[3] + dataBase.getCities()[4]);
            openCitySelectionActivity();
        });
    }

    public void openCitySelectionActivity(){
        Intent myIntent = new Intent(this, city_selection.class);
        startActivity(myIntent);
    }
}