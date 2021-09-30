package com.mr.realmandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mr.realmandroid.R;
import com.mr.realmandroid.model.ModelData;
import com.mr.realmandroid.views.UpdateActivity;

import java.util.ArrayList;

public class DataApadater extends RecyclerView.Adapter<DataApadater.ViewHolder> {

    private Context context;
    private ArrayList<ModelData> dataArray;

    public DataApadater(Context context, ArrayList<ModelData> dataArray) {
        this.context = context;
        this.dataArray = dataArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_data, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataApadater.ViewHolder holder, int position) {

        holder.tvNama.setText(dataArray.get(position).getNama().toString());
        holder.tvUmur.setText(String.valueOf(dataArray.get(position).getUmur()));
        holder.tvAlamat.setText(dataArray.get(position).getAlamat().toString());

        /* Jika salah satu CardView di Click, maka akan berpindah ke UpdateACtivity dengan membawa Data */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahHalaman = new Intent(context, UpdateActivity.class);
                pindahHalaman.putExtra("DATA_ID", dataArray.get(position).getId());
                pindahHalaman.putExtra("DATA_NAMA", dataArray.get(position).getNama());
                pindahHalaman.putExtra("DATA_UMUR", dataArray.get(position).getUmur());
                pindahHalaman.putExtra("DATA_ALAMAT", dataArray.get(position).getAlamat());
                context.startActivity(pindahHalaman);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama, tvUmur, tvAlamat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvUmur = itemView.findViewById(R.id.tv_umur);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);

        }
    }
}
