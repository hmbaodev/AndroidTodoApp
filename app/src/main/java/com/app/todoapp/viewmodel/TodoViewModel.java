package com.app.todoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.todoapp.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoViewModel extends ViewModel {

    private final MutableLiveData<List<Todo>> todos = new MutableLiveData<>();

    public TodoViewModel() {
        loadDummyData();
    }

    public LiveData<List<Todo>> getTodos() {
        return todos;
    }

    public void addTodo(String title, String description) {
        List<Todo> current = todos.getValue();
        if (current == null) current = new ArrayList<>();

        Todo newTodo = new Todo(
                UUID.randomUUID().toString(),
                title,
                description,
                false
        );

        current.add(newTodo);
        todos.setValue(current);
    }

    public void toggleCompleted(Todo todo) {
        List<Todo> current = todos.getValue();
        if (current == null) return;

        for (Todo t : current) {
            if (t.getId().equals(todo.getId())) {
                t.setCompleted(!t.isCompleted());
                break;
            }
        }
        todos.setValue(current);
    }

    private void loadDummyData() {
        List<Todo> dummy = new ArrayList<>();
        dummy.add(new Todo("1", "Buy groceries", "Milk, eggs, bread", false));
        dummy.add(new Todo("2", "Read a book", "Clean code by Robert Martin", false));
        dummy.add(new Todo("3", "Go for a walk", "30 minutes in the park", true));
        todos.setValue(dummy);
    }
}