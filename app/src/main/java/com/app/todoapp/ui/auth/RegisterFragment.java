package com.app.todoapp.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.app.todoapp.R;
import com.google.android.material.button.MaterialButton;

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnRegister = view.findViewById(R.id.btn_register);
        TextView tvGoLogin = view.findViewById(R.id.tv_go_login);

        btnRegister.setOnClickListener(v -> {
            // TODO: hook up real auth later
        });

        tvGoLogin.setOnClickListener(v ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_register_to_login)
        );
    }
}