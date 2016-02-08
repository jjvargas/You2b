package com.chilliwifi.you2b;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new SearchYouTubeFragment())
                    .commit();
        }
    }
}
