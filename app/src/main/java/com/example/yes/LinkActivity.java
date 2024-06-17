package com.example.yes;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class LinkActivity extends AppCompatActivity {

    public static List<String> result;

    private DatabaseHelperLink databaseHelperLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_link);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseHelperLink = new DatabaseHelperLink(this);
    }

    public void addLinkDescription (View v) {
        EditText editText = new EditText(this);
        editText.setHint("Название");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Введите название");
        builder.setView(editText);

        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String linkDescription = editText.getText().toString();
            addLinkLink(linkDescription);
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void addLinkLink (String description) {
        EditText editText = new EditText(this);
        editText.setHint("Ссылка");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Введите ссылку");
        builder.setView(editText);

        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String link= editText.getText().toString();
            databaseHelperLink.addEvent(description,  link);
            Toast.makeText(LinkActivity.this, "Ссылка добавлена", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public void see (View v) {
        result = databaseHelperLink.getAllMessage();
        Intent intent = new Intent(this, SeeActivity.class);
        startActivity(intent);
    }

    public void inBack (View v) {
        Intent intent = new Intent(this, UsefulLinks.class);
        startActivity(intent);
    }
}