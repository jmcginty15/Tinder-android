package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tinder.databinding.CardFragmentBinding;
import com.example.tinder.databinding.SchoolFragmentBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardFragment extends Fragment {
    private MainViewModel viewModel;
    private UserAdapter adapter;

    private CardFragmentBinding binding;

    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance() {
        return new CardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = CardFragmentBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println("ON VIEW CREATED:  " + viewModel.getCurrentFragment());

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(100, true);

        adapter = new UserAdapter(requireContext(), viewModel.getUserList());
        binding.userRecyclerView.setAdapter(adapter);
        binding.userRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.cardContinueButton.setOnClickListener(v -> backToStart());
    }

    private void backToSchoolFragment() {
        viewModel.setCurrentFragment(R.id.destination_school_fragment);
        NavHostFragment.findNavController(this).navigate(R.id.action_card_fragment_pop);
    }

    private void backToStart() {
        viewModel.resetAll();
        NavHostFragment.findNavController(this).navigate(R.id.destination_email_fragment);
    }
}
