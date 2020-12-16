package com.example.clothesshop.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.clothesshop.R;
import com.example.clothesshop.adapter.HomeAdapter;
import com.example.clothesshop.model.Clothes;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    ImageButton img1, img2, img3, img4;
    ImageView imageViewCart;
    TextView tvNameCart;
    TextView tvPriceCart;
    Button btnCart, btnCash;
    RadioButton radioButton;
    public static int price = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        imageViewCart = view.findViewById(R.id.imgViewClothes);
        tvNameCart = view.findViewById(R.id.tvClothesName);
        tvPriceCart = view.findViewById(R.id.tvClothesPrice);
        btnCart = view.findViewById(R.id.btnCart);
        btnCash = view.findViewById(R.id.btnCash);
        radioButton = view.findViewById(R.id.radio_S);

        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImg1(v);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImg2(v);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImg3(v);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImg4(v);
            }
        });

        radioButton.setChecked(true);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnCart(v);
            }
        });

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnCash(v);
            }
        });

        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");
        Picasso.get().load(bundle.getString("Image")).into(imageViewCart);
        tvNameCart.setText(bundle.getString("Name"));
        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        int price = bundle.getInt("Price");
        tvPriceCart.setText("" + currencyFormatter.format(price));
        Picasso.get().load(bundle.getString("Image")).into(img1);
        Picasso.get().load(bundle.getString("Image2")).into(img2);
        Picasso.get().load(bundle.getString("Image3")).into(img3);
        Picasso.get().load(bundle.getString("Image4")).into(img4);
        return view;
    }

    private void onClickBtnCash(View view){
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");
        Clothes clothes = (Clothes) bundle.getSerializable("Clothes");
        Intent intent = new Intent(getActivity().getApplication(), PayActivity.class);
        intent.putExtra("Clothes", clothes);
        startActivityForResult(intent, PayActivity.PAY_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void onClickBtnCart(View view)
    {
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");
        Clothes clothes = (Clothes) bundle.getSerializable("Clothes");
        price +=  bundle.getInt("Price");
        CartActivity.mClothes.add(clothes);
    }

    void setImageCart(String imagePosition)
    {
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");
        String url = bundle.getString(imagePosition);
        Picasso.get().load(url).into(imageViewCart);
    }
    void onClickImg1(View view)
    {
        setImageCart("Image");
    }
    void onClickImg2(View view)
    {
        setImageCart("Image2");
    }
    void onClickImg3(View view)
    {
        setImageCart("Image3");
    }
    void onClickImg4(View view)
    {
        setImageCart("Image4");
    }


}