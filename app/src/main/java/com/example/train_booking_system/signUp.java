package com.example.train_booking_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class signUp extends AppCompatActivity {

    private EditText name,email,pass,confpass;
    private Button signup;
    private TextView login;

    private String user="User";

    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intialze();
        movelogin();

        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
    }

    public void Intialze(){
        name=findViewById(R.id.nameET);
        email=findViewById(R.id.userET);

        pass=findViewById(R.id.passET);
        confpass=findViewById(R.id.confpassET);

        signup=findViewById(R.id.singupBTN);
        login=findViewById(R.id.loginTV);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });
    }

    public void registeruser(){
        if(name.getText().toString().isEmpty()&&email.getText().toString().isEmpty()
                &&pass.getText().toString().isEmpty()&&confpass.getText().toString().isEmpty()){
            Toast.makeText(this,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
        }
        else {
            if (pass.getText().toString().equals(confpass.getText().toString())){
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                 registeruser registeruser= new registeruser(
                 name.getText().toString(),email.getText().toString(),
                 pass.getText().toString());
                 FirebaseDatabase.getInstance().getReference(user).child(
                 FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(registeruser)
                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                 if (task.isSuccessful()) {
                      Toast.makeText(signUp.this, "Your Account Created", Toast.LENGTH_SHORT).show();
                      firebaseAuth.signOut();
                      Intent intent = new Intent(signUp.this, MainActivity.class);
                      startActivity(intent);
                      }
                 else {
                       Toast.makeText(signUp.this, "Error"+task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                     }
                 });
                 }
                 else {

                      Toast.makeText(signUp.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                 }}

                        });}
            else {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void movelogin(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(signUp.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
