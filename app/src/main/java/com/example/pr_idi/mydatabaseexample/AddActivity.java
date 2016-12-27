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


public class AddActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        //getActionBar().setHomeButtonEnabled(true);
        initializeActionBar();
        setUpButton();
        initializeNavigationView();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpButton() {
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeNavigationView(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_add);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializeActionBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
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


