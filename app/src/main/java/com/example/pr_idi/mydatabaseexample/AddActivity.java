package com.example.pr_idi.mydatabaseexample;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.design.widget.Snackbar;
import android.content.Intent;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        FloatingActionButton accept = (FloatingActionButton)findViewById(R.id.accept_button);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    String t = year.getText().toString();
                    int yr = (t.length() > 0 ? Integer.parseInt(t) : -1);
                    String publi = publisher.getText().toString();
                    String cat = category.getSelectedItem().toString();
                    String eval = evaluation.getText().toString();
                    if(ti.length() == 0 || at.length() == 0)
                        Snackbar.make(v, R.string.noContinue, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    else{
                        Intent data = new Intent();
                        data.putExtra("title",ti);
                        data.putExtra("author",at);
                        data.putExtra("year",yr);
                        data.putExtra("publisher",publi);
                        data.putExtra("category",cat);
                        data.putExtra("evaluation",eval);
                        setResult(RESULT_OK,data);
                        finish();
                    }
                }
            });

        FloatingActionButton denny = (FloatingActionButton)findViewById(R.id.deny_button);
        denny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
