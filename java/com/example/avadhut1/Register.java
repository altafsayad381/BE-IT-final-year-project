package com.example.avadhut1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText Fullname;
    private EditText Email;
    private EditText Pass;
    private EditText Mobile;
    private EditText Address;
    private EditText Gender;
    private EditText Birth;
    private Button Register;
    private TextView Reglogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validate()) {
                    //upload the data to the database
                    String Email_id = Email.getText().toString().trim();
                    String Password = Pass.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(Email_id, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, " Registeration Successful ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, MainActivity.class));
                            } else {
                                Toast.makeText(Register.this, " Registeration Failed ", Toast.LENGTH_SHORT).show();
                            }

                        }


                    });
                }
            }
        });
        Reglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, MainActivity.class));

            }
        });
    }

    private void setupUIViews() {

        Fullname = findViewById(R.id.fullname);
        Email = findViewById(R.id.email_id);
        Pass = findViewById(R.id.password);
        Birth = findViewById(R.id.dob);
        Mobile = findViewById(R.id.mobile);
        Address = findViewById(R.id.address);
        Register = findViewById(R.id.submit);
        Reglogin = findViewById(R.id.reglogin);
    }

    private Boolean Validate() {
        Boolean result = false;
        String Name = Fullname.getText().toString();
        String Password = Pass.getText().toString();
        String Email_id = Email.getText().toString();
        String Mobile = Email.getText().toString();

        if (Name.isEmpty() || Password.isEmpty() || Email_id.isEmpty() || Mobile.isEmpty()) {
            Toast.makeText(this, "Please Enter All The Details ", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}