package com.example.clothesshop.adapter;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.Bill;
import com.example.clothesshop.model.Purchased;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    ArrayList<Purchased> mPurchased;
    Activity activity;

    OnDetailListener onDetailListener;

    public interface OnDetailListener{
        void onClothesClick(int position);
    }
    public BillAdapter(Activity activity, ArrayList<Purchased> mPurchased, OnDetailListener onDetailListener){
        this.mPurchased = mPurchased;
        this.onDetailListener = onDetailListener;
        this.activity = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.purchase_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPurchased.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdBill, tvDate, tvNameClothes, tvCount, tvPrice, tvCountProduct, tvShipCost, tvTotal;
        Button btnDetail;
        ImageView imgProduct;
        public ViewHolder(@NonNull View view) {
            super(view);

            tvIdBill = view.findViewById(R.id.tvMaDonHang);
            tvDate = view.findViewById(R.id.tvDate);
            tvNameClothes = view.findViewById(R.id.tvClothes_Name_Bill);
            tvCount = view.findViewById(R.id.tvAmount);
            tvPrice = view.findViewById(R.id.tvPriceItem);
            tvCountProduct = view.findViewById(R.id.tvCountItem);
            tvShipCost = view.findViewById(R.id.tvShipPrice);
            tvTotal = view.findViewById(R.id.tvTotal_Price);
            imgProduct = view.findViewById(R.id.imageViewItem);
            btnDetail = view.findViewById(R.id.DetailButton);
        }
    }

}
