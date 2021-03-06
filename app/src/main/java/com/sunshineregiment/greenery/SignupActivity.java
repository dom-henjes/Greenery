package com.sunshineregiment.greenery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Lets user register for new account - unfinished
 */

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);
    }

    /**
     * can either register (which takes you to main app functionality) or switch to login page
     */
    public boolean onClick(View view){
        int id = view.getId();
        if (id == R.id.btn_signup){
            Intent myIntent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.link_login) {
            Intent myIntent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(myIntent);
        }
        return true;
    }
}
