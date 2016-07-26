package com.chilliwifi.you2b.videoplayer.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.chilliwifi.you2b.videoplayer.manager.PlaylistManager;
import com.devbrackets.android.playlistcore.manager.IPlaylistItem;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

/**
 * A custom {@link IPlaylistItem}
 * to hold the information pertaining to the audio and video items
 */

public class MediaItem implements IPlaylistItem, Parcelable {

    private Samples.Sample sample;
    private String videoId;
    boolean isAudio;

//    public MediaItem(Samples.Sample sample, boolean isAudio) {
//        this(sample,isAudio,null);
//    }

    public MediaItem(Samples.Sample sample, boolean isAudio,String videoId) {
        this.sample = sample;
        this.isAudio = isAudio;
        this.videoId = videoId;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getPlaylistId() {
        return 0;
    }

    @Override
    public int getMediaType() {
        return isAudio ? PlaylistManager.AUDIO : PlaylistManager.VIDEO;
    }

    @Override
    public String getMediaUrl() {
        return sample.getMediaUrl();
    }

    @Override
    public String getDownloadedMediaUri() {
        return null;
    }

    @Override
    public String getThumbnailUrl() {
        return sample.getArtworkUrl();
    }

    @Override
    public String getArtworkUrl() {
        return sample.getArtworkUrl();
    }

    @Override
    public String getTitle() {
        return sample.getTitle();
    }

    @Override
    public String getAlbum() {
        return "ExoMedia Demo";
    }

    @Override
    public String getArtist() {
        return "Unknown Artist";
    }

    public String getVideoId() {
        return videoId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.sample, flags);
        dest.writeString(this.videoId);
        dest.writeByte(this.isAudio ? (byte) 1 : (byte) 0);
    }

    protected MediaItem(Parcel in) {
        this.sample = in.readParcelable(Samples.Sample.class.getClassLoader());
        this.videoId = in.readString();
        this.isAudio = in.readByte() != 0;
    }

    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        @Override
        public MediaItem createFromParcel(Parcel source) {
            return new MediaItem(source);
        }

        @Override
        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };
}