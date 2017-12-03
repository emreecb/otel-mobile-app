package com.emrebicici.sakaryaotelleri;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    ArrayList<String> oteladiFromFB;
    ArrayList<String> otelicerikFromFB;
    ArrayList<String> oteltelefonFromFB;
    ArrayList<String> oteladresFromFB;
    ArrayList<String> otelpuaniFromFB;
    ArrayList<String> imageFromFB;
    ArrayList<String> image2FromFB;
    ArrayList<String> image3FromFB;
    ArrayList<String> image4FromFB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    OtelClass adapter;
    ListView listView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Intent intent = getIntent();
        String kullaniciAdi = intent.getStringExtra("emailAktar");

        if (kullaniciAdi.equals("admin@gmail.com"))
        {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.admin, menu);
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.admin){
            Intent intent2 = new Intent(getApplicationContext(), EklemeActivity.class);
            intent2.putExtra("emailAktar", "admin@gmail.com");
            startActivity(intent2);
        }
        if(item.getItemId()==R.id.ref){
            recreateActivityCompat(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public static final void recreateActivityCompat(final Activity a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            a.recreate();
        } else {
            final Intent intent = a.getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            a.finish();
            a.overridePendingTransition(0, 0);
            a.startActivity(intent);
            a.overridePendingTransition(0, 0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        oteladiFromFB = new ArrayList<String>();
        imageFromFB = new ArrayList<String>();
        image2FromFB = new ArrayList<String>();
        image3FromFB = new ArrayList<String>();
        image4FromFB = new ArrayList<String>();
        otelicerikFromFB = new ArrayList<String>();
        oteltelefonFromFB = new ArrayList<String>();
        oteladresFromFB = new ArrayList<String>();
        otelpuaniFromFB = new ArrayList<String>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        adapter = new OtelClass(oteladiFromFB,imageFromFB, otelpuaniFromFB, this);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        getDataFromDatabase();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent31 = new Intent(getApplicationContext(),DetailActivity.class);
                intent31.putExtra("oteladi", oteladiFromFB.get(position));
                intent31.putExtra("photo1", imageFromFB.get(position));
                intent31.putExtra("icerik", otelicerikFromFB.get(position));
                intent31.putExtra("telefon", oteltelefonFromFB.get(position));
                intent31.putExtra("adres", oteladresFromFB.get(position));
                intent31.putExtra("photo2", image2FromFB.get(position));
                intent31.putExtra("photo3", image3FromFB.get(position));
                intent31.putExtra("photo4", image4FromFB.get(position));
                intent31.putExtra("puan", otelpuaniFromFB.get(position));
                startActivity(intent31);
            }
        });

    }
    protected void getDataFromDatabase(){
        DatabaseReference newReference = firebaseDatabase.getReference("Oteller");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    HashMap<String, String> hashMap = (HashMap) ds.getValue();
                    oteladiFromFB.add(hashMap.get("oteladi"));
                    otelicerikFromFB.add(hashMap.get("icerik"));
                    oteltelefonFromFB.add(hashMap.get("telefon"));
                    oteladresFromFB.add(hashMap.get("adres"));
                    otelpuaniFromFB.add(hashMap.get("puan"));
                    imageFromFB.add(hashMap.get("downloadurl1"));
                    image2FromFB.add(hashMap.get("downloadurl2"));
                    image3FromFB.add(hashMap.get("downloadurl3"));
                    image4FromFB.add(hashMap.get("downloadurl4"));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
