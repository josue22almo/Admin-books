package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
    }
}

