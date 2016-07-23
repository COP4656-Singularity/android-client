package singularity.twodolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

/*
            https://guides.codepath.com/android/using-the-recyclerview

            A LOT OF USEFUL INFORMATION HERE ^^^^
 */

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private ArrayList<Task> mTaskLists;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TaskListAdapter(Context context, ArrayList<Task> taskLists) {
        mTaskLists = taskLists;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_task, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Task taskList = mTaskLists.get(position);

        // Set item views based on your views and data model
        TextView nameTextView = viewHolder.nameTextView;
        nameTextView.setText(taskList.get_task_name());
    }

    @Override
    public int getItemCount() {
        int size;
        if (mTaskLists != null)
            size = mTaskLists.size();
        else {
            // The list item does not have any tasks
            size = 0;
        }
        return size;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.task_name);
        }
    }
}