package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BookViewHolder>{

    List<Book> books;
    TypedArray cercles;
    public RVAdapter(List<Book> books){
        this.books = books;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookCategory;
        TextView bookYear;
        TextView bookPublisher;
        TextView bookEvaluation;
        ImageView bookPhoto;
        TextView letter;
        int cercle;
        View view;

        BookViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            bookTitle = (TextView)itemView.findViewById(R.id.book_title);
            bookAuthor = (TextView)itemView.findViewById(R.id.book_author);
            bookCategory = (TextView)itemView.findViewById(R.id.book_category);
            bookYear = (TextView)itemView.findViewById(R.id.book_year);
            bookPublisher = (TextView)itemView.findViewById(R.id.book_publisher);
            bookEvaluation = (TextView)itemView.findViewById(R.id.book_evaluation);
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
    public void onBindViewHolder(final BookViewHolder bookViewHolder, int i) {
        final Book book = books.get(i);
        //getting and setting in text views all atributes book
        bookViewHolder.bookTitle.setText(book.getTitle());
        bookViewHolder.bookAuthor.setText(book.getAuthor());
        bookViewHolder.bookCategory.setText(book.getCategory());
        bookViewHolder.bookYear.setText(Objects.toString(book.getYear()));
        bookViewHolder.bookPublisher.setText(book.getPublisher());
        bookViewHolder.bookEvaluation.setText(book.getPersonal_evaluation());
        bookViewHolder.letter.setText("" + book.getTitle().charAt(0));
        bookViewHolder.cercle = cercles.getResourceId(book.getCercle(),-1);
        bookViewHolder.bookPhoto.setImageResource(bookViewHolder.cercle);
        //if the book has not personal evaluation, we must to say it. Else if
        //the personal evalution takes more than 3 lines in the text view, we must ellipze the text.
        String evaluation = book.getPersonal_evaluation();
        if (evaluation.equals(null) || evaluation.length() == 0){
            evaluation = "You don't have personal evaluation for this book.";
            bookViewHolder.bookEvaluation.setText(evaluation);
        }
        else{
            bookViewHolder.bookEvaluation.setText(evaluation);
            if (bookViewHolder.bookEvaluation.getLineCount() > 3){
                evaluation = evaluation.substring(0,50) + "...";
                bookViewHolder.bookEvaluation.setText(evaluation);
            }
        }
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
               // context.startActivity(intent);*/
                /*LinearLayout layout = (LinearLayout) bookViewHolder.view.findViewById(R.id.buttons_layout);
                layout.setVisibility(View.VISIBLE);*/
                ((Activity)context).startActivityForResult(intent,Variables.request_code_book);

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
