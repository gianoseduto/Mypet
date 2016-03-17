package com.cicciopasticcio.chiappette;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Handler;

public class ConActivity extends Activity{

    private EditText usernameField,passwordField;
    private TextView status,role;


    @Override
    protected void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);
        setContentView(R.layout.conactivity_main);

        usernameField = (EditText)findViewById(R.id.editText1);
        passwordField = (EditText)findViewById(R.id.editText2);

        status = (TextView)findViewById(R.id.textView6);
        role = (TextView)findViewById(R.id.textView7);

    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        new SigninActivity(this,status,role).execute(username, password);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (role.getText().toString().equals("CHIAPPONE")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000L);
    }
}
