package com.example.clothesshop.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clothesshop.R;
import com.example.clothesshop.adapter.HomeAdapter;
import com.example.clothesshop.model.Clothes;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView recyclerViewProduct;
    ArrayList<Clothes> mclothes = new ArrayList<>();
    HomeAdapter adapter;
    ImageButton imageButtonTops;
    ImageButton imageButtonBottoms;
    ImageButton imageButtonBags;
    ImageButton imageButtonHats;
    ImageButton imageButtonSales;
    TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerViewProduct = view.findViewById(R.id.recyclerViewProducts);

        imageButtonBottoms = view.findViewById(R.id.imgBtnBottoms);
        imageButtonTops = view.findViewById(R.id.imgBtnTop);
        imageButtonBags = view.findViewById(R.id.imgBtnBags);
        imageButtonHats = view.findViewById(R.id.imgBtnHats);
        imageButtonSales = view.findViewById(R.id.imgBtnSales);
        tvTitle = view.findViewById(R.id.tvTitle);

        imageButtonTops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTops(v);
            }
        });

        imageButtonBottoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBottoms(v);
            }
        });

        imageButtonBags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBags(v);
            }
        });

        imageButtonHats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHats(v);
            }
        });

        imageButtonSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSales(v);
            }
        });

        onClickTops(view);
        return view;
    }
    private void AddClothes(){
        mclothes.add(new Clothes("Áo 1", 400000, R.drawable.image_2));
        mclothes.add(new Clothes("Áo 2", 300000, R.drawable.image_3));
        mclothes.add(new Clothes("Áo 3", 250000, R.drawable.image_4));
        mclothes.add(new Clothes("Áo 4", 500000, R.drawable.image_5));
        mclothes.add(new Clothes("Áo 1", 400000, R.drawable.image_2));
        mclothes.add(new Clothes("Áo 2", 300000, R.drawable.image_3));
        mclothes.add(new Clothes("Áo 3", 250000, R.drawable.image_4));
        mclothes.add(new Clothes("Áo 4", 500000, R.drawable.image_5));
        mclothes.add(new Clothes("Áo 1", 400000, R.drawable.image_2));
    }
    private void onClickTops(View view)
    {
        mclothes.clear();
        tvTitle.setText("TOPS");
        AddClothes();
        adapter = new HomeAdapter(mclothes);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewProduct.setAdapter(adapter);
    }
    private void onClickBottoms(View view)
    {
        mclothes.clear();
        tvTitle.setText("BOTTOMS");
        AddClothes();
        adapter = new HomeAdapter(mclothes);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewProduct.setAdapter(adapter);
    }
    private void onClickBags(View view)
    {
        mclothes.clear();
        tvTitle.setText("BAGS");
        AddClothes();
        adapter = new HomeAdapter(mclothes);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewProduct.setAdapter(adapter);
    }
    private void onClickHats(View view)
    {
        mclothes.clear();
        tvTitle.setText("HATS");
        AddClothes();
        adapter = new HomeAdapter(mclothes);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewProduct.setAdapter(adapter);
    }
    private void onClickSales(View view)
    {
        mclothes.clear();
        tvTitle.setText("SALES");
        AddClothes();
        adapter = new HomeAdapter(mclothes);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewProduct.setAdapter(adapter);
    }
}