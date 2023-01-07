package com.example.pharmacieapp.user_pharmacie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacieapp.R;

import java.util.ArrayList;
import java.util.List;

public class PharmacieAdapter extends RecyclerView.Adapter<PharmacieAdapter.MyViewHolder> {
    Context context;
    List<Pharmacie> pharmacies;

    public PharmacieAdapter(Context context, List<Pharmacie> pharmacies) {
        this.context=context;
        this.pharmacies=pharmacies;
    }

    @NonNull
    @Override
    public PharmacieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pharmacie_item,parent,false);
        return new PharmacieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacieAdapter.MyViewHolder holder, int position) {
        holder.nom.setText(pharmacies.get(position).getNom());
        holder.address.setText(pharmacies.get(position).getAdresse());
        holder.latitude.setText(new Double(pharmacies.get(position).getLatitude()).toString());
        holder.longitude.setText(new Double(pharmacies.get(position).getLongitude()).toString());
        holder.etat.setText(pharmacies.get(position).getEtat().toString());


    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView nom,address,latitude,longitude,etat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageIV);
            nom = itemView.findViewById(R.id.nom);
            address = itemView.findViewById(R.id.address);
            latitude = itemView.findViewById(R.id.latitude);
            longitude = itemView.findViewById(R.id.longitude);
            etat = itemView.findViewById(R.id.etat);

        }
    }
}
