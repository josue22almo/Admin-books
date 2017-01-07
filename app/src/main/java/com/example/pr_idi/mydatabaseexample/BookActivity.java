package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity{

    private long id;
    private String title;
    private String author;
    private int year;
    private String publisher;
    private String category;
    private String evaluation;
    private List<String> list;

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
        int i = item.getItemId();
       switch (i){
           case R.id.action_edit:
               Toast.makeText(getBaseContext(),"Edit", Toast.LENGTH_LONG).show();
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
        list = new ArrayList<>();
        setTitle(title + " - " + author);
        list.add("Title: " + title); list.add("Author: " + author); list.add("Year: " + Integer.toString(year));
        list.add("Publisher: " + publisher); list.add("Categroy: " + category); list.add("Evaluation: " + evaluation);
        ListView listView = (ListView)findViewById(R.id.book_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}
