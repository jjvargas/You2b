package com.chilliwifi.you2b.repos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilliwifi.you2b.R;
import com.chilliwifi.you2b.searchyou2b.model.Items;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.videoplayer.AudioPlayerActivity;
import com.chilliwifi.you2b.videoplayer.data.MediaItem;
import com.chilliwifi.you2b.videoplayer.data.Samples;
import com.chilliwifi.you2b.videourl.VideoUrlApi;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class ReposAdapter extends SupportAnnotatedAdapter implements ReposAdapterBinder {

  @ViewType(
      layout = R.layout.list_repo,
      views = {
          @ViewField(id = R.id.avatar, type = ImageView.class, name = "avatar"),
          @ViewField(id = R.id.name, type = TextView.class, name = "name"),
          @ViewField(id = R.id.description, type = TextView.class, name = "description")
      }) public final int repo = 0;


  YoutubeVO repos;

  Picasso picasso;

  VideoUrlApi videoUrlApi;

  @Inject public ReposAdapter(Context context, Picasso picasso, VideoUrlApi videoUrlApi) {
    super(context);
    this.picasso = picasso;
    this.videoUrlApi = videoUrlApi;
  }

  @Override public int getItemCount() {
    return repos == null ? 0 : repos.items.size();
  }

  public YoutubeVO getRepos() {
    return repos;
  }

  public void setRepos(YoutubeVO  repos) {
    this.repos = repos;
  }

  @Override public void bindViewHolder(final ReposAdapterHolders.RepoViewHolder vh, final int position) {
    final Items repo = repos.items.get(position);

    vh.name.setText(repo.snippet.title);
    vh.description.setText(repo.snippet.description);

    picasso.load(repo.snippet.thumbnails.medium.url)
        .placeholder(R.color.grey)
        .error(R.color.grey)
        .into(vh.avatar);

    vh.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        //change to linkedlist
//        ArrayList<MediaItem> mediaItems = new ArrayList<MediaItem>();
//
//        for (Items item  : repos.items) {
//          Samples.Sample sample =  new Samples.Sample(item.snippet.title,null,item.snippet.thumbnails.medium.url);
//          MediaItem mediaItem = new MediaItem(sample, true,item.id.videoId);
//          mediaItems.add(mediaItem);
//        }

        Intent intent = new Intent(view.getContext(), AudioPlayerActivity.class);
//        Bundle mBundle = new Bundle();
//        mBundle.putString(AudioPlayerActivity.EXTRA_INDEX, repo.id.videoId);
//        mBundle.putParcelableArrayList(AudioPlayerActivity.MEDIA_ITEMS, mediaItems);
//        intent.putExtras(mBundle);

        //////////////////////////////

        ArrayList<MediaItem> mediaItems = new ArrayList<>();
//        for (Samples.Sample sample : Samples.getAudioSamples()) {
//
//          MediaItem mediaItem = new MediaItem(sample, true);
//          System.out.println("Javier media item adding " +  sample.getTitle());
//          mediaItems.add(mediaItem);
//
//        }

       for (Items item  : repos.items) {
          Samples.Sample sample =  new Samples.Sample(item.snippet.title,null,item.snippet.thumbnails.medium.url);
          MediaItem mediaItem = new MediaItem(sample, true,item.id.videoId);
          mediaItems.add(mediaItem);
        }


        Bundle mBundle = new Bundle();
        mBundle.putParcelableArrayList(AudioPlayerActivity.MEDIA_ITEMS, mediaItems);
          mBundle.putInt(AudioPlayerActivity.EXTRA_INDEX, position);
        intent.putExtras(mBundle);

        view.getContext().startActivity(intent);










      }
    });

  }


}
