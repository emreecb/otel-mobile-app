package com.emrebicici.sakaryaotelleri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView otelAdiDetay;
    TextView otelIcerikDetay;
    TextView otelTelefonDetay;
    TextView otelAdresDetay;
    TextView otelPuaniDetay;

    ImageView imagedetay1;
    ImageView imagedetay2;
    ImageView imagedetay3;
    ImageView imagedetay4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        otelAdiDetay = (TextView)findViewById(R.id.otelAdiDetayText);
        otelIcerikDetay = (TextView) findViewById(R.id.otelIcerikDetayText);
        otelTelefonDetay = (TextView) findViewById(R.id.otelTelefonDetayText);
        otelAdresDetay = (TextView) findViewById(R.id.otelAdresDetayText);
        otelPuaniDetay = (TextView) findViewById(R.id.otelPuaniDetayText);
        imagedetay1 = (ImageView) findViewById(R.id.imageDetay1);
        imagedetay2 = (ImageView) findViewById(R.id.imageDetay2);
        imagedetay3 = (ImageView) findViewById(R.id.imageDetay3);
        imagedetay4 = (ImageView) findViewById(R.id.imageDetay4);

        Intent intent = getIntent();
        String a = intent.getStringExtra("oteladi");
        otelAdiDetay.setText(a);
        String b = intent.getStringExtra("photo1");
        Picasso.with(this).load(b).into(imagedetay1);
        String c = intent.getStringExtra("photo2");
        Picasso.with(this).load(c).into(imagedetay2);
        String d = intent.getStringExtra("photo3");
        Picasso.with(this).load(d).into(imagedetay3);
        String e = intent.getStringExtra("photo4");
        Picasso.with(this).load(e).into(imagedetay4);
        String f = intent.getStringExtra("icerik");
        otelIcerikDetay.setText(f);
        String g = intent.getStringExtra("telefon");
        otelTelefonDetay.setText(g);
        String h = intent.getStringExtra("adres");
        otelAdresDetay.setText(h);
        String i = intent.getStringExtra("puan");
        otelPuaniDetay.setText(i+"/10" );


    }


}
