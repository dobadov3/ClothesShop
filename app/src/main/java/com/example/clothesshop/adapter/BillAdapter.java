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
        Purchased purchased = mPurchased.get(position);
        
        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        Picasso.get().load(purchased.getImage()).into(holder.imgProduct);
        holder.tvIdBill.setText(activity.getString(R.string.bill_id) + ": " + purchased.getId());
        holder.tvDate.setText(activity.getString(R.string.date_buy) + " " + purchased.getCreateDay().toString());
        holder.tvNameClothes.setText(purchased.getName());
        holder.tvCount.setText("x" + purchased.getCountProduct());
        holder.tvPrice.setText("" + currencyFormatter.format(purchased.getPrice() * purchased.getCountProduct()));
        holder.tvCountProduct.setText(purchased.getCountItem() + " " + activity.getString(R.string.product));
        holder.tvShipCost.setText(activity.getString(R.string.transport_fee) + ": " + currencyFormatter.format(purchased.getShipCost()));
        holder.tvTotal.setText(activity.getString(R.string.totalPrice) + " " +currencyFormatter.format(purchased.getTotal()));

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetailListener.onClothesClick(position);
            }
        });
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
