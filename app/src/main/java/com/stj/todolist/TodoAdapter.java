package com.stj.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends BaseAdapter {
    private List<Todo> todos;

    public TodoAdapter(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int i) {
        return todos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return todos.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.textViewId = convertView.findViewById(R.id.item_todo__textViewId);
            viewHolder.textViewTitle = convertView.findViewById(R.id.item_todo__textViewTitle);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo todo = todos.get(position);

        viewHolder.textViewId.setText(todo.getId() + "");
        viewHolder.textViewTitle.setText(todo.getTitle());

        return convertView;
    }

    static class ViewHolder {
        TextView textViewId;
        TextView textViewTitle;
    }
}
