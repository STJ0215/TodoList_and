package com.stj.todolist;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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
            viewHolder.btnDelete = convertView.findViewById(R.id.item_todo__btnDelete);

            viewHolder.btnDelete.setOnClickListener(view -> {
                final int indexToDelete = (int)view.getTag();

                DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            todos.remove(indexToDelete);
                            this.notifyDataSetChanged();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                };

                new AlertDialog.Builder(parent.getContext())
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("예", onClickListener)
                        .setNegativeButton("아니오", onClickListener)
                        .show();
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo todo = todos.get(position);

        viewHolder.textViewId.setText(todo.getId() + "");
        viewHolder.textViewTitle.setText(todo.getTitle());
        viewHolder.btnDelete.setTag(position);

        return convertView;
    }

    static class ViewHolder {
        TextView textViewId;
        TextView textViewTitle;
        TextView btnDelete;
    }
}