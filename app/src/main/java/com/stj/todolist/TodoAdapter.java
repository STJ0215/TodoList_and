package com.stj.todolist;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends BaseAdapter {
    private List<Todo> todos;
    private View.OnClickListener onBtnDetailClicked;
    private View.OnClickListener onBtnShowModifyClicked;
    private View.OnClickListener onBtnModifyClicked;
    private View.OnClickListener onBtnCancelModifyClicked;
    private View.OnClickListener onBtnDeleteClicked;

    public TodoAdapter(List<Todo> todos, View.OnClickListener onBtnDetailClicked, View.OnClickListener onBtnShowModifyClicked, View.OnClickListener onBtnModifyClicked, View.OnClickListener onBtnCancelModifyClicked, View.OnClickListener onBtnDeleteClicked) {
        this.todos = todos;
        this.onBtnDetailClicked = onBtnDetailClicked;
        this.onBtnShowModifyClicked = onBtnShowModifyClicked;
        this.onBtnModifyClicked = onBtnModifyClicked;
        this.onBtnCancelModifyClicked = onBtnCancelModifyClicked;
        this.onBtnDeleteClicked = onBtnDeleteClicked;
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
            viewHolder.textViewId.setOnClickListener(onBtnDetailClicked);
            viewHolder.textViewTitle = convertView.findViewById(R.id.item_todo__textViewTitle);
            viewHolder.textViewTitle.setOnClickListener(onBtnDetailClicked);

            viewHolder.btnDetail = convertView.findViewById(R.id.item_todo__btnDetail);
            viewHolder.btnDetail.setOnClickListener(onBtnDetailClicked);

            viewHolder.btnShowModify = convertView.findViewById(R.id.item_todo__btnShowModify);
            viewHolder.btnShowModify.setOnClickListener(onBtnShowModifyClicked);

            viewHolder.btnModify = convertView.findViewById(R.id.item_todo__btnModify);
            viewHolder.btnModify.setOnClickListener(onBtnModifyClicked);

            viewHolder.btnCancelModify = convertView.findViewById(R.id.item_todo__btnCancelModify);
            viewHolder.btnCancelModify.setOnClickListener(onBtnCancelModifyClicked);

            viewHolder.btnDelete = convertView.findViewById(R.id.item_todo__btnDelete);
            viewHolder.btnDelete.setOnClickListener(onBtnDeleteClicked);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo todo = todos.get(position);

        viewHolder.textViewId.setText(todo.getId() + "");
        viewHolder.textViewId.setTag(position);
        viewHolder.textViewTitle.setText(todo.getTitle());
        viewHolder.textViewTitle.setTag(position);

        viewHolder.btnDetail.setTag(position);
        viewHolder.btnShowModify.setTag(position);
        viewHolder.btnModify.setTag(position);
        viewHolder.btnCancelModify.setTag(position);
        viewHolder.btnDelete.setTag(position);

        return convertView;
    }

    static class ViewHolder {
        TextView textViewId;
        TextView textViewTitle;
        Button btnDetail;
        Button btnShowModify;
        Button btnModify;
        Button btnCancelModify;
        Button btnDelete;
    }
}