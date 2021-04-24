package com.example.garfield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountDetailsActivity extends AppCompatActivity {

    Button done;
    EditText name;
    EditText phone;
    EditText address;
    EditText ec;
    EditText ecn;
    EditText doc;
    EditText docn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        done = findViewById(R.id.button_done);
        name = findViewById(R.id.NameText);
        phone = findViewById(R.id.PNText);
        address = findViewById(R.id.Adtext);
        ec = findViewById(R.id.ENameText);
        ecn = findViewById(R.id.ENCText);
        doc = findViewById(R.id.DNameText);
        docn = findViewById(R.id.DNText);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String strname = name.getText().toString();
              String strphone = phone.getText().toString();
              String straddress = address.getText().toString();
              String strec = ec.getText().toString();
              String strecn = ecn.getText().toString();
              String strdoc = doc.getText().toString();
              String strdocn = docn.getText().toString();
                PersonalInfo send = new
                        PersonalInfo(strname,strec,strecn,strphone,straddress,strdoc,strdocn);
                Log.i("SE",send.getDoctor());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("Key",send);
                startActivity(intent);
                finish();


            }
        });

    }
}