package com.example.clothesshop.adapter;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.Clothes;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {
    ArrayList<Clothes> mClothes;
    OnClothesSaleListener onClothesSaleListener;

    public interface OnClothesSaleListener{
        void onClothesSaleClick(int position);
    }

    public SaleAdapter(ArrayList<Clothes> mClothes, OnClothesSaleListener onClothesSaleListener)
    {
        this.mClothes = mClothes;
        this.onClothesSaleListener = onClothesSaleListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_layout, parent, false);

        return new ViewHolder(view, onClothesSaleListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mClothes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton imageClothes;
        TextView tvClothesName;
        TextView tvClothesPrice, tvSale;
        OnClothesSaleListener onClothesSaleListener;

        public ViewHolder(@NonNull View itemView, OnClothesSaleListener onClothesSaleListener) {
            super(itemView);

            imageClothes = itemView.findViewById(R.id.imageClothesSale);
            tvClothesName = itemView.findViewById(R.id.tvClothesSale);
            tvClothesPrice = itemView.findViewById(R.id.tvClothesPriceOG);
            tvSale = itemView.findViewById(R.id.tvClothesPriceSale);
            this.onClothesSaleListener = onClothesSaleListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClothesSaleListener.onClothesSaleClick(getAdapterPosition());
        }
    }
}
