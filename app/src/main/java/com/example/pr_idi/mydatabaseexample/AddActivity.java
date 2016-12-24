package com.example.pr_idi.mydatabaseexample;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.design.widget.Snackbar;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
    }

    public void onClick(View v){
        int fab;
        switch (v.getId()){
            case R.id.accept_button:
                //finding widgets
                EditText title = (EditText) findViewById(R.id.titleText);
                EditText author = (EditText) findViewById(R.id.authorText);
                EditText year = (EditText) findViewById(R.id.yearText);
                EditText publisher = (EditText) findViewById(R.id.publisherText);
                Spinner category = (Spinner) findViewById(R.id.spinner);
                EditText evaluation = (EditText) findViewById(R.id.evalText);
                //taking values from the widgets
                String ti = title.getText().toString();
                String at = author.getText().toString();
                int yr = year.getBaseline();
                String publi = publisher.getText().toString();
                String cat = category.getSelectedItem().toString();
                String eval = evaluation.getText().toString();
                if(ti.length() == 0 || at.length() == 0)
                    Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                break;
            case R.id.deny_button:
                break;
        }

    }
}
