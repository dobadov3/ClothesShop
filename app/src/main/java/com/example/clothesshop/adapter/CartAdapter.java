package com.example.clothesshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.clothesshop.R;
import com.example.clothesshop.activity.CartActivity;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Clothes;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>  {

    ArrayList<Clothes> mClothes;
    ArrayList<Cart> mCart;

    public CartAdapter(ArrayList<Clothes> mClothes, ArrayList<Cart> carts){
        this.mClothes = mClothes;
        this.mCart = carts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Clothes clothes = mClothes.get(position);
        Cart cart = mCart.get(position);

        holder.elegantNumberButton.setRange(1, 50);

        Picasso.get().load(clothes.getImage()).into(holder.imageView);

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        holder.elegantNumberButton.setNumber(cart.getCount());

        int number = Integer.parseInt(holder.elegantNumberButton.getNumber());

        holder.textView.setText("Thành tiền: " + currencyFormatter.format(clothes.getPriceSale()*number));

        //Set event cho nút xóa item
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.price -= mClothes.get(position).getPriceSale()*Integer.parseInt(holder.elegantNumberButton.getNumber());
                CartActivity.tvTotal.setText("Thành tiền: " + currencyFormatter.format(Cart.price));
                removeAt(position);
                CartActivity.tv1.setText("Bạn đang có " + mClothes.size() + " trong giỏ hàng");
            }
        });

        //set event cho ElegantNumberButton
        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                if(newValue>oldValue)
                {
                    Cart.price += clothes.getPriceSale();
                }
                else {
                    Cart.price -= clothes.getPriceSale();
                }
                holder.textView.setText("Thành tiền: " + currencyFormatter.format(clothes.getPriceSale()*Integer.parseInt(holder.elegantNumberButton.getNumber())));
                CartActivity.tvTotal.setText("Thành tiền: " + currencyFormatter.format(Cart.price));
                cart.setCount(holder.elegantNumberButton.getNumber());
            }
        });
    }

    //Xóa một item ra khỏi Recyclerview
    public void removeAt(int position) {
        mClothes.remove(position);
        mCart.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mClothes.size());
    }
    @Override
    public int getItemCount() {
        return mClothes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        ImageButton imageButton;
        ElegantNumberButton elegantNumberButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewCart);
            textView = itemView.findViewById(R.id.tvPriceCart);
            imageButton = itemView.findViewById(R.id.btnDeleteCash);
            elegantNumberButton = itemView.findViewById(R.id.elegantNumberCart);
        }

    }
}
