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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.DAO.RatingDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.activity.CartActivity;
import com.example.clothesshop.activity.PayActivity;
import com.example.clothesshop.adapter.RatingAdapter;
import com.example.clothesshop.model.Account;
import com.example.clothesshop.model.Cart;
import com.example.clothesshop.model.Clothes;
import com.example.clothesshop.model.Rating;
import com.example.clothesshop.model.RatingInfo;
import com.google.gson.Gson;
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
    Button btnCart, btnCash,btnSend;
    RadioButton radioButton;
    RatingBar ratingBar;
    ArrayList<RatingInfo> mRatingInfo;
    RatingAdapter adapter;
    RecyclerView recyclerView;
    EditText etRating;
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
        ratingBar = view.findViewById(R.id.ratingBar);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        img4 = view.findViewById(R.id.img4);
        btnSend = view.findViewById(R.id.btnSend);
        etRating = view.findViewById(R.id.etRating);
        recyclerView = view.findViewById(R.id.recyclerviewRating);

        LoadView();
        LoadComment();

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

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnSend(v);
            }
        });

        return view;
    }

    void LoadComment(){
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");

        Clothes clothes = (Clothes) bundle.getSerializable("Clothes");

        mRatingInfo = RatingDAO.getInstance().getRatingInfo(clothes.getId());
        adapter = new RatingAdapter(mRatingInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    boolean InsertRating(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("accountInfo", "");
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");

        Clothes clothes = (Clothes) bundle.getSerializable("Clothes");
        Account account = gson.fromJson(json, Account.class);

        return RatingDAO.getInstance().InsertRatingInfo(account.getId(), etRating.getText().toString(), ratingBar.getRating(), clothes.getId());
    }

    void LoadView(){
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");

        Clothes clothes = (Clothes) bundle.getSerializable("Clothes");

        Locale locale = new Locale("nv", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        int price = bundle.getInt("Price");

        Picasso.get().load(bundle.getString("Image")).into(imageViewCart);
        tvNameCart.setText(bundle.getString("Name"));
        tvPriceCart.setText("" + currencyFormatter.format(price));
        Picasso.get().load(bundle.getString("Image")).into(img1);
        Picasso.get().load(bundle.getString("Image2")).into(img2);
        Picasso.get().load(bundle.getString("Image3")).into(img3);
        Picasso.get().load(bundle.getString("Image4")).into(img4);
        ratingBar.setRating(RatingDAO.getInstance().getCurrentPoint(clothes.getId()));
        radioButton.setChecked(true);
    }

    private void onClickBtnSend(View view){
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");

        Clothes clothes = (Clothes) bundle.getSerializable("Clothes");

        float newPoint = ratingBar.getRating();

        RatingDAO.getInstance().UpdatePoint(clothes.getId(), newPoint);
        if(!etRating.getText().toString().equals("")){
            if (InsertRating()){
                mRatingInfo.clear();
                LoadComment();
                Toast.makeText(getActivity().getApplication(), getActivity().getString(R.string.rating), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onClickBtnCash(View view){
        Bundle bundle = this.getArguments();
        getFragmentManager().getFragment(bundle, "Bundle");

        Clothes clothes = (Clothes) bundle.getSerializable("Clothes");
        Cart cart = new Cart(clothes, "1");

        Intent intent = new Intent(getActivity().getApplication(), PayActivity.class);
        intent.putExtra("Clothes", clothes);
        intent.putExtra("Cart", cart);
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

        Cart cart = new Cart(clothes, "1");

        Cart.price +=  bundle.getInt("Price");
        CartActivity.mCart.add(cart);
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