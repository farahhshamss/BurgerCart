package com.prj.burgercart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final    OrdersDB or=new OrdersDB(this);
        final EditText UserName=(EditText) findViewById(R.id.username);
        final EditText Password=(EditText) findViewById(R.id.password);
        final EditText ConfirmPassword=(EditText) findViewById(R.id.RepasswordText);
        final EditText Address=(EditText) findViewById(R.id.AddressText);
        final EditText Email=(EditText) findViewById(R.id.EmailText);
        final EditText Phone=(EditText) findViewById(R.id.PhoneText);
        Button Register =(Button)  findViewById(R.id.registerBTN);
        Button Cancel =(Button)  findViewById(R.id.CancelBTN);
        final Pattern Pass=Pattern.compile("^"+
                "(?=.*[0-9])"+
                "(?=.*[a-z])"+
                "(?=.*[A-Z])"+
                "(?=.*[a-zA-Z])"+
                "(?=\\S+$)"+
                ".{6,}"+
                "$");
        or.getDatabaseName();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(UserName.getText().toString()))
                    UserName.setError("UserName can`t be null ");
                if (TextUtils.isEmpty(Password.getText().toString()))
                    Password.setError("Password can`t be null ");
                if (TextUtils.isEmpty(ConfirmPassword.getText().toString()))
                    ConfirmPassword.setError("Please Re-Enter your password ");
                if (TextUtils.isEmpty(Address.getText().toString()))
                    Address.setError("Address can`t be null ");
                if (TextUtils.isEmpty(Email.getText().toString()))
                    Email.setError("Email can`t be null ");
                if (TextUtils.isEmpty(Phone.getText().toString()))
                    Phone.setError("Phone can`t be null ");
                if (or.CheckingUserName(UserName.getText().toString()))
                    UserName.setError("This UserName is not available");
                if (or.CheckingPhone(Phone.getText().toString()))
                    Phone.setError("This Phone number is already registered");
                if (or.CheckingEmail(Email.getText().toString()))
                    Email.setError("This Email is already registered");
                if (!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString().trim()).matches())
                    Email.setError("Please Enter a valid Email");
                if(!Password.getText().toString().trim().equals(ConfirmPassword.getText().toString()))
                    ConfirmPassword.setError("Passwords do not match");
                if(!Pass.matcher((Password.getText().toString())).matches())
                    Password.setError("Password should contain at least 6 characters including at least( 1 lower case letter, 1 upper case letter, 1 digit ");
                if(!TextUtils.isEmpty(UserName.getText().toString())&&!TextUtils.isEmpty(Password.getText().toString())&&!TextUtils.isEmpty(ConfirmPassword.getText().toString())&&
                        !TextUtils.isEmpty(Address.getText().toString())&&!TextUtils.isEmpty(Email.getText().toString())&&!TextUtils.isEmpty(Phone.getText().toString())
                        &&!or.CheckingUserName(UserName.getText().toString())&&!or.CheckingPhone(Phone.getText().toString())&&!or.CheckingEmail(Email.getText().toString())&&Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString().trim()).matches()
                        &&Password.getText().toString().trim().equals(ConfirmPassword.getText().toString())&&Pass.matcher((Password.getText().toString())).matches()
                ) {
                    or.createNewUser(UserName.getText().toString(), Password.getText().toString(), Phone.getText().toString(), Email.getText().toString(), Address.getText().toString());
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                    Intent inn = new Intent(Register.this, Login.class);
                    finish();
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inn = new Intent(Register.this,Login.class);
                finish();
            }
        });
    }
}