package com.omurgun.registerwithphonenumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Toolbar toolbar;
    private TextView username;
    private String name;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setSupportActionBar(toolbar);

        if(name == null)
        {
            username.setText("Welcome "+getName());
        }
        else
        {
            username.setText("Welcome "+name);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.sign_out)
        {
            firebaseAuth.signOut();
            goMain();
        }
        return super.onOptionsItemSelected(item);
    }
    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.mytoolbar);
        username = findViewById(R.id.username);
        sharedPreferences = this.getSharedPreferences("com.omurgun.storingdata", Context.MODE_PRIVATE);
        name = sharedPreferences.getString("storedUsername",null);
    }
    private void goMain() {
        Intent intentMain = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intentMain);
        finish();
    }
    private String getName() {
        String getName="NULL";
        if (getIntent().getExtras()!=null) {
            getName = getIntent().getExtras().getString("name");
            if (getName != null)
            {
                sharedPreferences.edit().putString("storedUsername",getName).apply();
                return  getName;
            }

        }

        return getName;
    }
}
