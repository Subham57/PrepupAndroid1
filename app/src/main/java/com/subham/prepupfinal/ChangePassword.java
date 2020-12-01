package com.subham.prepupfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    private TextView emailId;
    private EditText oldpass;
    private EditText newpass;
    private EditText conpass;
    private Button btnChange;


    LoginResponse loginResponse = new LoginResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("userDetails");
        emailId = findViewById(R.id.tv_emailC);
        oldpass = findViewById(R.id.ed_oldC);
        newpass = findViewById(R.id.ed_newC);
        conpass = findViewById(R.id.ed_connewC);
        btnChange = findViewById(R.id.btnC_update);
        emailId.setText(loginResponse.userDetails.getEmailid());



        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(oldpass.getText().toString())||
                        TextUtils.isEmpty(newpass.getText().toString())||
                        TextUtils.isEmpty(conpass.getText().toString())){
                    String messege = "All Inputs Required";
                    Toast.makeText(ChangePassword.this, messege,Toast.LENGTH_LONG).show();
                }else if (newpass.getText().toString().equals(conpass.getText().toString())){
                    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
                    changePasswordRequest.setEmailId(emailId.getText().toString());
                    changePasswordRequest.setOldpass(oldpass.getText().toString());
                    changePasswordRequest.setNewpass(newpass.getText().toString());
                    Change(changePasswordRequest);
                }else{
                    String messege = "New Password is not match with conform password please cheack before clicking the button";
                    Toast.makeText(ChangePassword.this, messege,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Change(ChangePasswordRequest changePasswordRequest){
        HashMap<String,Object> params = new HashMap<>();
        params.put("emaiId",changePasswordRequest.getEmailId());
        params.put("oldPass",changePasswordRequest.getOldpass());
        params.put("newPass",changePasswordRequest.getNewpass());
        UserService userService = ApiClient.getRetrofit().create(UserService.class);
        Call<ChangePasswordResponse> changePasswordResponseCall = userService.ChangePassword(params);
        changePasswordResponseCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
                if (response.isSuccessful()){
                    changePasswordResponse = response.body();
                    if(changePasswordResponse.code == 200){
                        String messege = "Password Update Sucessfully";
                        Toast.makeText(ChangePassword.this, messege, Toast.LENGTH_SHORT).show();
                    }else{
                        String messege = "Old Password is Incorrect";
                        Toast.makeText(ChangePassword.this, messege, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                String messege = t.getLocalizedMessage();
                Toast.makeText(ChangePassword.this, messege, Toast.LENGTH_SHORT).show();
            }
        });
    }
}