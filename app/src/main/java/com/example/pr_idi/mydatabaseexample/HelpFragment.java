package com.example.pr_idi.mydatabaseexample;

/**
 * Created by josue on 6/01/17.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class HelpFragment extends DialogFragment{

    static HelpFragment newInstance(String title, String message){
        HelpFragment fragment = new HelpFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("message",message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        String title = getArguments().getString("title");
        final String message = getArguments().getString("message");
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
    }
}

