package com.example.yes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void inLinks (View v) {
        Intent intent = new Intent(this, UsefulLinks.class);
        startActivity(intent);
    }

    public void inCalendar (View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void inMap (View v) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}