package com.example.phonedialerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.editnumber);
        btn = findViewById(R.id.dialButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checking if the permission is present or not
                Toast.makeText(MainActivity.this, "HII", Toast.LENGTH_SHORT).show();

                //these two parameters are for checking whether CALL_PHONE(From Manifest.permissions file) permission is present in this main activity or not.
                int perm = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                if (perm == PackageManager.PERMISSION_GRANTED)
                    callNumber();
                else
                    //else ask the user to give the permission
                    //in parameters we can ask user for multiple permission from this activity and in last some random integer
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.CALL_PHONE }, 121);

            }
        });
    }
        void callNumber(){
            String telNo = number.getText().toString();
            Uri uri = Uri.parse("tel:"+telNo);
            Intent intent = new Intent(Intent.ACTION_CALL, uri);  //Implicit intent
            startActivity(intent);
        }
}