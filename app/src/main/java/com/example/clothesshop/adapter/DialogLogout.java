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

import com.example.clothesshop.R;
import com.example.clothesshop.fragment.UserFragment;
import com.example.clothesshop.model.CustomerInfo;
import com.google.gson.Gson;

public class DialogLogout extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.logout))
                .setMessage(getString(R.string.check_logout))
                .setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserFragment.CheckLogin = false;
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("checklogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("customerInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();

                        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = sharedPreferences2.edit();

                        editor.putString("login", "false");

                        editor1.clear();
                        editor2.clear();

                        editor1.apply();
                        editor2.apply();
                        editor.apply();

                        UserFragment.setVisibility();
                    }
                }).setPositiveButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
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
