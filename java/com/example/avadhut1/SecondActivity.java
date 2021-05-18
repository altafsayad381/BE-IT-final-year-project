package com.example.avadhut1;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class SecondActivity extends AppCompatActivity {

    public Button button;
    public Button dp;
    private FirebaseAuth firebaseAuth;
    public Button Logout;
    private Button Chat;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Chat = findViewById(R.id.btnchat);
        setContentView(R.layout.activity_second);
        firebaseAuth = FirebaseAuth.getInstance();
        Logout = findViewById(R.id.btnlogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
        addListenerOnButton();
    }

    private void Logout() {
        firebaseAuth.signOut();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                Logout();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnButton() {

        final Context context = this;
        Chat = findViewById(R.id.btnchat);
        Chat.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
            startActivity(intent);
        });


       dp= findViewById(R.id.dietp);
       dp.setOnClickListener(view -> {
           Intent i = new Intent(getApplicationContext(), selectdiet.class);
           startActivity(i);
       });
    }

    public void calculateBMI(View view) {
        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
        startActivity(intent);
    }

}