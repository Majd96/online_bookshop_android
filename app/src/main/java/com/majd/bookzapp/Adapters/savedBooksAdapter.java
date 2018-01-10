package com.majd.bookzapp.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.majd.bookzapp.R;
import com.majd.bookzapp.dataBase.BookContract;
import com.squareup.picasso.Picasso;

/**
 * Created by majd on 1/2/18.
 */

public class savedBooksAdapter extends RecyclerView.Adapter<savedBooksAdapter.SavedBooksViewHodler> {

    private Context mContext;
    public static Cursor mCursor;
    private BookAdapter.OnBookClickListener listener;
    public savedBooksAdapter(Context context,BookAdapter.OnBookClickListener listener) {
        mContext = context;
        this.listener=listener;
    }


    @Override
    public SavedBooksViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.check_out_item, parent, false);
        return new SavedBooksViewHodler(view);
    }

    @Override
    public void onBindViewHolder(SavedBooksViewHodler holder, int position) {
        mCursor.moveToPosition(position);
        String title = mCursor.getString(mCursor.getColumnIndex(BookContract.BookEntry.COLUMN_TITLE));
        String imageUrl = mCursor.getString(mCursor.getColumnIndex(BookContract.BookEntry.COLUMN_IMAGE_URL));
        String price = mCursor.getString(mCursor.getColumnIndex(BookContract.BookEntry.COLUMN_PRICE));


        holder.title.setText(title);
        holder.price.setText(price+"$");
        Picasso.with(mContext).load(imageUrl).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        else return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }





    public class SavedBooksViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public ImageView imageView;

        public TextView title;
        public TextView price;
        private ImageButton imageButton;


        public SavedBooksViewHodler(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.savedImage);
           title=itemView.findViewById(R.id.savedTitle);
            price=itemView.findViewById(R.id.savedPrice);
            imageButton=itemView.findViewById(R.id.delete);
            imageButton.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            listener.onBookClick(getAdapterPosition(), imageButton);

        }
    }
}
