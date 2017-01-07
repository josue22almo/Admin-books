package com.example.pr_idi.mydatabaseexample;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initializeToolbar();
        initializeList();
    }

    private void initializeToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeList(){
        ListView list = (ListView) findViewById(R.id.about_list);
        ArrayList<AboutItem> items = new ArrayList<>();
        PackageManager manager = getBaseContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    getBaseContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;
        items.add(new AboutItem("Help","Find answers to your questions hear"));
        items.add(new AboutItem("App version",version));

        AboutAdapter adapter = new AboutAdapter(this,items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    HelpFragment fragment = HelpFragment.newInstance("Help",view.getResources().getString(R.string.help));
                    fragment.show(getFragmentManager(),"dialog");
                }
            }
        });
    }
}

