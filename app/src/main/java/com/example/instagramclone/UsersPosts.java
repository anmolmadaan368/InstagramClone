package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UsersPosts extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_posts);

        linearLayout=findViewById(R.id.LinearLayout);

        Intent receivedIntentObject= getIntent();
        final  String recivedUserName= receivedIntentObject.getStringExtra("username");
        FancyToast.makeText(this,recivedUserName , Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();

        setTitle(recivedUserName+" 's Posts");

        ParseQuery<ParseObject>  parseQuery=new ParseQuery<ParseObject>("Photo");
        parseQuery.whereEqualTo("username",recivedUserName);
        parseQuery.orderByDescending("createdAt");

        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(objects.size()>0 && e==null){

                    for(ParseObject post:objects){

                        final TextView postDescription= new TextView(UsersPosts.this);
                        postDescription.setText(post.get("image_des")+" ");
                        ParseFile postPicture=(ParseFile)post.get("picture");
                        postPicture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {

                                if(data!=null && e==null){

                                    Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                    ImageView postImageView = new ImageView(UsersPosts.this);
                                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                    , ViewGroup.LayoutParams.WRAP_CONTENT);

                                    params.setMargins(5,5,5,5);
                                    postImageView.setLayoutParams(params);
                                    postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImageView.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams des_params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                                    ,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    des_params.setMargins(5,5,5,15);
                                    postDescription.setLayoutParams(des_params);
                                    postDescription.setGravity(Gravity.CENTER);
                                    postDescription.setBackgroundColor(Color.RED);
                                    postDescription.setTextColor(Color.WHITE);
                                    postDescription.setTextSize(30f);

                                    linearLayout.addView(postImageView);
                                    linearLayout.addView(postDescription);




                                }
                                else{
                                    FancyToast.makeText(UsersPosts.this,recivedUserName+" doesn't have any posts",Toast.LENGTH_SHORT,FancyToast.INFO,true).show();
                                    finish();
                                }

                            dialog.dismiss();}
                        });

                    }
                }
            }
        });

    }
}
