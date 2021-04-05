package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardFragment extends Fragment {
    TextView nameField;
    TextView ageField;
    TextView genderField;
    TextView schoolField;
    TextView emailField;

    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance() {
        return new CardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.card_fragment, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        nameField = rootView.findViewById(R.id.name);
        ageField = rootView.findViewById(R.id.age);
        genderField = rootView.findViewById(R.id.gender);
        schoolField = rootView.findViewById(R.id.school);
        emailField = rootView.findViewById(R.id.email);

        nameField.setText(mainActivity.getName());
        ageField.setText(parseAge(mainActivity.getBirthday()));
        if (mainActivity.getShowGender()) { genderField.setText(parseGender(mainActivity.getGender())); }
        else { genderField.setText("Gender hidden"); }
        schoolField.setText(mainActivity.getSchool());
        emailField.setText(mainActivity.getEmail());

        parseAge(mainActivity.getBirthday());

        return rootView;
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
