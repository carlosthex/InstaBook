package com.example.instabook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instabook.R;
import com.example.instabook.model.Pesquisa;

import java.util.List;

public class PesquisaAdapter extends RecyclerView.Adapter<PesquisaAdapter.ViewHolder> {

    private List<Pesquisa> dados;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }


    public PesquisaAdapter(List<Pesquisa> pesquisa) {
        this.dados = pesquisa;
    }

    @NonNull
    @Override
    public PesquisaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pesquisa_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesquisaAdapter.ViewHolder holder, int position) {
        Pesquisa pesquisa = dados.get(position);
        TextView tv = holder.view.findViewById(R.id.tvPesquisaConteudo);
        tv.setText("Conteudo: "+pesquisa.getConteudo());
        tv = holder.view.findViewById(R.id.tvDataPostagemPesquisa);
        tv.setText("Data: "+pesquisa.getDataPostagem());
        tv = holder.view.findViewById(R.id.tvAutorPostagemPesquisa);
        tv.setText("Autor: "+pesquisa.getAutorPostagem());

        //Supostamente o compartilhar, mas precisa ser FeedAdapter

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

