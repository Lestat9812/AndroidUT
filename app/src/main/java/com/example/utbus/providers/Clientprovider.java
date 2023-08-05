package com.example.utbus.providers;

import com.example.utbus.models.Client;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Clientprovider {

    DatabaseReference mDatabase;
    public Clientprovider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
    }

    public Task<Void> create(Client client){
        return mDatabase.child(client.getId()).setValue(client);

    }
}
