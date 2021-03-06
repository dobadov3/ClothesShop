package com.example.clothesshop.adapter;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    ArrayList<Clothes> mClothes;
    OnClothesListener onClothesListener;

    public interface OnClothesListener{
        void onClothesClick(int position);
    }
    public ClothesAdapter(ArrayList<Clothes> mClothes, OnClothesListener onClothesListener)
    {
        this.mClothes = mClothes;
        this.onClothesListener = onClothesListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothes_layout, parent, false);

        return new ViewHolder(view, onClothesListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mClothes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageButton imageClothes;
        TextView tvClothesName;
        TextView tvClothesPrice;
        OnClothesListener onClothesListener;

        public ViewHolder(@NonNull View itemView, OnClothesListener onClothesListener) {
            super(itemView);

            imageClothes = itemView.findViewById(R.id.imageClothes);
            tvClothesName = itemView.findViewById(R.id.tvClothes);
            tvClothesPrice = itemView.findViewById(R.id.tvClothesPrice);
            this.onClothesListener = onClothesListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClothesListener.onClothesClick(getAdapterPosition());
        }
    }

}
