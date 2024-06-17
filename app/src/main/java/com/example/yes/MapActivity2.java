package com.example.yes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class MapActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.imageView);
        String textButton = com.example.yes.MapActivity.userString;
        String building = textButton.split("-")[0];
        String floor = textButton.split("-")[1];
        if (building.equals("Р")){
            if(floor.startsWith("0"))
                imageView.setImage(ImageSource.resource(R.drawable.rawdick0));
            if(floor.startsWith("1"))
                imageView.setImage(ImageSource.resource(R.drawable.rawdick1));
            if(floor.startsWith("2"))
                imageView.setImage(ImageSource.resource(R.drawable.rawdick2));
            if(floor.startsWith("3"))
                imageView.setImage(ImageSource.resource(R.drawable.rawdick3));
            if(floor.startsWith("4"))
                imageView.setImage(ImageSource.resource(R.drawable.rawdick4));
        }


        else if (building.equals("Э") | building.equals("М") |
                building.equals("ГУК") | building.equals("И")){
            if(floor.startsWith("1"))
                imageView.setImage(ImageSource.resource(R.drawable.guk1));
            if(floor.startsWith("2"))
                imageView.setImage(ImageSource.resource(R.drawable.guk2));
            if(floor.startsWith("3"))
                imageView.setImage(ImageSource.resource(R.drawable.guk3));
            if(floor.startsWith("4"))
                imageView.setImage(ImageSource.resource(R.drawable.guk4));
            if(floor.startsWith("5"))
                imageView.setImage(ImageSource.resource(R.drawable.guk5));
        }


        else if (building.equals("Т")) {
            imageView.setImage(ImageSource.resource(R.drawable.teplick1));
        }


        else if (building.equals("C") | building.equals("СП")) {
            if(floor.startsWith("1"))
                imageView.setImage(ImageSource.resource(R.drawable.ssp1));
            if(floor.startsWith("2"))
                imageView.setImage(ImageSource.resource(R.drawable.ssp2));
            if(floor.startsWith("3"))
                imageView.setImage(ImageSource.resource(R.drawable.ssp3));
            if(floor.startsWith("4"))
                imageView.setImage(ImageSource.resource(R.drawable.ssp4));
        }

        else {
            imageView.setImage(ImageSource.resource(R.drawable.error_image));
        }
    }

    public void goBack(View v) {
        Intent intent = new Intent(this, com.example.yes.MapActivity.class);
        startActivity(intent);
    }
}