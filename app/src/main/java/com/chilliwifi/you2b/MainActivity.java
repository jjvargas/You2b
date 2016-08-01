package com.chilliwifi.you2b;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chilliwifi.you2b.searchyou2b.view.SearchYouTubeFragment;
import com.chilliwifi.you2b.videoplayer.AudioPlayerActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DemoActivity";
    private SlidingUpPanelLayout mLayout;
    private LinearLayout miniPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new SearchYouTubeFragment())
                    .commit();
        }


        miniPlayer = (LinearLayout) findViewById(R.id.miniplayer);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);


        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }

        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });


        mLayout.setTouchEnabled(false);
        miniPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Javier onclick");

                Intent intent = new Intent(view.getContext(), AudioPlayerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);

            }
        });

        mLayout.setOverlayed(false);
    }



    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
