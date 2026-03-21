package com.app.todoapp.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.todoapp.R;
import com.app.todoapp.adapter.TodoAdapter;
import com.app.todoapp.model.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TodoListFragment extends Fragment {

    private TodoAdapter adapter;

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

        // Dummy data for now
        List<Todo> dummyTodos = new ArrayList<>();
        dummyTodos.add(new Todo("1", "Buy groceries", "Milk, eggs, bread", false));
        dummyTodos.add(new Todo("2", "Read a book", "Clean code by Robert Martin", false));
        dummyTodos.add(new Todo("3", "Go for a walk", "30 minutes in the park", true));

        adapter = new TodoAdapter(dummyTodos, new TodoAdapter.OnTodoClickListener() {
            @Override
            public void onTodoClick(Todo todo) {
                // TODO: navigate to edit screen
            }

            @Override
            public void onTodoChecked(Todo todo, boolean isChecked) {
                // TODO: update todo status
            }
        });

        rvTodos.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTodos.setAdapter(adapter);

        fabAdd.setOnClickListener(v ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_list_to_form)
        );
    }
}