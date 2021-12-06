package com.example.instabook.ui.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instabook.R;
import com.example.instabook.presenter.PerfilPresenter;
import com.example.instabook.ui.BottomNavigation;
import com.example.instabook.ui.TelaLogin;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPerfil extends Fragment implements View.OnClickListener {


    PerfilPresenter presenterPerfil = new PerfilPresenter(this);
    Button btLogout;
    TextView tvName, tvAge, tvEmail, tvPosts;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPerfil newInstance(String param1, String param2) {
        FragmentPerfil fragment = new FragmentPerfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences pref = getActivity().getSharedPreferences("preferencia",MODE_PRIVATE);
        tvName = view.findViewById(R.id.tvNameProfile);
        tvAge = view.findViewById(R.id.tvAgeProfile);
        tvEmail = view.findViewById(R.id.tvEmailProfile);
        btLogout = (Button) view.findViewById(R.id.buttonLogout);
        tvName.setText(pref.getString("nome", "Sem nome definido"));
        tvAge.setText(pref.getString("idade", "18") + " anos");
        tvEmail.setText(pref.getString("email", "Email não cadastrado"));
        btLogout.setOnClickListener(this);
        presenterPerfil.buscaPerfil();

        return view;
    }

    public void preparaRecyclerView(RecyclerView.Adapter adapter){
        RecyclerView rv = getActivity().findViewById(R.id.rvPerfil);
        LinearLayoutManager llm =  new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences pref = getActivity().getSharedPreferences("preferencia",MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putString("senha","");
        ed.apply();
        Intent telaLogin = new Intent(getActivity().getApplicationContext(), TelaLogin.class);
        getActivity().startActivity(telaLogin);
        Toast.makeText(getActivity().getApplicationContext(), "Apertou pra deslogar", Toast.LENGTH_SHORT).show();
    }

}