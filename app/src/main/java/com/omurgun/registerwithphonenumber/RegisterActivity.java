package com.omurgun.registerwithphonenumber;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class RegisterActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText txtPhoneNumber;
    private Button btnContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

    }

    private void init(){

        txtPhoneNumber = findViewById(R.id.phonenumbertxt);
        btnContinue = findViewById(R.id.btnContinue);
        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryCodes.countryNames));



    }
    private void createNewAccount() {

        System.out.println("spinner : "+spinner.getSelectedItemPosition());

        String code = CountryCodes.countryAreaCodes[spinner.getSelectedItemPosition()];
        String number = txtPhoneNumber.getText().toString().trim();

        if (number.isEmpty() || number.length() < 10) {
            txtPhoneNumber.setError("Valid number is required");
            txtPhoneNumber.requestFocus();
            return;
        }

        String phoneNumber = "+" +code +number;
        System.out.println("phone number : "+phoneNumber);
        goLogin(phoneNumber);


    }
    private void goLogin(String phoneNumber) {
        Intent intentLogin = new Intent(RegisterActivity.this, VerifyPhoneActivity.class);
        intentLogin.putExtra("phonenumber", phoneNumber);
        startActivity(intentLogin);

    }

}
