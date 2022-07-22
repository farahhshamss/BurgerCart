package com.prj.burgercart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.regex.Pattern;

public class EditUserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);
        final EditText thenewemail = (EditText) findViewById(R.id.emaileditText);
        final EditText thenewaddress = (EditText) findViewById(R.id.adressedittext);
        final String username = getIntent().getStringExtra("username");
        final String UserLoggedIn = getIntent().getStringExtra("UserLoggedIn");
        final OrdersDB userdata = new OrdersDB(this);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        final RadioButton r1 = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
        TextView UserType = (TextView) findViewById(R.id.textView3);
        final EditText oldpasstext = (EditText) findViewById(R.id.oldpass);
        if (userdata.GetUserType(UserLoggedIn).equals("NU")) {
            UserType.setVisibility(View.INVISIBLE);
            r1.setVisibility(View.INVISIBLE);
            r2.setVisibility(View.INVISIBLE);
            rg.setVisibility(View.INVISIBLE);
        } else {
            oldpasstext.setVisibility(View.INVISIBLE);
            if (userdata.GetUserType(username).equals("NU"))
                r1.setChecked(true);
            if (userdata.GetUserType(username).equals("AD"))
                r2.setChecked(true);


        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            int id = userdata.getUserId(username);

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == r1.getId())
                    userdata.UpdatingUser2(id);
                if (checkedId == r2.getId())
                    userdata.UpdatingUser(id);
            }
        });
        final String oldemail = userdata.GetEmail(username).toString();
        final String oldaddress = userdata.GetAdress(username).toString();
        final String oldpassword = userdata.GetPassword(username);
        final EditText newpasstext = (EditText) findViewById(R.id.newpasseditbox);
        final EditText confirmpasstext = (EditText) findViewById(R.id.confirmpass);
        thenewemail.setText(oldemail);
        thenewaddress.setText(oldaddress);
        Button savenewemail = (Button) findViewById(R.id.saveemailbutton);
        Button savenewaddress = (Button) findViewById(R.id.saveadressbutton);
        Button savepass = (Button) findViewById(R.id.savepassbutton);
        final Pattern Pass = Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[a-zA-Z])" +
                "(?=\\S+$)" +
                ".{6,}" +
                "$");
        savenewemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(thenewemail.getText().toString())) {
                    thenewemail.setError("Email can`t be null ");
                    return;
                }

                if (thenewemail.getText().toString().equals(oldemail)) {
                    thenewemail.setError("Enter New Email ");
                    return;
                }

                if (userdata.CheckingEmail(thenewemail.getText().toString())) {
                    thenewemail.setError("This Email is already registered");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(thenewemail.getText().toString().trim()).matches()) {
                    thenewemail.setError("Please Enter a valid Email");
                    return;
                }
                userdata.Updateuseremail(thenewemail.getText().toString(), username);
            }
        });
        savenewaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(thenewaddress.getText().toString())) {
                    thenewaddress.setError("adress can`t be null ");
                    return;
                }

                if (thenewaddress.getText().toString().equals(oldaddress)) {
                    thenewaddress.setError("Enter New Email ");
                    return;
                }

                userdata.Updateuseraddress(thenewemail.getText().toString(), username);
            }
        });
        savepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(oldpasstext.getText().toString())) {
                    oldpasstext.setError("Password can`t be null ");
                    return;
                }

                if (TextUtils.isEmpty(confirmpasstext.getText().toString())) {
                    confirmpasstext.setError("Please Re-Enter your password ");
                    return;
                }

                if (TextUtils.isEmpty(newpasstext.getText().toString())) {
                    newpasstext.setError("Password can`t be null ");
                    return;
                }
                if (userdata.GetUserType(UserLoggedIn).equals("NU")) {
                    if (!oldpasstext.getText().toString().equals(oldpassword)) {
                        oldpasstext.setError("wrong Password");
                        return;
                    }
                }

                if (!newpasstext.getText().toString().trim().equals(confirmpasstext.getText().toString())) {
                    confirmpasstext.setError("Passwords do not match");
                    return;
                }

                if (!Pass.matcher((newpasstext.getText().toString())).matches()) {
                    newpasstext.setError("Password should contain at least 6 characters including at least( 1 lower case letter, 1 upper case letter, 1 digit ");
                    return;
                }
                userdata.Updateuserpassword(newpasstext.getText().toString(), username);
            }
        });

    }
}