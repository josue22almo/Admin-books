package com.example.pr_idi.mydatabaseexample;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends ActionBarActivity
implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private BookData bookData;
    List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
        initializeActionBar();
        initializeData();
        addBook();
        initializeNavigationView();
        books = bookData.getAllBooks();
        initializeRecycleView(books);
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
        // Define the listener
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                initializeRecycleView(bookData.getAllBooks());
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        };

        // Get the MenuItem for the action item
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        // Assign the listener to that action item
        MenuItemCompat.setOnActionExpandListener(searchMenuItem,expandListener);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        SearchView  searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                initializeRecycleView(getBooks(query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                initializeRecycleView(getBooks(query));
                return true;
            }
        });
        return true;
    }

    private List<Book> getBooks(String query) {
        List <Book> result = new ArrayList<>();
        for (Book book : books){
            String title = book.getTitle();
            if (title.contains(query) || title.toLowerCase().contains(query)) result.add(book);
        }
        return  result;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_about:
                startActivity(new Intent("android.intent.action.aboutActivity"));
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


    public void initializeActionBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    public void addBook(){
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent("android.intent.action.addActivity"),Variables.request_code);
            }
        });
    }

    public void deleteBook(long id){
        bookData.deleteBook(id);
        books = bookData.getAllBooks();
        initializeRecycleView(books);
    }

    public void initializeNavigationView(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializeRecycleView(List<Book> books) {
        RecyclerView rv = (RecyclerView)findViewById(R.id.recycle);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        RVAdapter adapter = new RVAdapter(books,getFragmentManager());
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
        books = bookData.getAllBooks();
        initializeRecycleView(books);
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
        books = bookData.getAllBooks();
        initializeRecycleView(books);
        Toast.makeText(getBaseContext(),getResources().getText(R.string.book_added),Toast.LENGTH_LONG).show();
    }
}
