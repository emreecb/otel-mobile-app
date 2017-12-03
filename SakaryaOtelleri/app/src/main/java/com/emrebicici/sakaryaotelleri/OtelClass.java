package com.emrebicici.sakaryaotelleri;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Can on 25.11.2017.
 */

public class OtelClass extends ArrayAdapter<String> {

    private final ArrayList<String> otelAdlari;
    private final ArrayList<String> image1;
    private final ArrayList<String> otelPuanlari;
    private final Activity context;


    public OtelClass(ArrayList<String> otelAdlari, ArrayList<String> image1, ArrayList<String> otelPuanlari, Activity context) {
        super(context,R.layout.custom_view, otelAdlari);
        this.otelAdlari = otelAdlari;
        this.image1 = image1;
        this.otelPuanlari = otelPuanlari;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView otelAdi = (TextView) customView.findViewById(R.id.oteladi);
        ImageView imageView = (ImageView) customView.findViewById(R.id.imageView);
        TextView puan= (TextView) customView.findViewById(R.id.puanView);

        otelAdi.setText(otelAdlari.get(position));
        puan.setText("Editör Puanı: "+ otelPuanlari.get(position)+"/10");
        Picasso.with(context).load(image1.get(position)).into(imageView);

        return customView;
    }


}
