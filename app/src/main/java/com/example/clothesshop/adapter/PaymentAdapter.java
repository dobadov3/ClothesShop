package com.example.clothesshop.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Clothes;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {
    ArrayList<Clothes> mClothes;
    ArrayList<Cart> mCart;

    public PaymentAdapter(ArrayList<Clothes> mClothes, ArrayList<Cart> mCart){
        this.mClothes = mClothes;
        this.mCart = mCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Clothes clothes = mClothes.get(position);
        Cart cart = mCart.get(position);

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        Picasso.get().load(clothes.getImage()).into(holder.imageView);

        holder.tvName.setText(clothes.getName());

        holder.tvPrice.setText("" + currencyFormatter.format(clothes.getPrice()));

        holder.tvCount.setText("x"+ cart.getCount());
    }

    @Override
    public int getItemCount() {
        return mClothes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvName, tvPrice, tvCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgPay);
            tvName = itemView.findViewById(R.id.name_clothes_pay);
            tvCount = itemView.findViewById(R.id.tv_count_pay);
            tvPrice = itemView.findViewById(R.id.tv_clothes_price);
        }
    }
}
