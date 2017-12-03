package com.emrebicici.sakaryaotelleri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void giris(View view)
    {
        Intent intent = new Intent (getApplicationContext(), KullaniciGirisiActivity.class);
        startActivity(intent);
    }

    public void kayit(View view)
    {
        Intent intent = new Intent(getApplicationContext(), KayitActivity.class);
        startActivity(intent);
    }
}
