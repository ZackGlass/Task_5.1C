package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class StoryFragment extends Fragment implements RelatedAdapter.OnRowClickListener {

    private int image;
    private String source;
    private String head;
    private String desc;
    private String topic;
    private List<Article> articleList;
    private List<Article> relatedList;

    View root;
    ImageView imageView;
    TextView headlineTextview;
    TextView descriptionTextview;
    RecyclerView relatedRecyclerView;

    public StoryFragment() {
        // Required empty public constructor
    }


    public static StoryFragment newInstance(int image, String head, String body) {
        StoryFragment fragment = new StoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getInt("image");
            head = getArguments().getString("headline");
            desc = getArguments().getString("description");
            topic = getArguments().getString("topic");

            Bundle bundle = getArguments();
            relatedList = bundle.getParcelableArrayList("relatedList");
            articleList = bundle.getParcelableArrayList("articleList");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_story, container, false);

        imageView = root.findViewById(R.id.storyImageView);
        headlineTextview = root.findViewById(R.id.headTextView);
        descriptionTextview = root.findViewById(R.id.bodyTextView);

        imageView.setImageResource(image);
        headlineTextview.setText(head);
        descriptionTextview.setText(desc);



        relatedRecyclerView = root.findViewById(R.id.relatedRecyclerView);
        RelatedAdapter relatedAdapter = new RelatedAdapter(relatedList, getContext(),  this);
        relatedRecyclerView.setAdapter(relatedAdapter);
        RecyclerView.LayoutManager relatedLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        relatedRecyclerView.setLayoutManager(relatedLayoutManager);

        return root;
    }

    @Override
    public void onRowClick(int position) {


        List<Article> newRelatedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++)
        {
            if (articleList.get(i).getTopic() == articleList.get(position).getTopic() && i != position)
            {
                newRelatedList.add(articleList.get(i));
            }


        }

        Fragment nextFragment = new StoryFragment();
        FragmentManager fragmentManager = getParentFragmentManager();

        Bundle nextBundle = new Bundle();
        nextBundle.putInt("image", relatedList.get(position).getImage());
        nextBundle.putString("source", relatedList.get(position).getSource());
        nextBundle.putString("headline", relatedList.get(position).getHeadline());
        nextBundle.putString("description", relatedList.get(position).getDescription());
        nextBundle.putString("topic", relatedList.get(position).getTopic());

        nextBundle.putParcelableArrayList("relatedList", (ArrayList<Article>) newRelatedList);
        nextBundle.putParcelableArrayList("articleList", (ArrayList<Article>) articleList);


        nextFragment.setArguments(nextBundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, nextFragment);
        fragmentTransaction.commit();
    }
}