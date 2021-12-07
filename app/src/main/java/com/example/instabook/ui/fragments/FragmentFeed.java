package com.example.instabook.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.instabook.R;
import com.example.instabook.presenter.FeedPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFeed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFeed extends Fragment {

    //instancia presenter
    FeedPresenter presenterFeed = new FeedPresenter(this);

    Button button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentFeed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFeed newInstance(String param1, String param2) {
        FragmentFeed fragment = new FragmentFeed();
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
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        presenterFeed.buscaFeed();

        return view;
    }

    public void preparaRecyclerView(RecyclerView.Adapter adapter){
        RecyclerView rv = getActivity().findViewById(R.id.rvFeed);
        LinearLayoutManager llm =  new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }
}