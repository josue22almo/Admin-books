package com.example.pr_idi.mydatabaseexample;

/**
 * Created by josue on 6/01/17.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DeleteFragment extends DialogFragment{

    static DeleteFragment newInstance(String title, Book book){
        DeleteFragment fragment = new DeleteFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putLong("id", book.getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        String title = getArguments().getString("title");
        final long id = getArguments().getLong("id");
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon)
                .setTitle(title)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((CategoryActivity)getActivity()).deleteBook(id);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();

    }
}
