package com.example.clothesshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.Clothes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<Clothes> mClothes;

    public HomeAdapter(ArrayList<Clothes> mClothes)
    {
        this.mClothes = mClothes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothes_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Clothes clothes = mClothes.get(position);

        Picasso.get().load(clothes.getImage()).into(holder.imageClothes);
        holder.tvClothesName.setText(clothes.getName());
        holder.tvClothesPrice.setText("" + clothes.getPrice());
    }

    @Override
    public int getItemCount() {
        return mClothes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageClothes;
        TextView tvClothesName;
        TextView tvClothesPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageClothes = itemView.findViewById(R.id.imageClothes);
            tvClothesName = itemView.findViewById(R.id.tvClothes);
            tvClothesPrice = itemView.findViewById(R.id.tvClothesPrice);
        }
    }
}
