package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;


public class AddActivity extends AppCompatActivity{

    private Spinner category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
        initializeActionBar();
        setUpButton();
        floatingButtons();
        setHint2Spinners();
    }


    private void setHint2Spinners(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == 0) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(0)); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount(); // you dont display last item. It is used as hint.
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String categories[] = getResources().getStringArray(R.array.categories);
        Arrays.sort(categories,1,categories.length);
        adapter.addAll(categories);
        category = (Spinner) findViewById(R.id.spinner);
        category.setAdapter(adapter);
    }

    private void setUpButton() {
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeActionBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void floatingButtons(){
            acceptButton();
            denyButton();
    }

    private void acceptButton() {
        FloatingActionButton accept = (FloatingActionButton)findViewById(R.id.accept_button);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finding widgets
                EditText title = (EditText) findViewById(R.id.titleText);
                EditText author = (EditText) findViewById(R.id.authorText);
                EditText year = (EditText) findViewById(R.id.yearText);
                EditText publisher = (EditText) findViewById(R.id.publisherText);
                RatingBar evaluation = (RatingBar)findViewById(R.id.evalRat);
                //taking values from the widgets
                String ti = title.getText().toString();
                String at = author.getText().toString();
                String t = year.getText().toString();
                int yr = (t.length() > 0 ? Integer.parseInt(t) : -1);
                String publi = publisher.getText().toString();
                String cat = category.getSelectedItem().toString();
                String[] evaluations = getResources().getStringArray(R.array.evaluation);
                float rating = evaluation.getRating() - 1.0f;
                String eval = (rating != -1 ? evaluations[(int)rating] : "You don't have personal evaluation for this book.");
                if(ti.length() == 0 || at.length() == 0 || yr == -1 || publi.length() == 0 || cat.equals(getResources().getStringArray(R.array.categories)[0]))
                    Snackbar.make(v,R.string.noContinue, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                else{
                    Intent intent = new Intent();
                    intent.putExtra(Variables.TITLE,ti);
                    intent.putExtra(Variables.AUTHOR,at);
                    intent.putExtra(Variables.YEAR,yr);
                    intent.putExtra(Variables.PUBLISHER,publi);
                    intent.putExtra(Variables.CATEGORY,cat);
                    intent.putExtra(Variables.EVALUATION,eval);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private void denyButton() {
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
