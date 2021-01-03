package com.example.clothesshop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clothesshop.DAO.NotiDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.activity.SignInActivity;
import com.example.clothesshop.adapter.NotificationAdapter;
import com.example.clothesshop.model.Account;
import com.example.clothesshop.model.Notification;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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

    ArrayList<Notification> mNoti;
    NotificationAdapter adapter;
    RecyclerView recyclerView;
    TextView tvNull;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewNoti);
        tvNull = view.findViewById(R.id.tvNullNoti);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("checklogin", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("login", "");
        if (login.equals("true")){
            setAdapterNoti();
        }else if (login.equals("false")){
            Intent intent = new Intent(getActivity().getApplication(), SignInActivity.class);
            startActivityForResult(intent, SignInActivity.RC_SIGN_IN);
        }

        return view;
    }

    void setAdapterNoti(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("accountInfo", "");

        Account account = gson.fromJson(json, Account.class);

        if(account != null){
            mNoti = NotiDAO.getInstance().getListNoti(account.getId());

            if (mNoti.size() != 0)
                tvNull.setVisibility(View.INVISIBLE);
            else
                tvNull.setVisibility(View.VISIBLE);

            adapter = new NotificationAdapter(mNoti);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SignInActivity.RC_SIGN_IN) {
            setAdapterNoti();
        }
    }
}