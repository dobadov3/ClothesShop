package com.example.clothesshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.clothesshop.DAO.ClothesDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.HomeAdapter;
import com.example.clothesshop.fragment.CartFragment;
import com.example.clothesshop.model.Clothes;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements HomeAdapter.OnClothesListener {
    public static final int REQUEST_SEARCH = 1510;
    ArrayList<Clothes> mClothes;
    HomeAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerviewSearch);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("searchBundle");
        String name = bundle.getString("search");

        mClothes = ClothesDAO.getInstance().searchByName(name);
        adapter = new HomeAdapter(mClothes, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClothesClick(int position) {
        Clothes clothes = mClothes.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("Image", clothes.getImage());
        bundle.putString("Image2", clothes.getImage2());
        bundle.putString("Image3", clothes.getImage3());
        bundle.putString("Image4", clothes.getImage4());
        bundle.putString("Name", clothes.getName());
        bundle.putInt("Price", clothes.getPriceSale());
        bundle.putSerializable("Clothes", clothes);

        Intent intent = new Intent();
        intent.putExtra("clothesInfo",bundle);
        setResult(MainActivity.REQUEST_MAIN_ACTIVITY, intent);
        finish();
    }
}