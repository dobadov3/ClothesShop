package com.example.clothesshop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clothesshop.DAO.PurchaseDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.BillAdapter;
import com.example.clothesshop.model.Account;
import com.example.clothesshop.model.Purchased;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment implements BillAdapter.OnDetailListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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

    RecyclerView recyclerView;
    ArrayList<Purchased> mPurchased;
    BillAdapter adapter;
    TextView tvNull;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewPurchased);
        tvNull = view.findViewById(R.id.tvNull);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("accountInfo", "");

        Account account = gson.fromJson(json, Account.class);

        mPurchased = PurchaseDAO.getInstance().getListPurchased(account.getId());
        adapter = new BillAdapter(mPurchased, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        if (mPurchased.size() == 0)
            tvNull.setVisibility(View.VISIBLE);
        else
            tvNull.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onClothesClick(int position) {
        Purchased purchased = mPurchased.get(position);

        BillInfoFragment billInfoFragment = new BillInfoFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("idBill", purchased.getId());

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.layout_container, billInfoFragment);
        billInfoFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
}