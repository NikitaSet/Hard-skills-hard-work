package com.example.yes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);
        ArrayList<String> itemList = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        List<String> result = CalendarActivity.result;

        listView.setAdapter(adapter);

        for (int i = 0; i < result.size(); i ++) {
            itemList.add(result.get(i));
            adapter.notifyDataSetChanged();
        }
    }

    public void back (View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}