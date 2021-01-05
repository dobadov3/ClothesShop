package com.example.clothesshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.BillInfo;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BillInfoAdapter extends RecyclerView.Adapter<BillInfoAdapter.ViewHolder> {
    ArrayList<BillInfo> mBillInfo;

    public BillInfoAdapter(ArrayList<BillInfo> mBillInfo){
        this.mBillInfo = mBillInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_info_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBillInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCount, tvPrice;
        ImageView imageView;
        public ViewHolder(@NonNull View view) {
            super(view);

            tvName = view.findViewById(R.id.tv_name_clothes_billInfo);
            tvCount = view.findViewById(R.id.tvCountBillInfo);
            tvPrice = view.findViewById(R.id.tvPriceBillInfo);
            imageView = view.findViewById(R.id.imageViewBillInfo);
        }
    }
}
