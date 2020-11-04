package com.subham.prepupfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class DeshboardStudent extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerlayout;
    private Toolbar mToolbar;

    LoginResponse loginResponse = new LoginResponse();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshboard_student);


//        LoginResponse loginResponse = (LoginResponse) getIntent().getSerializableExtra("userDetails");
        mToolbar = findViewById(R.id.st_toolbar);
        NavigationView mNavigationview = findViewById(R.id.navigation_view);
        mDrawerlayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(DeshboardStudent.this,mDrawerlayout,mToolbar,R.string.open,R.string.close);
        mDrawerToggle.syncState();
        mNavigationview.setNavigationItemSelectedListener(this);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("userDetails");
//        System.out.println(userDetails.getUserDetails().getEmailid());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerlayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()){
            case R.id.sd_profile:
//                startActivity(new Intent(DeshboardStudent.this,StudentProfile.class));
                Intent intent = new Intent(DeshboardStudent.this,StudentProfile.class);
                intent.putExtra("userDetails",loginResponse);
                break;
            case R.id.sd_home:
                startActivity(new Intent(DeshboardStudent.this,DeshboardStudent.class));
                break;
            case R.id.sd_class:
                startActivity(new Intent(DeshboardStudent.this,StudentClass.class));
                break;
            case R.id.sd_exam:
                startActivity(new Intent(DeshboardStudent.this,StudentExam.class));
                break;
            case R.id.sd_result:
                startActivity(new Intent(DeshboardStudent.this,StudentResult.class));
                break;
            case R.id.sd_logout:
                startActivity(new Intent(DeshboardStudent.this,LoginActivity.class));
                break;
        }

        return true;
    }
}