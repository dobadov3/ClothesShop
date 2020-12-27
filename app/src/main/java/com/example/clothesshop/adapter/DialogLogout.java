package com.example.clothesshop.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.clothesshop.fragment.UserFragment;
import com.example.clothesshop.model.CustomerInfo;
import com.google.gson.Gson;

public class DialogLogout extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Đăng xuất")
                .setMessage("Bạn có chắc là muốn đăng xuất không?")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserFragment.CheckLogin = false;
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("checklogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();

                        Gson gson = new Gson();
                        String json =gson.toJson(new CustomerInfo());
                        editor1.putString("cusInfo", json);

                        editor1.apply();

                        editor.putString("login", "false");
                        editor.apply();
                        UserFragment.setVisibility();
                    }
                }).setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserFragment.CheckLogin = true;
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("checklogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("login", "true");
                editor.apply();
                UserFragment.setVisibility();
            }
        });

        return builder.create();
    }
}
