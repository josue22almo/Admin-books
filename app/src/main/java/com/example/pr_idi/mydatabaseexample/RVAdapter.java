package com.example.pr_idi.mydatabaseexample;

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

import java.util.List;
import java.util.Random;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BookViewHolder>{

    List<Book> books;

    public RVAdapter(List<Book> books){
        this.books = books;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView bookName;
        TextView bookAge;
        ImageView bookPhoto;
        TextView letter;
        TypedArray cercles;

        BookViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            bookName = (TextView)itemView.findViewById(R.id.book_name);
            bookAge = (TextView)itemView.findViewById(R.id.book_age);
            bookPhoto = (ImageView)itemView.findViewById(R.id.book_photo);
            letter = (TextView) itemView.findViewById(R.id.book_photoText);
            //cercles = itemView.getResources().getIntArray(R.array.circles);
            cercles = itemView.getResources().obtainTypedArray(R.array.cercles);
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
        bookViewHolder.bookName.setText(books.get(i).getTitle());
        bookViewHolder.bookAge.setText(books.get(i).getAuthor());
        bookViewHolder.letter.setText("" + books.get(i).getTitle().charAt(0));

        int randCercle = bookViewHolder.cercles.getResourceId(new Random().nextInt(10),-1);
        //Drawable draw = itemView.getResources().getDrawable(randCercle);
        //GradientDrawable bgShape = (GradientDrawable)bookPhoto.getBackground();
        bookViewHolder.bookPhoto.setImageResource(randCercle);
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
