package com.example.train_booking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Adminpanel extends AppCompatActivity {

    EditText currnet,destination,quantity,price;
    Button add,logout;

    DatabaseReference fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);

        Intialize();
    }
    public void Intialize(){
        currnet=findViewById(R.id.currentET);
        destination=findViewById(R.id.destET);

        quantity=findViewById(R.id.quantityET);
        price=findViewById(R.id.priceET);

        add=findViewById(R.id.addBTN);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        logout=findViewById(R.id.logoutBTN);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Adminpanel.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        fd= FirebaseDatabase.getInstance().getReference("Ticket List");
    }
    public void upload(){
        try {
            if(currnet.getText().toString().isEmpty() &&destination.getText().toString().isEmpty() &&quantity.getText().toString().isEmpty() &&price.getText().toString().isEmpty() ){
                Toast.makeText(Adminpanel.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
            }

            else {
                HashMap map = new HashMap();
                map.put("current", currnet.getText().toString());
                map.put("destination", destination.getText().toString());

                map.put("quantity", quantity.getText().toString());
                map.put("price", price.getText().toString());
                fd.push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(Adminpanel.this, "Succesfully Uploaded", Toast.LENGTH_SHORT).show();
                        currnet.setText("");
                        destination.setText("");
                        quantity.setText("");
                        price.setText("");
                        currnet.requestFocus();
                    }
                });
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Unable To upload"+e,Toast.LENGTH_SHORT).show();
        }
    }
}