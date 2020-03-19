package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnsave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtgetdata;
    private Button btngetalldata;
    private String allkickboxers;
    private Button btnNextActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsave=findViewById(R.id.btnsave);
        edtName=findViewById(R.id.edtName);
        edtPunchSpeed=findViewById(R.id.edtPunchSpeed);
        edtPunchPower=findViewById(R.id.edtPunchPower);
        edtKickSpeed=findViewById(R.id.edtKickSpeed);
        edtKickPower=findViewById(R.id.edtKickPower);
        btngetalldata=findViewById(R.id.btngetalldata);
        btnNextActivity=findViewById(R.id.btnNextActivity);



        txtgetdata=findViewById(R.id.txtgetdata);

        txtgetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("yRN3G72n9n", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object!=null && e==null){

                            txtgetdata.setText(object.get("name")+"-"+"Punch Power: "+object.get("Punchpower"));
                        }
                    }
                });

            }
        });

        //btnsave.setOnClickListener(SignUp.this);
        btngetalldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allkickboxers="";

                ParseQuery<ParseObject> queryall=ParseQuery.getQuery("KickBoxer");

               //queryall.whereGreaterThanOrEqualTo("Kick_Power",400);

                queryall.findInBackground(new FindCallback<ParseObject>() { // calling all objects at once
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null){
                            if(objects.size()>0){
                                for(ParseObject Kickboxer: objects){
                                    allkickboxers=allkickboxers + Kickboxer.get("name")+"\n";

                                }

                                FancyToast.makeText(SignUp.this, allkickboxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            }

                            else{

                                FancyToast.makeText(SignUp.this,e.getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                            }

                        }

                    }
                });

            }
        });

        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        final ParseObject Kickboxer= new ParseObject("KickBoxer");
        Kickboxer.put("name",edtName.getText().toString());
        Kickboxer.put("Punch_Speed",Integer.parseInt(edtPunchSpeed.getText().toString()));
        Kickboxer.put("Punch_Power",Integer.parseInt(edtPunchPower.getText().toString()));
        Kickboxer.put("Kick_Speed",Integer.parseInt(edtKickSpeed.getText().toString()));
        Kickboxer.put("kick_Power",Integer.parseInt(edtKickPower.getText().toString()));
        Kickboxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e){

                if(e==null) {
                    FancyToast.makeText(SignUp.this, Kickboxer.get("name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
//                    Toast.makeText(SignUp.this,Kickboxer.get("name")+" is saved successfully",Toast.LENGTH_LONG).show();
                }
                else{

                    FancyToast.makeText(SignUp.this,"Some Error Has Occured",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
//                    Toast.makeText(SignUp.this,"Some Exception has occured",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    //        ParseObject boxer=new ParseObject("Boxer");
//        boxer.put("Punch_Speed",520);
//        boxer.put("Punch_Power",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if(e==null){
//                    Toast.makeText(SignUp.this,"boxer object is saved successfully",Toast.LENGTH_LONG).show();
//                }
//            }
//        });

    }

