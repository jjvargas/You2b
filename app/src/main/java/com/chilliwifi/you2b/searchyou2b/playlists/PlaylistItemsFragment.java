package com.chilliwifi.you2b.searchyou2b.playlists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.chilliwifi.you2b.R;
import com.chilliwifi.you2b.SampleModule;
import com.chilliwifi.you2b.repos.ReposAdapter;
import com.chilliwifi.you2b.repos.exception.ErrorMessageDeterminer;
import com.chilliwifi.you2b.searchyou2b.DaggerSearchYouTubeComponent;
import com.chilliwifi.you2b.searchyou2b.model.YoutubeVO;
import com.chilliwifi.you2b.searchyou2b.playlists.model.Playlists;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class PlaylistItemsFragment
        extends MvpLceViewStateFragment<SwipeRefreshLayout, Playlists, PlaylistItemsView, PlaylistItemsRxPresenter>
        implements PlaylistItemsView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.searchView)
    SearchView searchView;

    @Inject
    ErrorMessageDeterminer errorMessageDeterminer;
    PlaylistItemsComponent playlistItemsComponent;
    PlaylistAdapter adapter;

    protected void injectDependencies() {
        playlistItemsComponent =
                DaggerPlaylistItemsComponent.builder().sampleModule(new SampleModule(getActivity())).build();
        playlistItemsComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        injectDependencies();
        return inflater.inflate(R.layout.fragment_repos, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = playlistItemsComponent.adapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        contentView.setOnRefreshListener(this);

        searchView.setVisibility(GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public LceViewState<Playlists, PlaylistItemsView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public Playlists getData() {
        return adapter.getRepos();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return errorMessageDeterminer.getErrorMessage(e, pullToRefresh);
    }

    @Override
    public PlaylistItemsRxPresenter createPresenter() {
        return playlistItemsComponent.presenter();
    }

    @Override
    public void setData(Playlists data) {
        adapter.setRepos(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getVideosRx(pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
        e.printStackTrace();
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
            contentView.post(new Runnable() {
                @Override
                public void run() {
                    contentView.setRefreshing(true);
                }
            });
        }
    }
}
