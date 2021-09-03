package com.example.taskmaster;
// First step: create the model class
// Second: create the fragment (represents a single item)
// Create the adapter:
// Create the RecyclerView object in the desired activity , set the layout manager (linear layout manager), set the adapter

public class Task {
   private String title;
   private String body;
    private String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }


}
