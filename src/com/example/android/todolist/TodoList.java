package com.example.android.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoList extends Activity {
	
	static final private int ADD_NEW = Menu.FIRST;
	static final private int REMOVE = Menu.FIRST + 1;
	
	private boolean addingNew = false;
	
	private ListView myListView;
	private EditText myEditText;
	private ArrayList<TodoItem> todoItems;
	private TodoItemAdapter arrayAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myListView = (ListView) findViewById(R.id.myListView);
        myEditText = (EditText) findViewById(R.id.myEditText);
        
        todoItems = new ArrayList<TodoItem>();
        arrayAdapter = new TodoItemAdapter(this, R.layout.todolist_item, todoItems);
        
        myListView.setAdapter(arrayAdapter);
        
        myEditText.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_DOWN){
					if(keyCode == KeyEvent.KEYCODE_ENTER){
						todoItems.add(new TodoItem(myEditText.getText().toString()));
						myEditText.setText("");
						arrayAdapter.notifyDataSetChanged();
						
						cancelAdd();
						return true;
					}
				}
				return false;
			}
		});
        
        registerForContextMenu(myListView);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	MenuItem addItem = menu.add(0, ADD_NEW, Menu.NONE, R.string.add_new);
    	MenuItem removeItem = menu.add(0, REMOVE, Menu.NONE, R.string.remove);
    	
    	addItem.setIcon(R.drawable.add);
    	removeItem.setIcon(R.drawable.remove);
    	
    	addItem.setShortcut('0', 'a');
    	removeItem.setShortcut('1', 'r');
    	
    	return true;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
    	menu.setHeaderTitle("Selected ToDo Item");
    	menu.add(0, REMOVE, Menu.NONE, R.string.remove);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	super.onPrepareOptionsMenu(menu);
    	
    	int idx = myListView.getSelectedItemPosition();
    	
    	String removeTitle = getString(addingNew ? R.string.cancel : R.string.remove);
    	
    	MenuItem removeItem = menu.findItem(REMOVE);
    	removeItem.setTitle(removeTitle);
    	removeItem.setVisible(addingNew || idx > -1);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	super.onOptionsItemSelected(item);
    	
    	int selectedItem = myListView.getSelectedItemPosition();
    	
    	switch (item.getItemId()) {
		case REMOVE:
			if(addingNew){
				cancelAdd();
			}else{
				removeItem(selectedItem);
			}
			return true;
			
		case ADD_NEW:
			addItem();
			return true;
		}
    	
    	return false;
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	super.onContextItemSelected(item);
    	
    	if(item.getItemId() == REMOVE) {
    		AdapterView.AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
    		removeItem(menuInfo.position);
    		return true;
    	}
    	
    	return false;
    }
    
    private void addItem() {
    	addingNew = true;
    	myEditText.setVisibility(View.VISIBLE);
    	myEditText.requestFocus();
	}

	private void cancelAdd() {
		addingNew = false;
		myEditText.setVisibility(View.GONE);
	}

	private void removeItem(int item) {
		todoItems.remove(item);
		arrayAdapter.notifyDataSetChanged();
	}
}








