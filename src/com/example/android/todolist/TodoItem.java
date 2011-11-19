package com.example.android.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	
	private String task;
	private Date date;
	
	public TodoItem(String task) {
		this.task = task;
		this.date = new Date(System.currentTimeMillis());
	}
	
	public TodoItem(String task, Date date) {
		this.task = task;
		this.date = date;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return "(" + sdf.format(getDate()) + ") "+ getTask();
	}

}
