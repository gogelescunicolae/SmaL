package com.example.fragmentapp.past;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentapp.R;

public class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTv;
    private TextView firstnameTv;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews();
    }

    private void initializeViews()
    {
        nameTv = itemView.findViewById(R.id.tv_row_example_name);
        firstnameTv = itemView.findViewById(R.id.tv_row_example_firstname);

    }

    public void setValues(String name, String firstname, int age){
        nameTv.setText(name);
        firstnameTv.setText(firstname + "    " +age);


    }

}
