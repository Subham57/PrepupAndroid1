package com.subham.prepupfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StudentProfile extends AppCompatActivity {

    private TextView tvname;
    private TextView tvphone;
    private TextView tvemail;
    private TextView tvinst;
    private TextView tvrole;
    private Button btn_update;
    private Button btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);


        tvname = findViewById(R.id.tv_name);
        tvphone = findViewById(R.id.tv_phone);
        tvemail = findViewById(R.id.tv_email);
        tvinst = findViewById(R.id.tv_inst);
        tvrole = findViewById(R.id.tv_role);
        btn_update = findViewById(R.id.btn_update);
        btn_change = findViewById(R.id.btn_change);

        LoginResponse loginResponse = new LoginResponse();
        tvname.setText(loginResponse.userDetails.getFname());
        tvphone.setText((int) loginResponse.userDetails.getPhnumber());
        tvemail.setText(loginResponse.userDetails.getEmailid());
        tvinst.setText(loginResponse.userDetails.getInstname());
        tvrole.setText(loginResponse.userDetails.getRole());
    }
}