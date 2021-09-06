package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            itemView.findViewById(R.id.goToDetailesPage).setOnClickListener(View->{
                Intent goToDetaiels = new Intent(View.getContext(), TaskDetailPage.class);
                goToDetaiels.putExtra("TaskName" , task.getTitle());
                goToDetaiels.putExtra("TaskBody" , task.getBody());
                goToDetaiels.putExtra("TaskState" , task.getState());
                View.getContext().startActivity(goToDetaiels);
            });
//            itemView.findViewById(R.id.goToDetailesPage).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent goToDetailPageActivity = new Intent(view.getContext(),TaskDetailPage.class);
//                    goToDetailPageActivity.putExtra("taskName",task.title);
//                    view.getContext().startActivity(goToDetailPageActivity);
//                }
//            });


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
//        TextView bodyTask = holder.itemView.findViewById(R.id.textBody);
//        TextView stateTask = holder.itemView.findViewById(R.id.textState);

        titleTask.setText(holder.task.getTitle());
//        bodyTask.setText(holder.task.getBody());
//        stateTask.setText(holder.task.getState());

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }


}
