package com.example.instabook.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.ui.TelaCadastro;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Cadastro implements Parcelable {
    private String nome;
    private String idade;
    private String email;
    private String senha;

    public Cadastro(String nome, String idade, String email, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
    }
    public Cadastro(JSONObject json) {
        super();
        try {
            this.nome = json.getString("nome");
            this.idade = json.getString("idade");
            this.email = json.getString("email");
            this.senha = json.getString("senha");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Cadastro(Parcel in) {
        nome = in.readString();
        idade = in.readString();
        email = in.readString();
        senha = in.readString();
    }

    public static final Parcelable.Creator<Cadastro> CREATOR = new Parcelable.Creator<Cadastro>() {
        @Override
        public Cadastro createFromParcel(Parcel in) {
            return new Cadastro(in);
        }

        @Override
        public Cadastro[] newArray(int size) {
            return new Cadastro[size];
        }
    };

    @Override
    public String toString() {
        return "nome: "+nome+"\n"+
                "Idade: "+idade+"\n"+
                "email: " + email+"\n"+
                "senha: " + senha+"\n------------\n";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String id) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cadastro cadastro = (Cadastro) o;
        return nome == cadastro.nome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nome);
        parcel.writeString(this.idade);
        parcel.writeString(this.email);
        parcel.writeString(this.senha);
    }
}