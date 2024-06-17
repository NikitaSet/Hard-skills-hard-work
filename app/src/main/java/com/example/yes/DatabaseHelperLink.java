package com.example.yes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperLink extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LinksDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_LINK = "link";

    public DatabaseHelperLink(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_LINK + " TEXT" +
                ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    public void addEvent(String description, String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_LINK, link);

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
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                @SuppressLint("Range") String link = cursor.getString(cursor.getColumnIndex(COLUMN_LINK));

                Event event = new Event(id, description, link);
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventList;
    }

    public List<String> getAllMessage () {
        List<Event> eventList = getAllEvents();
        List<String> result = new ArrayList();
        result.add("Полезные ссылки");

        for (int i = 0; i < eventList.size(); i ++) {
           Event check = eventList.get(i);
            result.add(check.description);
        }
        return result;
    }

    public String getLink (String description) {
        List<Event> eventList = getAllEvents();
        String result = null;
        for (int i = 0; i < eventList.size(); i ++) {
            Event check = eventList.get(i);
            if (description.equals(check.description)) {
                result = check.link;
                break;
            }
        }
        return result;
    }

    public static class Event {
        private int id;
        private String description;
        private String link;

        public Event(int id, String description, String link) {
            this.id = id;
            this.description = description;
            this.link = link;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String time) {
            this.link = link;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "id=" + id +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
