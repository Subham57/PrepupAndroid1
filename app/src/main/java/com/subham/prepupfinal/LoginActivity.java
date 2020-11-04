package com.subham.prepupfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity<userServiceApi> extends AppCompatActivity {

    private EditText etusername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getSupportActionBar().hide();

        btnLogin = findViewById(R.id.btnLogin);
        etusername = findViewById(R.id.etusername);
        etPassword = findViewById(R.id.etPassword);
        tvSignin = findViewById(R.id.tv_signin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etusername.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
                    String messege = "All Inputs Required";
                    Toast.makeText(LoginActivity.this, messege,Toast.LENGTH_LONG).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(etusername.getText().toString());
                    loginRequest.setPassword(etPassword.getText().toString());
                    LoginUser(loginRequest);
                }
            }
        });

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signup();
            }
        });

    }

    public void LoginUser(LoginRequest loginRequest){
        HashMap<String,Object> params = new HashMap<>();
        params.put("email",loginRequest.getEmail());
        params.put("password",loginRequest.getPassword());
        UserService userService = ApiClient.getRetrofit().create(UserService.class);
        Call<LoginResponse> loginResponseCall = userService.LoginUser(params);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(response.isSuccessful()){
                    loginResponse = response.body();
                    if(loginResponse.status_code == 200){
                        String role = loginResponse.userDetails.getRole();
//                        System.out.println(role);
                        if(role.equals("Student")) {
                            Intent intent =  new Intent(LoginActivity.this,DeshboardStudent.class);
                            intent.putExtra("userDetails",loginResponse);
                            startActivity(intent);
                        }else{
                            startActivity(new Intent(LoginActivity.this, DeshboardTeacher.class));
                        }
                    }else{
                        String messege = "Invalid Credentials";
                        Toast.makeText(LoginActivity.this, messege ,Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String messege = "An Error occurred please try again later ";
                    Toast.makeText(LoginActivity.this, messege, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String messege = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, messege, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Signup(){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);

    }
}