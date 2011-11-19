package com.example.android.todolist;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TodoItemAdapter extends ArrayAdapter<TodoItem>{
	
	int resource;

	public TodoItemAdapter(Context context, int resource, List<TodoItem> items) {
		super(context, resource, items);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout todoView;
		
		TodoItem item = getItem(position);
		
		String task = item.getTask();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(item.getDate());
		
		if(convertView == null){
			todoView = new LinearLayout(getContext());
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi.inflate(resource, todoView, true);
		}else {
			todoView = (LinearLayout) convertView;
		}
		
		TextView dateView = (TextView) todoView.findViewById(R.id.rowDate);
		TextView taskView = (TextView) todoView.findViewById(R.id.row);
		
		dateView.setText(date);
		taskView.setText(task);
		
		return todoView;
	}

}
