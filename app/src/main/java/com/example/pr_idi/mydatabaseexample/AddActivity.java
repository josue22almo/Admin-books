package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        initializeActionBar();
        setUpButton();
        floatingButtons();
    }


    // Life cycle methods. Check whether it is necessary to reimplement them
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                if(ti.length() == 0 || at.length() == 0 || yr == -1 || publi.length() == 0)
                    Snackbar.make(v, R.string.noContinue, Snackbar.LENGTH_LONG)
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


