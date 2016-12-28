package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {

    private long id;
    private String title;
    private String author;
    private int year;
    private String publisher;
    private String category;
    private String evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        initializeToolbar();
        getExtras();
        setExtras();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int i = item.getItemId();

       switch (i){
           case R.id.action_about:
               break;
           case R.id.action_edit:
               Toast.makeText(getBaseContext(),"Edit",Toast.LENGTH_LONG).show();
               break;
           case R.id.action_delete:
               Intent data = new Intent();
               data.putExtra(Variables.ID,id);
               setResult(Variables.DELETE,data);
               finish();
       }

        return super.onOptionsItemSelected(item);
    }


    private void initializeToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getExtras() {
        Intent intent = getIntent();
        id = intent.getLongExtra(Variables.ID,-1);
        title = intent.getStringExtra(Variables.TITLE);
        author = intent.getStringExtra(Variables.AUTHOR);
        year = intent.getIntExtra(Variables.YEAR,-1);
        publisher = intent.getStringExtra(Variables.PUBLISHER);
        category = intent.getStringExtra(Variables.CATEGORY);
        evaluation = intent.getStringExtra(Variables.EVALUATION);
    }

    private void setExtras() {
        setTitle(title);
    }
}
