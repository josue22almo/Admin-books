package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BookData bookData;
    List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeActionBar();
        initializeData();
        addBook();
        initializeNavigationView();
        initializeRecycleView();

       // getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Life cycle methods.
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
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_delete:
                bookData = new BookData(this);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        bookData.open();
        initializeNavigationView();
        super.onResume();
    }

    @Override
    protected void onPause() {
        bookData.close();
        super.onPause();
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

    private void initializeData(){
        bookData = new BookData(this);
        books = bookData.getAllBooks();
    }

    private void addBook(){
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent("android.intent.action.addActivity"),Variables.request_code);
            }
        });
    }

    private void initializeNavigationView(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializeRecycleView() {
        books = bookData.getAllBooks();
        RecyclerView rv = (RecyclerView)findViewById(R.id.recycle);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        RVAdapter adapter = new RVAdapter(books);
        rv.setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        switch (requestCode){
            case Variables.request_code:
                switch (resultCode){
                    case RESULT_OK:
                        activity_for_add(intent);
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this,"Cancel",Toast.LENGTH_LONG).show();
                }
            case Variables.request_code_book:
                switch(resultCode){
                    case Variables.DELETE:
                        activity_for_delete(intent);
                        break;
                }
        }
    }

    private void activity_for_delete(Intent intent) {
        Book book = new Book(intent.getLongExtra(Variables.ID,-1));
        bookData.deleteBook(book);
        initializeRecycleView();
        Toast.makeText(getBaseContext(),"Delete",Toast.LENGTH_LONG).show();
    }

    private void activity_for_add(Intent intent){
        String title = intent.getStringExtra(Variables.TITLE);
        String author = intent.getStringExtra(Variables.AUTHOR);
        int year = intent.getIntExtra(Variables.YEAR,-1);
        String publisher = intent.getStringExtra(Variables.PUBLISHER);
        String category = intent.getStringExtra(Variables.CATEGORY);
        String evaluation = intent.getStringExtra(Variables.EVALUATION);
        Book newB = bookData.createBook(title,author,year,publisher,category,evaluation);
        initializeRecycleView();
        Toast.makeText(getBaseContext(),getResources().getText(R.string.book_added),Toast.LENGTH_LONG).show();
    }
}
