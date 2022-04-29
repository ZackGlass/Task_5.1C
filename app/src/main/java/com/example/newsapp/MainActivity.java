package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TopStoriesAdapter.OnImageClickListener, NewsAdapter.OnStoryClickListener {

    RecyclerView newsRecyclerView;
    NewsAdapter newsAdapter;
    RecyclerView topStoryRecyclerView;
    TopStoriesAdapter topStoriesAdapter;
    List<Article> articleList = new ArrayList<>();
    List<Article> topStoryList = new ArrayList<>();

    Integer[] imageList = {R.drawable.picture0, R.drawable.picture1, R.drawable.picture2, R.drawable.picture3, R.drawable.picture4, R.drawable.picture5, R.drawable.picture6, R.drawable.picture7, R.drawable.picture8, R.drawable.picture9};
    String[] sourceList = {"The Guardian", "The Guardian", "ABC News", "ABC News", "Crikey", "Crikey", "ABC News", "ABC News", "The Guardian", "The Guardian", };
    String[] headlineList = {
            "Concern over Elon Musk’s Twitter takeover wipes $126bn off Tesla value",
            "Victoria’s solar rebate expansion will help wean state off gas, say experts",
            "Treasurer Josh Frydenberg blames rising inflation on COVID-19, war in Ukraine",
            "Labor promises crackdown on multinational tax avoidance would bring almost $2 billion in revenue",
            "Why isn’t the Coalition’s election campaign being called a disaster?",
            "Carbon state: Coalition changed rules to save fossil fuel donor tens of millions",
            "Surging inflation means interest rates could rise next week, as cost of living jumps most since GST",
            "Now that he is buying Twitter, has Elon Musk finally lost the plot?",
            "Coalition accused of sitting on environment report to avoid delivering ‘more bad news’",
            "Meta investors brace for a difficult quarter after stocks nosedive" };

    String[] descriptionList = {
            "Investors have wiped $126bn off Tesla’s value amid concern that Elon Musk may have to sell shares in the electric carmaker to fund his personal contribution to his $44bn acquisition of Twitter.",
            "The 190,000 residents who previously accessed a $1,400 rebate to install solar panels will now be able to access up to $1,000 for solar hot water",
            "The Federal Treasurer is blaming international volatility for grim inflation figures released today that show the sharpest rise in cost of living pressures in more than two decades.",
            "Labor has promised to close loopholes being used by multinational corporations to avoid paying tax to \"level the playing field for Australian businesses\" if it wins the federal election.",
            "With only three weeks until election day, Scott Morrison and the Coalition remain behind in the polls and aren’t making up much ground.",
            "Chevron's carbon capture and storage project off Western Australia has been a miserable failure, but luckily the government has stepped in to keep the costs down.",
            "The last time inflation was this high was in June 2001, when prices rose 6.1 per cent largely from the effect of the recently introduced 10 per cent Goods and Services Tax.",
            "He became the world's richest man by making electric cars through a venture that, despite being unprofitable until 18 months ago, is somehow valued at more than $US1 trillion ($1.38t) — more than every other automobile manufacturer combined.",
            "The Morrison government has been accused of sitting on a major report card on the state of Australia’s environment it received more than three months ago to avoid \"more bad news\".",
            "After losing a record $230bn in market value due to a disappointing earnings report in February, analysts are hoping to see progress" };

    String[] topicList = { "tech", "environment", "auspol", "auspol", "auspol", "environment", "auspol", "tech", "environment", "tech"};

    boolean[] topList = {true, false, false, true, false, false, true, false, true, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsAdapter = new NewsAdapter(articleList, MainActivity.this, this);
        newsRecyclerView.setAdapter(newsAdapter);
        RecyclerView.LayoutManager newsLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        newsRecyclerView.setLayoutManager(newsLayoutManager);

        topStoryRecyclerView = findViewById(R.id.topStoryRecyclerView);
        topStoriesAdapter = new TopStoriesAdapter(topStoryList, MainActivity.this, this);
        topStoryRecyclerView.setAdapter(topStoriesAdapter);
        RecyclerView.LayoutManager topStoriesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topStoryRecyclerView.setLayoutManager(topStoriesLayoutManager);

        for (int i = 0; i < sourceList.length; i++)
        {
            Article article = new Article(i, imageList[i], sourceList[i], headlineList[i], descriptionList[i], topicList[i], topList[i]);
            articleList.add(article);

            if (topList[i] == true)
            {
                topStoryList.add(article);
            }
        }
    }

    @Override
    public void onStoryClick(int position) {

        List<Article> relatedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++)
        {
            if (articleList.get(i).getTopic() == articleList.get(position).getTopic() && articleList.get(i).getHeadline() != articleList.get(position).getHeadline())
            {
                relatedList.add(articleList.get(i));
            }
        }

        Fragment fragment = new StoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putInt("image", articleList.get(position).getImage());
        bundle.putString("source", articleList.get(position).getSource());
        bundle.putString("headline", articleList.get(position).getHeadline());
        bundle.putString("description", articleList.get(position).getDescription());
        bundle.putString("topic", articleList.get(position).getTopic());

        bundle.putParcelableArrayList("relatedList", (ArrayList<Article>) relatedList);
        bundle.putParcelableArrayList("articleList", (ArrayList<Article>) articleList);


        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onImageClick(int position) {
        List<Article> relatedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++)
        {
            if (articleList.get(i).getTopic() == articleList.get(position).getTopic() && articleList.get(i).getHeadline() != articleList.get(position).getHeadline())
            {
                relatedList.add(articleList.get(i));
            }
        }

        Fragment fragment = new StoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putInt("image", topStoryList.get(position).getImage());
        bundle.putString("source", topStoryList.get(position).getSource());
        bundle.putString("headline", topStoryList.get(position).getHeadline());
        bundle.putString("description", topStoryList.get(position).getDescription());
        bundle.putString("topic", topStoryList.get(position).getTopic());

        bundle.putParcelableArrayList("relatedList", (ArrayList<Article>) relatedList);
        bundle.putParcelableArrayList("articleList", (ArrayList<Article>) articleList);


        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }


}