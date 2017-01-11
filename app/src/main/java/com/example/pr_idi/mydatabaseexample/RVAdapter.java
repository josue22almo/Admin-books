package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BookViewHolder>{

    List<Book> books;
    FragmentManager fragmentManager;
    TypedArray cercles;
    public RVAdapter(List<Book> books, FragmentManager fragmentManager){

        this.books = books;
        this.fragmentManager = fragmentManager;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        TextView bookAuthor;
        TextView letter;
        ImageView bookPhoto;
        int cercle;
        View view;

        BookViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            bookTitle = (TextView)itemView.findViewById(R.id.book_title);
            bookAuthor = (TextView)itemView.findViewById(R.id.book_author);
            letter = (TextView) itemView.findViewById(R.id.book_photoText);
            bookPhoto = (ImageView)itemView.findViewById(R.id.book_photo);
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
    public void onBindViewHolder(final BookViewHolder bookViewHolder, int i) {
        final Book book = books.get(i);
        //getting and setting in text views all atributes book
        bookViewHolder.bookTitle.setText(book.getTitle());
        bookViewHolder.bookAuthor.setText(book.getAuthor());
        bookViewHolder.letter.setText("" + book.getTitle().charAt(0));
        bookViewHolder.cercle = cercles.getResourceId(book.getCercle(),-1);
        bookViewHolder.bookPhoto.setImageResource(bookViewHolder.cercle);
        final Context context = bookViewHolder.view.getContext();
        bookViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.bookActivity");
                long id = book.getId();
                intent.putExtra(Variables.ID,id);
                intent.putExtra(Variables.TITLE,book.getTitle());
                intent.putExtra(Variables.AUTHOR,book.getAuthor());
                intent.putExtra(Variables.YEAR,book.getYear());
                intent.putExtra(Variables.PUBLISHER,book.getPublisher());
                intent.putExtra(Variables.CATEGORY,book.getCategory());
                intent.putExtra(Variables.EVALUATION,book.getPersonal_evaluation());
                ((Activity)context).startActivityForResult(intent,Variables.request_code_book);

            }
        });
        bookViewHolder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DeleteFragment fragment = DeleteFragment.newInstance("Delete book",book);
                fragment.show(fragmentManager,"dialog");
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
