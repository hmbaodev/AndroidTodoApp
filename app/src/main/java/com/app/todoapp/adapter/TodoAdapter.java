package com.app.todoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.todoapp.R;
import com.app.todoapp.model.Todo;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    public interface OnTodoClickListener {
        void onTodoClick(Todo todo);
        void onTodoChecked(Todo todo, boolean isChecked);
    }

    private List<Todo> todos;
    private OnTodoClickListener listener;

    public TodoAdapter(List<Todo> todos, OnTodoClickListener listener) {
        this.todos = todos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.tvTitle.setText(todo.getTitle());
        holder.tvDescription.setText(todo.getDescription());
        holder.cbCompleted.setChecked(todo.isCompleted());

        holder.itemView.setOnClickListener(v -> listener.onTodoClick(todo));
        holder.cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) ->
                listener.onTodoChecked(todo, isChecked));
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void updateTodos(List<Todo> newTodos) {
        this.todos = newTodos;
        notifyDataSetChanged();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        CheckBox cbCompleted;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            cbCompleted = itemView.findViewById(R.id.cb_completed);
        }
    }
}