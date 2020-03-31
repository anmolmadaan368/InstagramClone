package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginemail,edtLoginpassword;
    private Button btnloginAcitvity,btnsignupActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtLoginemail=findViewById(R.id.edtLoginemail);
        edtLoginpassword=findViewById(R.id.edtLoginpassword);
        btnloginAcitvity=findViewById(R.id.btnloginActivity);
        btnsignupActivity=findViewById(R.id.btnsignupActivity);

        btnloginAcitvity.setOnClickListener(this);
        btnsignupActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnloginActivity:


                ParseUser.logInInBackground(edtLoginemail.getText().toString(), edtLoginpassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user!=null&&e==null){
                            FancyToast.makeText(LoginActivity.this,user.get("username")+" is logged in successfully", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            transitiontoSocialMediaActivity();
                        }
                        else{
                            FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG).show();
                        }

                    }
                });

                break;

            case R.id.btnsignupActivity:
                Intent intent= new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
                break;
        }

    }
    public void transitiontoSocialMediaActivity(){
        Intent intent=new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
