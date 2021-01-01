package com.example.clothesshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothesshop.R;
import com.example.clothesshop.model.Rating;
import com.example.clothesshop.model.RatingInfo;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {
    ArrayList<RatingInfo> mRatingInfo;

    public RatingAdapter(ArrayList<RatingInfo> mRatingInfo){
        this.mRatingInfo = mRatingInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RatingInfo ratingInfo = mRatingInfo.get(position);

        holder.tvName.setText(ratingInfo.getNameCus());
        holder.tvDate.setText(ratingInfo.getDate().toString());
        holder.tvComment.setText(ratingInfo.getComment());
        holder.ratingBar.setRating(ratingInfo.getPoint());
    }

    @Override
    public int getItemCount() {
        return mRatingInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvComment;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameRating);
            tvDate = itemView.findViewById(R.id.tvDateRating);
            tvComment = itemView.findViewById(R.id.tvComment);
            ratingBar = itemView.findViewById(R.id.rating);
        }
    }
}
