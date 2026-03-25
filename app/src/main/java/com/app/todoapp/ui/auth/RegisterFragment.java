package com.app.todoapp.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.todoapp.MainActivity;
import com.app.todoapp.R;
import com.app.todoapp.viewmodel.AuthViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment {

    private AuthViewModel authViewModel;

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

        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        TextInputEditText etName = view.findViewById(R.id.et_name);
        TextInputEditText etEmail = view.findViewById(R.id.et_email);
        TextInputEditText etPassword = view.findViewById(R.id.et_password);
        MaterialButton btnRegister = view.findViewById(R.id.btn_register);
        TextView tvGoLogin = view.findViewById(R.id.tv_go_login);

        // Observe register success
        authViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                requireActivity().finish();
            }
        });

        // Observe errors
        authViewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText() != null
                    ? etName.getText().toString().trim() : "";
            String email = etEmail.getText() != null
                    ? etEmail.getText().toString().trim() : "";
            String password = etPassword.getText() != null
                    ? etPassword.getText().toString().trim() : "";

            if (name.isEmpty()) {
                etName.setError("Name is required");
                return;
            }
            if (email.isEmpty()) {
                etEmail.setError("Email is required");
                return;
            }
            if (password.isEmpty()) {
                etPassword.setError("Password is required");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters");
                return;
            }

            authViewModel.register(email, password);
        });

        tvGoLogin.setOnClickListener(v ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_register_to_login)
        );
    }
}