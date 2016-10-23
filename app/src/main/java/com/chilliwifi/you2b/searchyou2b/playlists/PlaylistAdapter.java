package com.chilliwifi.you2b.searchyou2b.playlists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilliwifi.you2b.R;
import com.chilliwifi.you2b.repos.ReposAdapterBinder;
import com.chilliwifi.you2b.repos.ReposAdapterHolders;
import com.chilliwifi.you2b.searchyou2b.model.Items;
import com.chilliwifi.you2b.searchyou2b.playlists.model.Item;
import com.chilliwifi.you2b.searchyou2b.playlists.model.Playlists;
import com.chilliwifi.you2b.videoplayer.AudioPlayerActivity;
import com.chilliwifi.you2b.videoplayer.data.MediaItem;
import com.chilliwifi.you2b.videoplayer.data.Samples;
import com.chilliwifi.you2b.videourl.VideoUrlApi;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

public class PlaylistAdapter extends SupportAnnotatedAdapter implements PlaylistAdapterBinder {

  @ViewType(
      layout = R.layout.list_repo,
      views = {
          @ViewField(id = R.id.avatar, type = ImageView.class, name = "avatar"),
          @ViewField(id = R.id.name, type = TextView.class, name = "name"),
          @ViewField(id = R.id.description, type = TextView.class, name = "description")
      }) public final int repo = 0;


    Playlists repos;

  Picasso picasso;

  VideoUrlApi videoUrlApi;

  @Inject public PlaylistAdapter(Context context, Picasso picasso, VideoUrlApi videoUrlApi) {
    super(context);
    this.picasso = picasso;
    this.videoUrlApi = videoUrlApi;
  }

  @Override public int getItemCount() {
    return repos == null ? 0 : repos.getItems().size();
  }

  public Playlists getRepos() {
    return repos;
  }

  public void setRepos(Playlists  repos) {
    this.repos = repos;
  }


  @Override public void bindViewHolder(final PlaylistAdapterHolders.RepoViewHolder vh, final int position) {
    final Item repo = repos.getItems().get(position);

    vh.name.setText(repo.getSnippet().getTitle());
    vh.description.setText(repo.getSnippet().getTitle());

    picasso.load(repo.getSnippet().getThumbnails().getMedium().getUrl())
        .placeholder(R.color.grey)
        .error(R.color.grey)
        .into(vh.avatar);

    vh.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {


        Intent intent = new Intent(view.getContext(), AudioPlayerActivity.class);
        //////////////////////////////

        ArrayList<MediaItem> mediaItems = new ArrayList<>();

       for (Item item  : repos.getItems()) {
          Samples.Sample sample =  new Samples.Sample(item.getSnippet().getTitle(),null,item.getSnippet().getThumbnails().getMedium().getUrl());
          MediaItem mediaItem = new MediaItem(sample, true,item.getSnippet().getResourceId().getVideoId());
          mediaItems.add(mediaItem);
        }


        Bundle mBundle = new Bundle();
        mBundle.putParcelableArrayList(AudioPlayerActivity.MEDIA_ITEMS, mediaItems);
          mBundle.putInt(AudioPlayerActivity.EXTRA_INDEX, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtras(mBundle);

        view.getContext().startActivity(intent);




      }
    });

  }


}
