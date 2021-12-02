package com.example.instabook.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;

public class Perfil implements Parcelable {
    private String id;
    private String conteudo;
    private String dataPostagem;
    private String autorPostagem;

    public Perfil(String id, String conteudo, String dataPostagem, String autorPostagem) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataPostagem = dataPostagem;
        this.autorPostagem = autorPostagem;
    }
    public Perfil(JSONObject json) {
        super();
        try {
            this.id = json.getString("id");
            this.conteudo = json.getString("conteudo");
            this.dataPostagem = json.getString("dataPostagem");
            this.autorPostagem = json.getJSONObject("autorPostagem").getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Perfil(Parcel in) {
        id = in.readString();
        conteudo = in.readString();
        dataPostagem = in.readString();
        autorPostagem = in.readString();
    }

    public static final Parcelable.Creator<Perfil> CREATOR = new Parcelable.Creator<Perfil>() {
        @Override
        public Perfil createFromParcel(Parcel in) {
            return new Perfil(in);
        }

        @Override
        public Perfil[] newArray(int size) {
            return new Perfil[size];
        }
    };

    @Override
    public String toString() {
        return "id: "+id+"\n"+
                "Conteudo: "+conteudo+"\n"+
                "dataPostagem: " + dataPostagem+"\n"+
                "autorPostagem " + autorPostagem+"\n------------\n";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(String dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public String getAutorPostagem() {
        return autorPostagem;
    }

    public void setAutorPostagem(String autorPostagem) {
        this.autorPostagem = autorPostagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return id == perfil.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.conteudo);
        parcel.writeString(this.dataPostagem);
        parcel.writeString(this.autorPostagem);
    }
}
