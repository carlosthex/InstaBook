package com.example.instabook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instabook.R;
import com.example.instabook.model.Feed;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<Feed> dados;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }


    public FeedAdapter(List<Feed> feed) {
        this.dados = feed;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        Feed feed = dados.get(position);
        TextView tv = holder.view.findViewById(R.id.tvFeedConteudo);
        tv.setText("Conteudo: "+feed.getConteudo());
        tv = holder.view.findViewById(R.id.tvFeedId);
        tv.setText("Id: "+feed.getId());
        tv = holder.view.findViewById(R.id.tvDataPostagem);
        tv.setText("Data: "+feed.getDataPostagem());
        tv = holder.view.findViewById(R.id.tvAutorPostagem);
        tv.setText("Autor: "+feed.getAutorPostagem());


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

