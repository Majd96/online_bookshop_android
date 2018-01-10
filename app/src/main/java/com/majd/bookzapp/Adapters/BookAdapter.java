package com.majd.bookzapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.majd.bookzapp.Classes.Book;
import com.majd.bookzapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by majd on 1/2/18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {


    private OnBookClickListener listener;
    private ArrayList<Book> books;
    private Context mContext;



    public BookAdapter(ArrayList<Book> books, Context mContext,OnBookClickListener listener) {
        this.books=books;
        this.mContext = mContext;
        this.listener=listener;

    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, final int position) {


        holder.title.setText(books.get(position).getTitle());
        holder.desc.setText(books.get(position).getDesc());
        holder.price.setText(10+position+"$");
        books.get(position).setPrice(10+position);


        Picasso.with(mContext).load(books.get(position).getImageUrl()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        if (books == null) return 0;
        else return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;

        public TextView title;
        public TextView desc;

        public TextView price;
        private ImageButton imageButton;


        public BookViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.articleImage);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            imageButton = itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            listener.onBookClick(getAdapterPosition(), imageButton);
        }
    }

    public interface OnBookClickListener {
        void onBookClick(int clickedItemIndex, View view);
    }


}
