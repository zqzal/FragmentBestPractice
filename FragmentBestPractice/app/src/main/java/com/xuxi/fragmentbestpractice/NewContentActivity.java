package com.xuxi.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewContentActivity extends AppCompatActivity {


    public static void actionStart(Context context,String newsTitle,String newsContent){
        Intent intent = new Intent(context,NewContentActivity.class);
        intent.putExtra("new_title",newsTitle);
        intent.putExtra("new_contet",newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String newsTitle = getIntent().getStringExtra("new_title");//获取传入的新闻标题
        String newsContent = getIntent().getStringExtra("new_contet");//获取传入的新闻内容
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_context_fragment);
        newsContentFragment.refresh(newsTitle,newsContent);//刷新NewsContentFragment界面
    }
}
