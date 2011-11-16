package com.example.android.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoList extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView myListView = (ListView) findViewById(R.id.myListView);
        final EditText myEditText = (EditText) findViewById(R.id.myEditText);
        
        final ArrayList<String> todoItems = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        
        myListView.setAdapter(arrayAdapter);
        
        myEditText.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_DOWN){
					if(keyCode == KeyEvent.KEYCODE_ENTER){
						System.out.println("pego evento");
						todoItems.add(myEditText.getText().toString());
						myEditText.setText("");
						return true;
					}
				}
				return false;
			}
		});
    }
}