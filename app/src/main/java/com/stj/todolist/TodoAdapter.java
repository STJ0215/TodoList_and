package com.stj.todolist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends BaseAdapter {
    private List<Todo> todos;
    private View.OnClickListener onBtnDetailClicked;
    private View.OnClickListener onBtnShowModifyClicked;
    private View.OnClickListener onBtnDeleteClicked;

    private View.OnClickListener onBtnCancelModifyClicked;
    private View.OnClickListener onBtnModifyClicked;

    public TodoAdapter(List<Todo> todos, View.OnClickListener onBtnDetailClicked, View.OnClickListener onBtnShowModifyClicked, View.OnClickListener onBtnDeleteClicked, View.OnClickListener onBtnCancelModifyClicked, View.OnClickListener onBtnModifyClicked) {
        this.todos = todos;
        this.onBtnDetailClicked = onBtnDetailClicked;
        this.onBtnShowModifyClicked = onBtnShowModifyClicked;
        this.onBtnDeleteClicked = onBtnDeleteClicked;

        this.onBtnCancelModifyClicked = onBtnCancelModifyClicked;
        this.onBtnModifyClicked = onBtnModifyClicked;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int i) {
        Log.d("A1", "getItem");
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

            // 일반모드 일 때
            viewHolder.textViewId = convertView.findViewById(R.id.item_todo__textViewId);
            viewHolder.textViewId.setOnClickListener(onBtnDetailClicked);

            viewHolder.textViewTitle = convertView.findViewById(R.id.item_todo__textViewTitle);
            viewHolder.textViewTitle.setOnClickListener(onBtnDetailClicked);

            viewHolder.btnDetail = convertView.findViewById(R.id.item_todo__btnDetail);
            viewHolder.btnDetail.setOnClickListener(onBtnDetailClicked);

            viewHolder.btnShowModify = convertView.findViewById(R.id.item_todo__btnShowModify);
            viewHolder.btnShowModify.setOnClickListener(onBtnShowModifyClicked);

            viewHolder.btnDelete = convertView.findViewById(R.id.item_todo__btnDelete);
            viewHolder.btnDelete.setOnClickListener(onBtnDeleteClicked);

            // 수정모드 일 때
            viewHolder.editTextTitle = convertView.findViewById(R.id.item_todo__editTextTitle);

            viewHolder.btnCancelModify = convertView.findViewById(R.id.item_todo__btnCancelModify);
            viewHolder.btnCancelModify.setOnClickListener(onBtnCancelModifyClicked);

            viewHolder.btnModify = convertView.findViewById(R.id.item_todo__btnModify);
            viewHolder.btnModify.setOnClickListener(onBtnModifyClicked);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo todo = todos.get(position);

        viewHolder.textViewId.setText(todo.getId() + "");
        viewHolder.textViewId.setTag(position);

        viewHolder.textViewTitle.setText(todo.getTitle());
        viewHolder.textViewTitle.setTag(position);

        viewHolder.btnDetail.setTag(position);
        viewHolder.btnShowModify.setTag(position);
        viewHolder.btnDelete.setTag(position);

        viewHolder.editTextTitle.setText(todo.getTitle());
        viewHolder.btnCancelModify.setTag(position);
        viewHolder.btnModify.setTag(position);

        // 역할면에서 현재 수정모드여야 하는데, 배우가 수정모드가 아닐 때
        if (todo.isView__modifyMode() && viewHolder.textViewTitle.getVisibility() == View.VISIBLE) {
            // 강제로 수정버튼을 눌러서 수정모드로 바꿔준다.
            viewHolder.btnShowModify.performClick();
        }

        // 역할면에서 현재 일반모드여야 하는데, 배우가 일반모드가 아닐 때
        if (todo.isView__modifyMode() == false && viewHolder.textViewTitle.getVisibility() == View.GONE) {
            // 강제로 수정취소버튼을 눌러서 일반모드로 바꿔준다.
            viewHolder.btnCancelModify.performClick();
        }

        return convertView;
    }

    static class ViewHolder {
        TextView textViewId;
        TextView textViewTitle;
        Button btnDetail;
        Button btnShowModify;
        Button btnDelete;

        EditText editTextTitle;
        Button btnCancelModify;
        Button btnModify;
    }
}