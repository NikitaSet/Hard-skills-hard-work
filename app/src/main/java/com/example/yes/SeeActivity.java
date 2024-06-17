package com.example.yes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SeeActivity extends AppCompatActivity {

    private DatabaseHelperLink databaseHelperLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see2);
        databaseHelperLink = new DatabaseHelperLink(this);
        ArrayList<String> itemList = new ArrayList<>();
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        List<String> result = LinkActivity.result;

        listView.setAdapter(adapter);

        for (int i = 0; i < result.size(); i ++) {
            itemList.add(result.get(i));
            adapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                String link = databaseHelperLink.getLink(String.valueOf(((TextView) itemClicked).getText()));
                String url = link;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    public void back (View v) {
        Intent intent = new Intent(this, LinkActivity.class);
        startActivity(intent);
    }
}
