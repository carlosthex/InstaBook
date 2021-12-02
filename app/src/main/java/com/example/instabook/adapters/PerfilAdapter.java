package com.example.instabook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instabook.R;
import com.example.instabook.model.Feed;
import com.example.instabook.model.Perfil;

import java.util.List;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.ViewHolder> {

    private List<Perfil> dados;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }


    public PerfilAdapter(List<Perfil> perfil) {
        this.dados = perfil;
    }

    @NonNull
    @Override
    public PerfilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.perfil_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilAdapter.ViewHolder holder, int position) {
        Perfil perfil = dados.get(position);
        TextView tv = holder.view.findViewById(R.id.tvPerfilConteudo);
        tv.setText("Conteudo: "+perfil.getConteudo());
        tv = holder.view.findViewById(R.id.tvDataPostagemPerfil);
        tv.setText("Data: "+perfil.getDataPostagem());
        tv = holder.view.findViewById(R.id.tvAutorPostagemPerfil);
        tv.setText("Autor: "+perfil.getAutorPostagem());


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

