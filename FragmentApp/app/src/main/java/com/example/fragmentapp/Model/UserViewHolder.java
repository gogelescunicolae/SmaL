package com.example.fragmentapp.Model;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentapp.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView email;
    private TextView password;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        initialize();
    }

    public void initialize()
    {
        //email=itemView.findViewById(R.id.emailList);
        //password=itemView.findViewById(R.id.passwordList);
    }

    public void setValue(String username,String email)
    {
        this.email.setText(email);
        this.password.setText(username);

        //this.user.setText(prenume);
        //this.btn_edit.setText(id);
    }

}
