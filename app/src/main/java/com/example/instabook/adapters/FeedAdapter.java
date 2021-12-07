package com.example.instabook.adapters;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instabook.R;
import com.example.instabook.model.Feed;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
        tv.setText(feed.getConteudo());
        tv = holder.view.findViewById(R.id.tvDataPostagem);

        //Gambi feia
        Button botao;
        botao = holder.view.findViewById(R.id.buttonCompartilha);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView autor = holder.view.findViewById(R.id.tvAutorPostagem);
                TextView data = holder.view.findViewById(R.id.tvDataPostagem);
                TextView conteudo = holder.view.findViewById(R.id.tvFeedConteudo);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);

                String CompartilharString = autor.getText().toString()
                        +"\n"+data.getText().toString()
                        +"\n"+conteudo.getText().toString();
                sendIntent.putExtra(Intent.EXTRA_TEXT, CompartilharString);
                sendIntent.setType("text/plain");

                //Gambiarra direcionada pelo próprio whatsapp
                sendIntent.setPackage("com.whatsapp");

                try{
                    view.getContext().startActivity(sendIntent);
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(view.getContext(), "Whatsapp Não Instalado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String dtStart = feed.getDataPostagem();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Locale.setDefault(new Locale("pt", "BR"));
        try {
            tv.setText(DateFormat.getDateInstance(DateFormat.FULL).format(format.parse(dtStart)));
        } catch (ParseException e) {
            tv.setText("data indisponível");
            e.printStackTrace();
        }

        tv = holder.view.findViewById(R.id.tvAutorPostagem);
        tv.setText((feed.getAutorPostagem() != null) ? feed.getAutorPostagem() : "autor desconhecido");

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

