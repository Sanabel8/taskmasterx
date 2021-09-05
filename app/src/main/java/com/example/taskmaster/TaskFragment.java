package com.example.taskmaster;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
/*
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 ="titleTask";
    private static final String ARG_PARAM2  ="bodyTask";
    private static final String ARG_PARAM3 ="stateTask";

    // TODO: Rename and change types of parameters
    private String mTaskTitle;
    private String mTaskBody;
    private String mTaskState;

    public TaskFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mStudentName Parameter 1.
     * @param mSchoolYear Parameter 2.
     * @return A new instance of fragment StudentItemFragment.
     */
    // TODO: Rename and change types and number of  parameters

public static TaskFragment newInstance(String mTaskTitle, String mTaskBody ,String mTaskState) {
    TaskFragment fragment = new TaskFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, mTaskTitle);
    args.putString(ARG_PARAM2, mTaskBody);
    args.putString(ARG_PARAM3, mTaskState);
    fragment.setArguments(args);
    return fragment;
}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskTitle = getArguments().getString(ARG_PARAM1);
            mTaskBody = getArguments().getString(ARG_PARAM2);
            mTaskState = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_item, container, false);
    }

}
