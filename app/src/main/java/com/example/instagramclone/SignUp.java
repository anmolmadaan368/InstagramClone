package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtenteremail,edtusername,edtpassword;
    private Button btnSignUp,btnlogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Sign Up");

        edtenteremail=findViewById(R.id.edtenteremail);
        edtusername=findViewById(R.id.edtusername);
        edtpassword=findViewById(R.id.edtpassword);

       edtpassword.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View view, int keyCode, KeyEvent event) {

               if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                   onClick(btnSignUp);
               }
               return false;
           }
       });

        btnlogin=findViewById(R.id.btnlogin);
        btnSignUp=findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
        btnlogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSignUp:

                if(edtenteremail.getText().toString().equals("")|| edtusername.getText().toString().equals("")|| edtpassword.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this,"Email,  Username , Password is required" ,FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }
                else {
                    final ParseUser appuser = new ParseUser();
                    appuser.setEmail(edtenteremail.getText().toString());
                    appuser.setUsername(edtusername.getText().toString());
                    appuser.setPassword(edtpassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up" + edtusername.getText().toString());
                    progressDialog.show();


                    appuser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                FancyToast.makeText(SignUp.this, appuser.getUsername() + " is signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {

                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();

                        }
                    });
                }

                break;
            case R.id.btnlogin:

                Intent intent=new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);

                break;

        }



    }

    public void rootelementisclicked(View view){
        try{
        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);}
         catch (Exception e){
            e.printStackTrace();
         }



    }
}

