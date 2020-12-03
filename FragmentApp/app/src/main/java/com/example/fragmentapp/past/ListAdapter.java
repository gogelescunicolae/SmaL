package com.example.fragmentapp.past;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentapp.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private List<DataM> exampleList;
    private Context context;

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
       LayoutInflater inflater = LayoutInflater.from(context);
       View contactView = inflater.inflate(R.layout.row_example_list, parent, false);
       ListViewHolder viewHolder = new ListViewHolder(contactView);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final DataM listModel = exampleList.get(position);
        holder.setValues(listModel.getFirstname(), listModel.getName(), listModel.getAge());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //ce se intampla la click pe rand
            }
        });
    }
    public ListAdapter(List<DataM> exampleList) {
        this.exampleList = exampleList;
    }
    @Override
    public int getItemCount() {

        return exampleList.size();
    }

    public interface OnItemClickListener {

        void onItemClick(int position);

    }
}
