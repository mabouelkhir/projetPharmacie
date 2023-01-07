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

import java.util.Date;
import java.util.List;

public class PharmacieAdapterValide extends RecyclerView.Adapter<PharmacieAdapterValide.MyViewHolder> {
    Context context;
    List<DatePharGarde> gardes;

    public PharmacieAdapterValide(Context context, List<DatePharGarde> gardes) {
        this.context=context;
        this.gardes=gardes;
    }

    @NonNull
    @Override
    public PharmacieAdapterValide.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pharmacie_garde,parent,false);
        return new PharmacieAdapterValide.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacieAdapterValide.MyViewHolder holder, int position) {
        holder.nom.setText(gardes.get(position).getPharmacie().getNom());
        holder.address.setText(gardes.get(position).getPharmacie().getAdresse());
        holder.date_debut.setText(gardes.get(position).getPk().getDatedebut());
        holder.date_fin.setText("Not yet");
        holder.garde.setText(gardes.get(position).getGarde().toString());


    }

    @Override
    public int getItemCount() {
        return gardes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView nom,address,date_debut,date_fin,garde;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageIV);
            nom = itemView.findViewById(R.id.nom);
            address = itemView.findViewById(R.id.address);
            date_debut = itemView.findViewById(R.id.date_debut);
            date_fin = itemView.findViewById(R.id.date_fin);
            garde = itemView.findViewById(R.id.garde);

        }
    }
}
