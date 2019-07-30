package com.xuxi.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private static final String TAG = "NewsTitleFragment";
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        RecyclerView newsTitleRecyclerView = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter =  new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);

        return view;
    }

    private List<News> getNews() {

        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.setTittle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + "."));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String string) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(string);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().findViewById(R.id.news_content_layout) != null){
            isTwoPane = true; //可以找到news_content_layput布局时，为双页模式
        }else {
            isTwoPane = false; //找不到news_content_layput布局时，为单页模式
        }


        Log.d(TAG, "onClick: "+getFragmentManager().findFragmentById(R.id.news_context_fragment));
        Log.d(TAG, "onClick: "+getFragmentManager().findFragmentById(R.id.news_context_fragment));

        Log.d(TAG, "onActivityCreated: " + getChildFragmentManager().findFragmentById(R.id.news_context_fragment));

    }


    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;

            public ViewHolder(View view){
                super(view);
                newsTitleText = view.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter(List<News> newsList){
            mNewsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item,viewGroup,false);
            final ViewHolder holder = new ViewHolder(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane){
                        //如果是双页模式，则刷新NewsContentFragment中的内容
//                        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_context_fragment);
//                        newsContentFragment.refresh(news.getTittle(),news.getContent());

//                        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_context_fragment);

//                        newsContentFragment.refresh(news.getTittle(),news.getContent());
//                        Log.d(TAG, "onClick: " + newsContentFragment);

                        NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_context_fragment);

                        Log.d(TAG, "onClick: "+getFragmentManager().findFragmentById(R.id.news_context_fragment));
                        Log.d(TAG, "onClick: "+getFragmentManager().findFragmentById(R.id.news_context_fragment));
                        Log.d(TAG, "onClick: "+getFragmentManager().findFragmentById(R.id.news_context_fragment));

                    }else {
                        NewContentActivity.actionStart(getActivity(),news.getTittle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            News news = mNewsList.get(i);
            viewHolder.newsTitleText.setText(news.getTittle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }





}
