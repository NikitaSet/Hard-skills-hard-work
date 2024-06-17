package com.example.yes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private CalendarActivity calendarActivity;
    private static final String DATABASE_NAME = "EventsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_TIME = "time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_YEAR + " INTEGER," +
                COLUMN_MONTH + " INTEGER," +
                COLUMN_DAY + " INTEGER," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_TIME + " TEXT" +
                ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    public void addEvent(int year, int month, int day, String description, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_MONTH, month);
        values.put(COLUMN_DAY, day);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_TIME, time);

        db.insert(TABLE_EVENTS, null, values);
        db.close();
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_EVENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") int year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR));
                @SuppressLint("Range") int month = cursor.getInt(cursor.getColumnIndex(COLUMN_MONTH));
                @SuppressLint("Range") int day = cursor.getInt(cursor.getColumnIndex(COLUMN_DAY));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));

                Event event = new Event(id, year, month, day, description, time);
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return eventList;
    }

    public List<String> getMessage(int year, int month, int day) {
        List<Event> eventList = getAllEvents();
        List<String> result = new ArrayList();
        result.add(day + "." + month + "." + year);
        for (int i = 0; i < eventList.size(); i ++) {
            Event check = eventList.get(i);
            if (check.year == year)
                if (check.month == month)
                    if (check.day == day) {
                        result.add(check.time + " " + ":" + " " + check.description);
                    }
        }
        return result;
    }

    public void checkMessage() {
        List<Event> eventList = getAllEvents();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String data = sdf.format(calendar.getTime());
        for (int i = 0; i < eventList.size(); i ++) {
            Event check = eventList.get(i);
            String dataCheck = (check.day + "-" + check.month + "-" + check.year);
            if (dataCheck.equals(data)) {
                startThread(check);
            }
        }
    }

    private void startThread(Event check) {
        new Thread(() -> {
            while (true) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String currentTime = sdf.format(calendar.getTime());
                    if (currentTime.equals(check.time)) {
                        calendarActivity.createNotification(check.description, check.time);
                    }
                }
        }).start();
    }

    public static class Event {
        private int id;
        private int year;
        private int month;
        private int day;
        private String description;
        private String time;

        public Event(int id, int year, int month, int day, String description, String time) {
            this.id = id;
            this.year = year;
            this.month = month;
            this.day = day;
            this.description = description;
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "id=" + id +
                    ", year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}