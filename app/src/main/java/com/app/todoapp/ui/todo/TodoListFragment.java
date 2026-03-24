package com.app.todoapp.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.todoapp.R;
import com.app.todoapp.adapter.TodoAdapter;
import com.app.todoapp.model.Todo;
import com.app.todoapp.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TodoListFragment extends Fragment {

    private TodoAdapter adapter;
    private TodoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvTodos = view.findViewById(R.id.rv_todos);
        FloatingActionButton fabAdd = view.findViewById(R.id.fab_add_todo);

        viewModel = new ViewModelProvider(requireActivity()).get(TodoViewModel.class);

        adapter = new TodoAdapter(new java.util.ArrayList<>(),
                new TodoAdapter.OnTodoClickListener() {
                    @Override
                    public void onTodoClick(Todo todo) {
                        // TODO: navigate to edit screen
                    }

                    @Override
                    public void onTodoChecked(Todo todo, boolean isChecked) {
                        viewModel.toggleCompleted(todo);
                    }
                });

        rvTodos.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTodos.setAdapter(adapter);

        // Observe LiveData — UI updates automatically when data changes
        viewModel.getTodos().observe(getViewLifecycleOwner(), todos ->
                adapter.updateTodos(todos)
        );

        fabAdd.setOnClickListener(v ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_list_to_form)
        );
    }
}