package com.example.tinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinder.databinding.CardFragmentBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserCardHolder> {
    private final List<MainViewModel> userList;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<MainViewModel> userList) {
        inflater = LayoutInflater.from(context);
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.UserCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = inflater.inflate(R.layout.user_card, parent, false);
        return new UserCardHolder(userView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserCardHolder holder, int position) {
        final TextView emailTextView;
        final TextView nameTextView;
        final TextView ageTextView;
        final TextView genderTextView;
        final TextView schoolTextView;

        System.out.println(holder);

        MainViewModel user = userList.get(position);

        emailTextView = holder.userCardView.findViewById(R.id.email);
        nameTextView = holder.userCardView.findViewById(R.id.name);
        ageTextView = holder.userCardView.findViewById(R.id.age);
        genderTextView = holder.userCardView.findViewById(R.id.gender);
        schoolTextView = holder.userCardView.findViewById(R.id.school);

        emailTextView.setText(user.getEmail());
        nameTextView.setText(user.getName());
        System.out.println(user.getAge());
        System.out.println(user.getGender());
        ageTextView.setText(Integer.toString(user.getAge()));
        genderTextView.setText(user.getGenderString());
        schoolTextView.setText(user.getSchool());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserCardHolder extends RecyclerView.ViewHolder {
        public final LinearLayout userCardView;
        final UserAdapter adapter;

        public UserCardHolder(View userView, UserAdapter adapter) {
            super(userView);
            userCardView = userView.findViewById(R.id.user_card_container);
            this.adapter = adapter;
        }
    }
}
