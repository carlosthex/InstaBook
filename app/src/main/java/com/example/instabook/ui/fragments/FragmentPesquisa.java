package com.example.instabook.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.instabook.R;

import com.example.instabook.presenter.PesquisaPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPesquisa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPesquisa extends Fragment implements View.OnClickListener{

    PesquisaPresenter presenterPesquisa = new PesquisaPresenter(this);

    Button btPesquisa;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPesquisa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPesquisa.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPesquisa newInstance(String param1, String param2) {
        FragmentPesquisa fragment = new FragmentPesquisa();
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
        View view = inflater.inflate(R.layout.fragment_pesquisa, container, false);

        btPesquisa = (Button) view.findViewById(R.id.buttonPesquisa);
        btPesquisa.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.X");
        Date data = new Date();
        String dataFormatada = formataData.format(data);
        Toast.makeText(getActivity().getApplicationContext(),
                "Data formatada " + dataFormatada, Toast.LENGTH_SHORT).show();
        presenterPesquisa.buscaPesquisa();
    }

    public void preparaRecyclerView(RecyclerView.Adapter adapter){
        RecyclerView rv = getActivity().findViewById(R.id.rvPesquisa);
        LinearLayoutManager llm =  new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }
}