package com.example.yes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private DatabaseHelper databaseHelper;
    public static List<String> result;
    public String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        databaseHelper = new DatabaseHelper(this);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            showAddEventDialogStart(year, month, dayOfMonth);
        });
        databaseHelper.checkMessage();
    }

    private void showAddEventDialogStart(int year, int month, int dayOfMonth) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        data = dayOfMonth + "." + month + "." + year;
        builder.setTitle(data);


        builder.setPositiveButton("Добавить событие", (dialog, which) -> {
            showAddEventDialog(year, month, dayOfMonth);
        });

        builder.setNegativeButton("Просмотреть событие", (dialog, which) -> {
            result = databaseHelper.getMessage(year, month, dayOfMonth);
            Intent intent = new Intent(this, NotificationView.class);
            startActivity(intent);
        });
        builder.show();
    }

    private void showAddEventDialog(int year, int month, int dayOfMonth) {

        EditText editText = new EditText(this);
        editText.setHint("Напоминание");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Добавить событие");
        builder.setView(editText);

        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String eventDescription = editText.getText().toString();
            showAddEventDialogTime(year, month, dayOfMonth, eventDescription);
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showAddEventDialogTime(int year, int month, int dayOfMonth, String eventDescription) {

        EditText editText = new EditText(this);
        editText.setHint("Введите время в формате 00:00");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Добавить событие");
        builder.setView(editText);

        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String eventTime = editText.getText().toString();
            databaseHelper.addEvent(year, month, dayOfMonth, eventDescription, eventTime);
            Toast.makeText(CalendarActivity.this, "Событие добавлено", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @SuppressLint({"NewApi", "NotificationPermission"})
    public void createNotification (String text, String time){
        NotificationChannel channel = new NotificationChannel(
                "TEST_CHANNEL",
                "TEST DESCRIPTION",
                NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, "TEST_CHANNEL")
                .setContentTitle("Напоминание")
                .setContentText(time + " " + ":" + " " + text)
                .setSmallIcon(R.drawable.go)
                .build();
        notificationManager.notify(42, notification);
    }


    public void inBack (View v) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}