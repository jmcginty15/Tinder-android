package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardFragment extends Fragment {
    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance() {
        return new CardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.card_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();

        TextView nameField = view.findViewById(R.id.name);
        TextView ageField = view.findViewById(R.id.age);
        TextView genderField = view.findViewById(R.id.gender);
        TextView schoolField = view.findViewById(R.id.school);
        TextView emailField = view.findViewById(R.id.email);

        nameField.setText(mainActivity.getName());
        ageField.setText(parseAge(mainActivity.getBirthday()));
        if (mainActivity.getShowGender()) { genderField.setText(parseGender(mainActivity.getGender())); }
        else { genderField.setText("Gender hidden"); }
        schoolField.setText(mainActivity.getSchool());
        emailField.setText(mainActivity.getEmail());

        parseAge(mainActivity.getBirthday());
    }

    private String parseAge(String birthdayString) {
        int length = birthdayString.length();
        String format = length == 10 ? "MM/dd/yyyy" : "MM/dd/yy";

        Date birthday = null;
        try {
            birthday = new SimpleDateFormat(format).parse(birthdayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date currentDate = new Date();
        long ms = currentDate.getTime() - birthday.getTime();

        double s = (double) ms / 1000.0;
        double h = s / 3600;
        double d = h / 24;
        double y = d / 365.25;

        int age = (int) Math.floor(y);

        return Integer.toString(age);
    }

    private String parseGender(int val) {
        if (val == 1) { return "Woman"; }
        else if (val == -1 ) { return "Man"; }
        else { return "You done goofed"; }
    }
}
