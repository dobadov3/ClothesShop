package com.example.clothesshop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothesshop.DAO.CustomerDAO;
import com.example.clothesshop.R;
import com.example.clothesshop.model.CustomerInfo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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

    Spinner spinGender;
    Button btnUpdate;
    EditText etName, etTel, etEmail, etAddress;
    String[] arrGender = {"Nam", "Nữ", "Khác"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        spinGender = view.findViewById(R.id.spin_gender);
        etName = view.findViewById(R.id.etNameCus);
        etTel = view.findViewById(R.id.etTelCus);
        etEmail = view.findViewById(R.id.etEmailCus);
        etAddress = view.findViewById(R.id.etAddressCus);
        btnUpdate = view.findViewById(R.id.btnUpdateInfo);
        TextView textView = view.findViewById(R.id.tvEmailCus);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnUpdate(v);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, arrGender);

        spinGender.setAdapter(adapter);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            CustomerInfo customerInfo = (CustomerInfo) bundle.get("customerInfo");
            if (customerInfo != null){
                etName.setText(customerInfo.getName());
                etTel.setText(customerInfo.getTel());
                String gender = customerInfo.getGender();
                if (gender.equals("Nam"))
                {
                    spinGender.setSelection(1);
                }
                else if(gender.equals("Nữ")){
                    spinGender.setSelection(2);
                }
                else{
                    spinGender.setSelection(3);
                }

                etEmail.setText(customerInfo.getEmail());
                etAddress.setText(customerInfo.getAddress());
            }
        }

        return view;
    }
    private void onClickBtnUpdate(View view){
        String name  = etName.getText().toString();
        String gender = spinGender.getSelectedItem().toString();
        String tel = etTel.getText().toString();
        String email = etEmail.getText().toString();
        String address = etAddress.getText().toString();

        if (CustomerDAO.getInstance().UpdateInfo(name, gender, tel, email, address))
        {
            Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
        }
    }
}