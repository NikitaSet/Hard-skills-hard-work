package com.example.yes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UsefulLinks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_useful_links);
    }

    public void openModeus (View v) {
        String url = "https://urfu.modeus.org/schedule-calendar/my?timeZone=%22Asia%2FYekaterinburg%22&calendar=%7B%22view%22:%22agendaWeek%22,%22date%22:%222024-05-24T03:23:05%22%7D";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void openLk (View v) {
        String url = "https://istudent.urfu.ru/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void openExam1 (View v) {
        String url = "https://exam1.urfu.ru/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void openOpenEdu (View v) {
        String url = "https://sso.openedu.ru/realms/openedu/protocol/openid-connect/auth?client_id=plp&redirect_uri=https://openedu.ru/complete/npoedsso/&state=Rf0ja3rUd3LUHv7wdDIHZd3ihEhq9EnE&response_type=code&nonce=dNfLgZRBBR1LAJ7zzc2USeOYrEe1NMuP1KoFqxvh7SvjgNQhl6Lu9HHxZ7pSrnyg&scope=openid+profile+email#timeline=current";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void back (View v) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void links (View v) {
        Intent intent = new Intent(this, LinkActivity.class);
        startActivity(intent);
    }
}