package com.example.assignmet_ph42448.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignmet_ph42448.Home;
import com.example.assignmet_ph42448.Model.Cay;
import com.example.assignmet_ph42448.R;

import java.text.NumberFormat;
import java.util.List;

public class AdapterCay extends RecyclerView.Adapter<AdapterCay.ViewHolder>{
    private List<Cay> list;
    private Context context;
    private Home home;

    public AdapterCay(List<Cay> list, Context context, Home home) {
        this.list = list;
        this.context = context;
        this.home = home;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cay cay = list.get(position);
        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        holder.tvten.setText(cay.getTen());
        holder.tvgia.setText(numberFormat.format(cay.getGia())+" đ");
        holder.tvkichthuoc.setText(cay.getKichthuoc());

        Glide.with(context)
                .load(cay.getAnh())
                .thumbnail(Glide.with(context).load(R.drawable.loading))
                .into(holder.image);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.xoa(cay.get_id());
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.them(context, 1, cay);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvten, tvgia, tvkichthuoc;
        Button btnDelete, btnUpdate;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tvten = itemView.findViewById(R.id.tvTen);
            tvgia = itemView.findViewById(R.id.tvGia);
            tvkichthuoc = itemView.findViewById(R.id.tvKichThuoc);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }

}
