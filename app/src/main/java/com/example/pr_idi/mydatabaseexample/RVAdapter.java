package com.example.pr_idi.mydatabaseexample;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


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
        final Book book = books.get(i);
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
                intent.putExtra(Variables.TITLE,book.getTitle());
                intent.putExtra(Variables.AUTHOR,book.getAuthor());
                intent.putExtra(Variables.YEAR,book.getYear());
                intent.putExtra(Variables.PUBLISHER,book.getPublisher());
                intent.putExtra(Variables.CATEGORY,book.getCategory());
                intent.putExtra(Variables.EVALUATION,book.getPersonal_evaluation());
                context.startActivity(intent);
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
