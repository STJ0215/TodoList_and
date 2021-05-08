package com.stj.todolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText editTextTodo;
    private List<String> todos;
    private ArrayAdapter<String> listViewTodoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todos = new ArrayList<>();
        editTextTodo = findViewById(R.id.activity_main__editTextTodo);

        ListView listViewTodo = findViewById(R.id.main_activity__listViewTodo);

        listViewTodo.setOnItemLongClickListener((adapterView, view, position, id) -> {
            final int indexToDelete = position;

            DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        todos.remove(indexToDelete);
                        listViewTodoAdapter.notifyDataSetChanged();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            };

            new AlertDialog.Builder(this)
                    .setMessage("삭제하시겠습니까?")
                    .setPositiveButton("예", onClickListener)
                    .setNegativeButton("아니오", onClickListener)
                    .show();
            
            return false;
        });

        listViewTodoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todos);
        listViewTodo.setAdapter(listViewTodoAdapter);
    }

    public void btnAddTodoClicked(View view) {
        String newTodo = editTextTodo.getText().toString().trim();
        editTextTodo.setText(newTodo);

        if (newTodo.length() == 0) {
            Toast.makeText(this, "할일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextTodo.requestFocus();

            return;
        }

        todos.add(0, newTodo);
        listViewTodoAdapter.notifyDataSetChanged();

        editTextTodo.setText("");
        editTextTodo.requestFocus();
    }
}