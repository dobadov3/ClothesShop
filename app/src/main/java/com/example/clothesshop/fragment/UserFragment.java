package com.example.clothesshop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.R;
import com.example.clothesshop.activity.MainActivity;
import com.example.clothesshop.activity.SignInActivity;
import com.example.clothesshop.adapter.DialogLogout;
import com.example.clothesshop.model.CustomerInfo;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
    public static final int SIGN_IN_REQUEST_CODE = 4667;
    public static boolean CheckLogin = false;
    RelativeLayout relativeLayoutInfo;
    static RelativeLayout relativeLayoutLogout;
    RelativeLayout relativeLayoutBill;
    RelativeLayout relativeLayoutHistory;
    RelativeLayout relativeLayoutContact;
    static ImageView imageView;
    public static TextView tvUserName, tvBirth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        relativeLayoutInfo = view.findViewById(R.id.relative2);
        relativeLayoutBill = view.findViewById(R.id.relative3);
        relativeLayoutHistory = view.findViewById(R.id.relative4);
        relativeLayoutContact = view.findViewById(R.id.relative5);
        relativeLayoutLogout = view.findViewById(R.id.relative6);
        imageView = view.findViewById(R.id.imgAvatar);
        tvUserName = view.findViewById(R.id.tvNameUser);
        tvBirth = view.findViewById(R.id.tvBirthday);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("checklogin", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("login", "");
        if(login.equals("true")){
            CheckLogin = true;
            SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences1.getString("cusInfo", "");
            CustomerInfo customerInfo = gson.fromJson(json, CustomerInfo.class);

            tvUserName.setText(customerInfo.getName());
            tvBirth.setText(customerInfo.getTel());
        }
        else if (login.equals("false")){
            CheckLogin = false;
        }

        setVisibility();

        relativeLayoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRelativeInfo(v);
            }
        });

        relativeLayoutBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRelativeBill(v);
            }
        });

        relativeLayoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRelativeHistory(v);
            }
        });

        relativeLayoutContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRelativeContact(v);
            }
        });

        relativeLayoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRelativeLogout(v);
            }
        });

        return view;
    }

    void onClickRelativeInfo(View view){
        if(CheckLogin == false)
        {
            Intent intent = new Intent(getActivity().getApplication(), SignInActivity.class);
            startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
        }
        else{
            InfoFragment infoFragment = new InfoFragment();
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("cusInfo", "");
            CustomerInfo customerInfo = gson.fromJson(json, CustomerInfo.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("customerInfo", customerInfo);
            infoFragment.setArguments(bundle);
            MoveToFragment(infoFragment);
            MainActivity.tvTitle.setText("THÔNG TIN");
        }
    }
    void onClickRelativeBill(View view){
        if(CheckLogin == false)
        {
            Intent intent = new Intent(getActivity().getApplication(), SignInActivity.class);
            startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
        }
        else{
            BillFragment billFragment = new BillFragment();
            MoveToFragment(billFragment);
            MainActivity.tvTitle.setText("ĐƠN HÀNG");
        }
    }
    void onClickRelativeHistory(View view){
        if(CheckLogin == false)
        {
            Intent intent = new Intent(getActivity().getApplication(), SignInActivity.class);
            startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
        }
        else{
            HistoryFragment historyFragment = new HistoryFragment();
            MoveToFragment(historyFragment);
            MainActivity.tvTitle.setText("LỊCH SỬ");
        }
    }
    void onClickRelativeContact(View view){
        if(CheckLogin == false)
        {
            Intent intent = new Intent(getActivity().getApplication(), SignInActivity.class);
            startActivityForResult(intent, SIGN_IN_REQUEST_CODE);
        }
        else{
            ContactFragment contactFragment = new ContactFragment();
            MoveToFragment(contactFragment);
            MainActivity.tvTitle.setText("LIÊN HỆ");
        }
    }
    void onClickRelativeLogout(View view){
        DialogLogout dialogLogout = new DialogLogout();
        dialogLogout.show(getActivity().getSupportFragmentManager(), "Logout");
        setVisibility();
    }

    public static void setVisibility(){
        if (CheckLogin){
            imageView.setVisibility(View.VISIBLE);
            relativeLayoutLogout.setVisibility(View.VISIBLE);}
        else{
            tvUserName.setText("");
            tvBirth.setText("");
            imageView.setVisibility(View.INVISIBLE);
            relativeLayoutLogout.setVisibility(View.INVISIBLE);}
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SIGN_IN_REQUEST_CODE)
        {
            CustomerInfo customerInfo = (CustomerInfo) data.getSerializableExtra("CustomerInfo");
            if (customerInfo != null)
            {
                CheckLogin = true;
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Gson gson = new Gson();
                String json =gson.toJson(customerInfo);
                editor.putString("cusInfo", json);

                editor.apply();

                tvUserName.setText(customerInfo.getName());
                tvBirth.setText(customerInfo.getTel());
                setVisibility();
            }
        }
        if(data == null)
            return;
    }
    void MoveToFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.layout_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences1.getString("cusInfo", "");
        CustomerInfo customerInfo = gson.fromJson(json, CustomerInfo.class);

        tvUserName.setText(customerInfo.getName());
        tvBirth.setText(customerInfo.getTel());
        MainActivity.tvTitle.setText("CÁ NHÂN");
    }


}