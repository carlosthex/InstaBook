package com.example.instabook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instabook.R;
import com.example.instabook.model.Pesquisa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
        tv.setText(pesquisa.getConteudo());
        tv = holder.view.findViewById(R.id.tvDataPostagemPesquisa);
        String dtStart = pesquisa.getDataPostagem();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Locale.setDefault(new Locale("pt", "BR"));
        try {
            tv.setText(DateFormat.getDateInstance(DateFormat.FULL).format(format.parse(dtStart)));
        } catch (ParseException e) {
            tv.setText("data indisponível");
            e.printStackTrace();
        }
        tv = holder.view.findViewById(R.id.tvAutorPostagemPesquisa);
        tv.setText(pesquisa.getAutorPostagem());


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

