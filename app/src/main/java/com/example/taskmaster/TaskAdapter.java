package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    // create list to bind the data by adapter
//    List<Task> allTasks = new ArrayList<Task>();
    List<com.amplifyframework.datastore.generated.model.Task> allTasks = new ArrayList<com.amplifyframework.datastore.generated.model.Task>();


    //create constructor
    public TaskAdapter(List<com.amplifyframework.datastore.generated.model.Task> allTasks){
        this.allTasks = allTasks;
    }

    // 3- create the ViewHolder class (Wraps the data and the view)
    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.findViewById(R.id.goToDetailesPage).setOnClickListener(View->{
                Intent goToDetaiels = new Intent(View.getContext(), TaskDetailPage.class);
                goToDetaiels.putExtra("title" , task.getTitle());
                goToDetaiels.putExtra("body" , task.getBody());
                goToDetaiels.putExtra("state" , task.getState());
                goToDetaiels.putExtra("imgName" , task.getImgName());
                View.getContext().startActivity(goToDetaiels);
            });

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

        TextView titleTask = holder.itemView.findViewById(R.id.textTitle);
        titleTask.setText(holder.task.getTitle());

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }


}
