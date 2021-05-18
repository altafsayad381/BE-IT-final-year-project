package com.example.avadhut1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class selectdiet extends AppCompatActivity {
    public Button Anemia;
    public Button hypertension;
    public Button Lowbp;
    public Button Stone;
    public Button Thyroid;
    public Button Celiac;
    public Button Losewt;
    public Button Gainwt;
    public Button Renal;
    public Button Heartcare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectdiet);
        Anemia = (Button) findViewById(R.id.anemia);
        Anemia.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, anemiabtn.class);
            startActivity(intent);
        });

        hypertension = (Button) findViewById(R.id.hypertension);
        hypertension.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, hypertensionbtn.class);
            startActivity(intent);
        });

        Lowbp = (Button) findViewById(R.id.lowbp);
        Lowbp.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, lowbpbtn.class);
            startActivity(intent);
        });

        Stone = (Button) findViewById(R.id.stone);
        Stone.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, stonebtn.class);
            startActivity(intent);
        });

        Thyroid = (Button) findViewById(R.id.thyroid);
        Thyroid.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, thyroidbtn.class);
            startActivity(intent);
        });

        Celiac = (Button) findViewById(R.id.celiac);
        Celiac.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, celiacbtn.class);
            startActivity(intent);
        });

        Losewt = (Button) findViewById(R.id.losewt);
        Losewt.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, losewtbtn.class);
            startActivity(intent);
        });

        Gainwt = (Button) findViewById(R.id.gainwt);
        Gainwt.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, gainwtbtn.class);
            startActivity(intent);
        });

        Renal = (Button) findViewById(R.id.renal);
        Renal.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, renalbtn.class);
            startActivity(intent);
        });

        Heartcare = (Button) findViewById(R.id.heartcare);
        Heartcare.setOnClickListener(view -> {
            Intent intent = new Intent(selectdiet.this, heartcarebtn.class);
            startActivity(intent);
        });
    }

}
