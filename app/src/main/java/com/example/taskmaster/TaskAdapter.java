package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    // create list to bind the data by adapter
    List<Task> allTasks = new ArrayList<Task>();


    //create constructor
    public TaskAdapter(List<Task> allTasks){
        this.allTasks = allTasks;
    }

    // 3- create the ViewHolder class (Wraps the data and the view)
    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }



//implemnt method
    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_item,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.task = allTasks.get(position);

//        TextView title = holder.itemView.findViewById(R.id.titleInFragment);
//        TextView body = holder.itemView.findViewById(R.id.bodyInFragment);
//        TextView state = holder.itemView.findViewById(R.id.stateInFragment);

//        title.setText(holder.task.title);
//        body.setText(Integer.toString(holder.task.body));
//        state.setText(Integer.toString(holder.task.state));

        Button taskButton = holder.itemView.findViewById(R.id.taskFragmentButton);
        taskButton.setText(holder.task.title);


    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }


}
