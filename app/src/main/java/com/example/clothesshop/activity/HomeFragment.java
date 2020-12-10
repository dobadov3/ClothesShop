package com.example.clothesshop.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clothesshop.DAO.ClothesDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.HomeAdapter;
import com.example.clothesshop.model.Clothes;
import com.example.clothesshop.model.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeAdapter.OnClothesListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    ArrayList<Clothes> mclothes;
    RecyclerView recyclerViewSale;
    RecyclerView recyclerViewHot;
    HomeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewSale = view.findViewById(R.id.recyclerviewClothesSale);
        recyclerViewHot = view.findViewById(R.id.recyclerviewClothesHot);

        mclothes = ClothesDAO.getInstance().getListClothes();

        adapter = new HomeAdapter(mclothes, this);

        recyclerViewSale.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSale.setAdapter(adapter);

        recyclerViewHot.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHot.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClothesClick(int position) {
        Clothes clothes = mclothes.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("Image", clothes.getImage());
        bundle.putString("Image2", clothes.getImage2());
        bundle.putString("Image3", clothes.getImage3());
        bundle.putString("Image4", clothes.getImage4());
        bundle.putString("Name", clothes.getName());
        bundle.putInt("Price", clothes.getPrice());
        bundle.putSerializable("Clothes", clothes);

        CartFragment cartFragment = new CartFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.layout_container, cartFragment);
        cartFragment.setArguments(bundle);
        fragmentTransaction.commit();

    }
}