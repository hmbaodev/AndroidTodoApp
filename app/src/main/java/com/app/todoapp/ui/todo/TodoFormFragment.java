package com.app.todoapp.ui.todo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.todoapp.R;
import com.app.todoapp.viewmodel.TodoViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class TodoFormFragment extends Fragment {

    private TodoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(TodoViewModel.class);

        TextInputEditText etTitle = view.findViewById(R.id.et_title);
        TextInputEditText etDescription = view.findViewById(R.id.et_description);
        MaterialButton btnSave = view.findViewById(R.id.btn_save);
        MaterialButton btnCancel = view.findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText() != null
                    ? etTitle.getText().toString().trim() : "";
            String description = etDescription.getText() != null
                    ? etDescription.getText().toString().trim() : "";

            if (TextUtils.isEmpty(title)) {
                etTitle.setError("Title is required");
                return;
            }

            viewModel.addTodo(title, description);
            Navigation.findNavController(view)
                    .navigate(R.id.action_form_to_list);
        });

        btnCancel.setOnClickListener(v ->
                Navigation.findNavController(view).popBackStack()
        );
    }
}