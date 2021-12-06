package com.example.instabook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instabook.R;
import com.example.instabook.model.Perfil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
        tv.setText(perfil.getConteudo());
        tv = holder.view.findViewById(R.id.tvDataPostagemPerfil);

        String dtStart = perfil.getDataPostagem();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Locale.setDefault(new Locale("pt", "BR"));
        try {
            tv.setText(DateFormat.getDateInstance(DateFormat.FULL).format(format.parse(dtStart)));
        } catch (ParseException e) {
            tv.setText("data indisponível");
            e.printStackTrace();
        }
        tv = holder.view.findViewById(R.id.tvAutorPostagemPerfil);
        tv.setText((perfil.getAutorPostagem() != null) ? perfil.getAutorPostagem() : "autor desconhecido");

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

