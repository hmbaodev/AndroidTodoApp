package com.app.todoapp.ui.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.todoapp.R;
import com.google.android.material.button.MaterialButton;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnLogin = view.findViewById(R.id.btn_login);
        TextView tvGoRegister = view.findViewById(R.id.tv_go_register);

        btnLogin.setOnClickListener(v -> {
            // TODO: hook up real auth later
        });

        tvGoRegister.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register);
        });
    }
}