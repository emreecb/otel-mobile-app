package com.emrebicici.sakaryaotelleri;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class EklemeActivity extends AppCompatActivity {

    EditText oteladiText;
    EditText icerikText;
    EditText telefonText;
    EditText adresText;
    Spinner spinner;

    private String array_spinner[];

    ImageView foto1View;
    ImageView foto2View;
    ImageView foto3View;
    ImageView foto4View;
    Uri selected;
    Uri selected2;
    Uri selected3;
    Uri selected4;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private StorageReference mStorageRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekleme);

        oteladiText = (EditText)findViewById(R.id.oteladiText);
        icerikText = (EditText)findViewById(R.id.icerikText);
        telefonText = (EditText)findViewById(R.id.telefonText);
        adresText = (EditText)findViewById(R.id.adresText);
        foto1View = (ImageView)findViewById(R.id.foto1View);
        foto2View = (ImageView)findViewById(R.id.foto2View);
        foto3View = (ImageView)findViewById(R.id.foto3View);
        foto4View = (ImageView)findViewById(R.id.foto4View);
        spinner = (Spinner)findViewById(R.id.spinner1);

        array_spinner = new String[10];
        array_spinner[0] = "1";
        array_spinner[1] = "2";
        array_spinner[2] = "3";
        array_spinner[3] = "4";
        array_spinner[4] = "5";
        array_spinner[5] = "6";
        array_spinner[6] = "7";
        array_spinner[7] = "8";
        array_spinner[8] = "9";
        array_spinner[9] = "10";

        ArrayAdapter secenek = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_spinner);
        spinner.setAdapter(secenek);


        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void ekle (View view){
        UUID uuidImage1 = UUID.randomUUID();
        UUID uuidImage2 = UUID.randomUUID();
        UUID uuidImage3 = UUID.randomUUID();
        UUID uuidImage4 = UUID.randomUUID();

        String imageName1 = "images/"+uuidImage1+".jpg";
        String imageName2 = "images/"+uuidImage2+".jpg";
        String imageName3 = "images/"+uuidImage3+".jpg";
        String imageName4 = "images/"+uuidImage4+".jpg";


        StorageReference storageReference1 = mStorageRef.child(imageName1);
        storageReference1.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL1 = taskSnapshot.getDownloadUrl().toString();

                String otelAdi = oteladiText.getText().toString();
                String icerik = icerikText.getText().toString();
                String telefon = telefonText.getText().toString();
                String adres = adresText.getText().toString();
                String otelPuani = spinner.getSelectedItem().toString();

                myRef.child("Oteller").child(otelAdi).child("oteladi").setValue(otelAdi);
                myRef.child("Oteller").child(otelAdi).child("icerik").setValue(icerik);
                myRef.child("Oteller").child(otelAdi).child("telefon").setValue(telefon);
                myRef.child("Oteller").child(otelAdi).child("adres").setValue(adres);
                myRef.child("Oteller").child(otelAdi).child("puan").setValue(otelPuani);
                myRef.child("Oteller").child(otelAdi).child("downloadurl1").setValue(downloadURL1);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
        StorageReference storageReference2 = mStorageRef.child(imageName2);
        storageReference2.putFile(selected2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL2 = taskSnapshot.getDownloadUrl().toString();

                String otelAdi = oteladiText.getText().toString();
                String icerik = icerikText.getText().toString();

                myRef.child("Oteller").child(otelAdi).child("downloadurl2").setValue(downloadURL2);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        StorageReference storageReference3 = mStorageRef.child(imageName3);
        storageReference3.putFile(selected3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL3 = taskSnapshot.getDownloadUrl().toString();

                String otelAdi = oteladiText.getText().toString();
                String icerik = icerikText.getText().toString();

                myRef.child("Oteller").child(otelAdi).child("downloadurl3").setValue(downloadURL3);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        StorageReference storageReference4 = mStorageRef.child(imageName4);
        storageReference4.putFile(selected4).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadURL4 = taskSnapshot.getDownloadUrl().toString();

                String otelAdi = oteladiText.getText().toString();
                String icerik = icerikText.getText().toString();

                myRef.child("Oteller").child(otelAdi).child("downloadurl4").setValue(downloadURL4);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Toast.makeText(getApplicationContext(),"Başarıyla Eklendi", Toast.LENGTH_LONG).show();

        Intent intent2 = getIntent();
        String kullaniciAdi = intent2.getStringExtra("emailAktar");
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        intent.putExtra("emailAktar", kullaniciAdi);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);


    }





    public void fotoSecim(final View v){
        switch (v.getId()){
            case R.id.foto1View:
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,2);
                }
                break;
            case R.id.foto2View:
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},3);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,4);
                }
                break;
            case R.id.foto3View:
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},5);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,6);
                }
                break;
            case R.id.foto4View:
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},7);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,8);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);
            }
        }
        if (requestCode == 3) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,4);
            }
        }
        if (requestCode == 5) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,6);
            }
        }
        if (requestCode == 7) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,8);
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            selected = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selected);
                foto1View.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 4 && resultCode == RESULT_OK && data != null) {

            selected2 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selected2);
                foto2View.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 6 && resultCode == RESULT_OK && data != null) {

            selected3 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selected3);
                foto3View.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 8 && resultCode == RESULT_OK && data != null) {

            selected4 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selected4);
                foto4View.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



}
