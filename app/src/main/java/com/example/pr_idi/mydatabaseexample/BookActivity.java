package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BookActivity extends AppCompatActivity {

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
        setTitle(title);
    }

    private void initializeToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getExtras() {
        Intent intent = getIntent();
        title = intent.getStringExtra(Variables.TITLE);
        author = intent.getStringExtra(Variables.AUTHOR);
        year = intent.getIntExtra(Variables.YEAR,-1);
        publisher = intent.getStringExtra(Variables.PUBLISHER);
        category = intent.getStringExtra(Variables.CATEGORY);
        evaluation = intent.getStringExtra(Variables.EVALUATION);
    }
}
