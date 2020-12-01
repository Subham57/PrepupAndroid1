package com.subham.prepupfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity {

    private EditText fname;
    private EditText mname;
    private EditText lname;
    private EditText phone;
    private EditText email;
    private EditText inst;
    private Button btnUpdate;

    LoginResponse loginResponse = new LoginResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("userDetails");

        fname = findViewById(R.id.ed_fname);
        mname = findViewById(R.id.ed_mname);
        lname = findViewById(R.id.ed_lname);
        phone = findViewById(R.id.ed_phnumber);
        email = findViewById(R.id.ed_email);
        inst =  findViewById(R.id.ed_inst);
        btnUpdate = findViewById(R.id.btnu_update);


        String f_name = loginResponse.userDetails.getFname();
        String m_name = loginResponse.userDetails.getMname();
        String l_name = loginResponse.userDetails.getLname();
        long phonenu = loginResponse.userDetails.getPhnumber();
        String emailid = loginResponse.userDetails.getEmailid();
        String insti = loginResponse.userDetails.getInstname();
        fname.setText(f_name);
        mname.setText(m_name);
        lname.setText(l_name);
        phone.setText(String.valueOf(phonenu));
        email.setText(emailid);
        inst.setText(insti);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(fname.getText().toString())||
                        TextUtils.isEmpty(mname.getText().toString())||
                        TextUtils.isEmpty(lname.getText().toString())||
                        TextUtils.isEmpty(phone.getText().toString())||
                        TextUtils.isEmpty(email.getText().toString())||
                        TextUtils.isEmpty(inst.getText().toString())){
                    String messege = "All Inputs Required";
                    Toast.makeText(UpdateProfile.this, messege,Toast.LENGTH_LONG).show();
                }else{
                    UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
                    updateProfileRequest.setFname(fname.getText().toString());
                    updateProfileRequest.setMname(mname.getText().toString());
                    updateProfileRequest.setLname(lname.getText().toString());
                    updateProfileRequest.setPhone(Long.parseLong(phone.getText().toString()));
                    updateProfileRequest.setEmail(email.getText().toString());
                    updateProfileRequest.setInstitute(inst.getText().toString());
                    Update(updateProfileRequest);
                }
            }
        });


    }

    public void Update(UpdateProfileRequest updateProfileRequest){
        HashMap<String,Object> params = new HashMap<>();
        params.put("fname",updateProfileRequest.getFname());
        params.put("mname",updateProfileRequest.getMname());
        params.put("lname",updateProfileRequest.getLname());
        params.put("phone",updateProfileRequest.getPhone());
        params.put("email",updateProfileRequest.getEmail());
        params.put("institute",updateProfileRequest.getInstitute());
        UserService userService = ApiClient.getRetrofit().create(UserService.class);
        Call<LoginResponse> UpdateResponseCall = userService.UpdateUser(params);
        UpdateResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()){
                    loginResponse = response.body();
                    if(loginResponse.status_code == 200){
//                        String messege ="Update Sucessfully";
//                        Toast.makeText(UpdateProfile.this, messege, Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(UpdateProfile.this,StudentProfile.class));
                        Intent intent = new Intent(UpdateProfile.this,StudentProfile.class);
                        intent.putExtra("userDetails",loginResponse);
                        startActivity(intent);
                    }else{
                        String messege = "Something went wrong. Please try again";
                        Toast.makeText(UpdateProfile.this, messege, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String messege = t.getLocalizedMessage();
                Toast.makeText(UpdateProfile.this, messege, Toast.LENGTH_SHORT).show();
            }
        });
    }
}