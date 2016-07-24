package singularity.twodolist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

/*
            https://guides.codepath.com/android/using-the-recyclerview

            A LOT OF USEFUL INFORMATION HERE ^^^^
 */


// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private ArrayList<ToDoList> mToDoLists;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public ToDoListAdapter(Context context, ArrayList<ToDoList> toDoLists) {
        mToDoLists = toDoLists;
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
        View contactView = inflater.inflate(R.layout.item_list, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ToDoList toDoList = mToDoLists.get(position);

        // Set item views based on your views and data model
        TextView listNameTextView = viewHolder.listNameTextView;
        listNameTextView.setText(toDoList.get_list_name());
        listNameTextView.setTag(toDoList.get_list_self());

        RecyclerView rvTasks = viewHolder.taskRecyclerView;
        ArrayList<Task> tasks = toDoList.get_list_tasks();
        TaskListAdapter adapter = new TaskListAdapter(getContext(), tasks);
        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public int getItemCount() {
        return mToDoLists.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView listNameTextView;
        public ImageButton addNewListImageButton;
        public RecyclerView taskRecyclerView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            listNameTextView = (TextView) itemView.findViewById(R.id.list_name);
            addNewListImageButton = (ImageButton) itemView.findViewById(R.id.addNewList);
            taskRecyclerView = (RecyclerView) itemView.findViewById(R.id.tasks);

            addNewListImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // start newListItem process and use listNameTextView.getTag().toString()
                }
            });
        }
    }
}
