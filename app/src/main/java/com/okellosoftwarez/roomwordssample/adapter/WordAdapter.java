package com.okellosoftwarez.roomwordssample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.okellosoftwarez.roomwordssample.R;
import com.okellosoftwarez.roomwordssample.entity.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder>{
    private LayoutInflater mInflater;
    private List<Word> wordList;

    public WordAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        if (wordList != null){
            Word current = wordList.get(position);
            holder.textView.setText(current.getWord());
        }
        else {
            holder.textView.setText("No Words...");
        }
    }

    public void setWordList(List<Word> words){

        wordList = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (wordList != null){
            return wordList.size();
        }
        else
        return 0;
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.itemView);
        }
    }
}
