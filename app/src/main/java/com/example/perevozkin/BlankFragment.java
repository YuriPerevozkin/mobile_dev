package com.example.perevozkin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    public TextView balance_text;
    public TextView title_text;

    private TextView current_date;
    private Button button;
    private Button switch_activity;

    private EditText username_input;

    private UpdateListener updateListener;

    private int count = 0;

    public BlankFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    public static BlankFragment newInstance(String param1, String param2) {
        int counter = 0;
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        updateListener = (UpdateListener) ctx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        title_text = view.findViewById(R.id.titleText);

        button = view.findViewById(R.id.button);

        switch_activity = view.findViewById(R.id.button2);

        username_input = view.findViewById(R.id.usernameInput);

        balance_text = view.findViewById(R.id.balanceText);
        balance_text.setText("Баланс: " + String.valueOf(count));
        current_date = view.findViewById(R.id.currentDate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;
                balance_text.setText("Баланс: " + String.valueOf(count));
            }
        });

        balance_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragment2 fragment2 = new BlankFragment2();
                updateListener.onFragmentSwitch(R.id.main, fragment2);
            }
        });

        title_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlankFragment3 fragment3 = new BlankFragment3();
                updateListener.onFragmentSwitch(R.id.main, fragment3);
            }
        });

        switch_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                intent.putExtra("USERNAME", username_input.getText().toString());
                startActivity(intent);
            }
        });

        String date = getActivity().getIntent().getStringExtra("DATE");
        if (date == null) {
            current_date.setText("No date was provided");
        }
        else {
            current_date.setText(date);
        }

        return view;
    }
}