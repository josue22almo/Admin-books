package com.example.pr_idi.mydatabaseexample;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BookViewHolder>{

    List<Book> books;
    private final int request_Code = 1;
    TypedArray cercles;
    public RVAdapter(List<Book> books){
        this.books = books;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        //CardView cv;
        TextView bookTitle;
        TextView bookAuthor;
        ImageView bookPhoto;
        TextView letter;
        int cercle;
        View view;

        BookViewHolder(View itemView) {
            super(itemView);
            view = itemView;
           // cv = (CardView)itemView.findViewById(R.id.cv);
            bookTitle = (TextView)itemView.findViewById(R.id.book_title);
            bookAuthor = (TextView)itemView.findViewById(R.id.book_author);
            bookPhoto = (ImageView)itemView.findViewById(R.id.book_photo);
            letter = (TextView) itemView.findViewById(R.id.book_photoText);
            cercles = itemView.getResources().obtainTypedArray(R.array.cercles);
            cercle = cercles.getResourceId(new Random().nextInt(10),-1);
            bookPhoto.setImageResource(cercle);
        }
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        BookViewHolder pvh = new BookViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int i) {
        bookViewHolder.bookTitle.setText(books.get(i).getTitle());
        bookViewHolder.bookAuthor.setText(books.get(i).getAuthor());
        bookViewHolder.letter.setText("" + books.get(i).getTitle().charAt(0));

        final Context context = bookViewHolder.view.getContext();
        bookViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Clicked",Toast.LENGTH_LONG).show();
                context.startActivity(new Intent("android.intent.action.addActivity"));
            }
        });
        bookViewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(),"Long clicked",Toast.LENGTH_LONG).show();
                return true;
            }
        });


    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
