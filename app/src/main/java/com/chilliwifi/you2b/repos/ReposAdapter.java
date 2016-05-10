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
import com.chilliwifi.you2b.videoplayer.FullScreenVideoPlayerActivity;
import com.chilliwifi.you2b.videoplayer.VideoPlayerActivity;
import com.chilliwifi.you2b.videourl.VideoUrlApi;
import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.squareup.picasso.Picasso;

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

  @Override public void bindViewHolder(final ReposAdapterHolders.RepoViewHolder vh, int position) {
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
//        getVideoURl(repo.id.videoId,view.getContext());

        Intent intent = new Intent(view.getContext(), FullScreenVideoPlayerActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(VideoPlayerActivity.EXTRA_VIDEO_ID, repo.id.videoId);
        intent.putExtras(mBundle);
        view.getContext().startActivity(intent);

      }
    });

  }


}
