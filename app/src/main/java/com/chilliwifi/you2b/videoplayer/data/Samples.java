
package com.chilliwifi.you2b.videoplayer.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Samples {
//    @NonNull
//    private static final List<Sample> audioSamples;
    @NonNull
    private static final List<Sample> videoSamples;

    static {
        String audioImage = "https://ia902708.us.archive.org/3/items/count_monte_cristo_0711_librivox/Count_Monte_Cristo_1110.jpg?cnt=0";

        //Audio items
//        audioSamples = new LinkedList<>();
//        audioSamples.add(new Sample("Beach boys I get around", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/beach%20boys%20-%20i%20get%20around.mp3", "https://upload.wikimedia.org/wikipedia/en/e/e2/Beach_Boys_-_I_Get_Around.jpg"));
//        audioSamples.add(new Sample("Eric Clapton layla", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/eric%20clapton%20-%20layla.mp3", "https://upload.wikimedia.org/wikipedia/en/thumb/1/10/Layla_(Acoustic)_Cover.jpg/220px-Layla_(Acoustic)_Cover.jpg"));
//        audioSamples.add(new Sample("INXS", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/inxs%20-%20need%20you%20tonight.mp3", "http://cps-static.rovicorp.com/3/JPG_500/MI0001/732/MI0001732931.jpg"));
//        audioSamples.add(new Sample("Radiohead Karma Police", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/radiohead%20-%20o.k.%20computer%20-%2006%20-%20karma%20police.mp3", "https://upload.wikimedia.org/wikipedia/en/a/a1/Radiohead.okcomputer.albumart.jpg"));
//        audioSamples.add(new Sample("Oasis Wonderwall","http://132.248.192.201/Mi_musica/Rock_80s/Varias/oasis%20-%20wonderwall.mp3", "https://upload.wikimedia.org/wikipedia/en/1/17/Wonderwall_cover.jpg"));
//


        //Video items
        videoSamples = new ArrayList<>();
        videoSamples.add(new Sample("3GP - Big Buck Bunny by Blender", "http://www.sample-videos.com/video/3gp/240/big_buck_bunny_240p_10mb.3gp"));
//        videoSamples.add(new Sample("FLV - Big Buck Bunny by Blender", "http://www.sample-videos.com/video/flv/720/big_buck_bunny_720p_5mb.flv"));
        videoSamples.add(new Sample("HLS - Wildlife", "http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8"));
        videoSamples.add(new Sample("MKV - Big Buck Bunny by Blender", "http://www.sample-videos.com/video/mkv/480/big_buck_bunny_480p_10mb.mkv"));
        videoSamples.add(new Sample("MP4 - Big Buck Bunny by Blender", "http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_10mb.mp4"));
        videoSamples.add(new Sample("MPEG DASH - Sintel by Blender", "https://bitdash-a.akamaihd.net/content/sintel/sintel.mpd"));
        videoSamples.add(new Sample("MPEG DASH - Big Buck Bunny by Blender, Live", "https://wowzaec2demo.streamlock.net/live/bigbuckbunny/manifest_mpm4sav_mvtime.mpd"));
        videoSamples.add(new Sample("Smooth Stream - Caminandes: Llama Drama by Blender", "http://amssamples.streaming.mediaservices.windows.net/634cd01c-6822-4630-8444-8dd6279f94c6/CaminandesLlamaDrama4K.ism/manifest"));
        videoSamples.add(new Sample("Smooth Stream - Tears of Steel Teaser by Blender", "http://amssamples.streaming.mediaservices.windows.net/3d7eaff9-39fa-442f-81cc-f2ea7db1797e/TearsOfSteelTeaser.ism/manifest"));
        videoSamples.add(new Sample("WEBM - Big Buck Bunny", "http://video.webmfiles.org/big-buck-bunny_trailer.webm"));
        videoSamples.add(new Sample("Radiohead", "https://r13---sn-vgqs7n7d.googlevideo.com/videoplayback?mime=video%2Fmp4&itag=18&key=yt6&ipbits=0&ratebypass=yes&lmt=1462272957493085&hcs=yes&sver=3&expire=1462315055&requiressl=yes&pl=27&gcr=us&ip=107.178.195.128&id=o-ADy1_1vJfTYYoEqAuciGs1HZ_-41hAI4STBuYKmi28pI&sparams=dur%2Cgcr%2Chcs%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Cshardbypass%2Csource%2Cupn%2Cexpire&initcwndbps=5862500&source=youtube&mm=31&mn=sn-vgqs7n7d&upn=O3mByI0R6c4&shardbypass=yes&dur=239.374&fexp=9406003%2C9416126%2C9416891%2C9419451%2C9420096%2C9422596%2C9428398%2C9429743%2C9431012%2C9433096%2C9433189%2C9433859%2C9433946%2C9433956%2C9434022%2C9434182%2C9434289%2C9434343%2C9434402%2C9435893&ms=au&mt=1462293177&mv=m&signature=A6C195446F2FDB15054D0077D67160CCE1837854.85008D4E887BEB0D7849837F5F17AC0C039B8E6F"));
        videoSamples.add(new Sample("Saicot", "https://r11---sn-vgqs7n7r.googlevideo.com/videoplayback?initcwndbps=5757500&lmt=1456484065843185&expire=1462316680&requiressl=yes&mime=video%2Fmp4&ratebypass=yes&ipbits=0&upn=zRKPz4vqdwQ&itag=18&fexp=9406012%2C9408211%2C9416126%2C9416891%2C9422596%2C9423749%2C9424337%2C9428398%2C9431012%2C9432076%2C9432683%2C9432955%2C9433043%2C9433096%2C9433946%2C9434282%2C9434290%2C9434611%2C9434767%2C9435445&id=o-AGxKiHMnWkpLA9R3g7n1HWMJ6b51C8QI5YsaE4lC4gMA&ms=au&mt=1462294772&mv=m&dur=1923.494&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&sver=3&source=youtube&pl=27&key=yt6&ip=107.178.194.114&mm=31&signature=6176AADFA0B2E8F9EB2A63117187D8EDEE27A14D.087BDD06A2FC4980AF391F4DF1914FEABC940499&mn=sn-vgqs7n7r"));



    }

    @NonNull
    public static List<Sample> getAudioSamples() {

        LinkedList audioSamples = new LinkedList<>();
        audioSamples.add(new Sample("Beach boys I get around", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/beach%20boys%20-%20i%20get%20around.mp3", "https://upload.wikimedia.org/wikipedia/en/e/e2/Beach_Boys_-_I_Get_Around.jpg"));
        audioSamples.add(new Sample("Eric Clapton layla", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/eric%20clapton%20-%20layla.mp3", "https://upload.wikimedia.org/wikipedia/en/thumb/1/10/Layla_(Acoustic)_Cover.jpg/220px-Layla_(Acoustic)_Cover.jpg"));
        audioSamples.add(new Sample("INXS", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/inxs%20-%20need%20you%20tonight.mp3", "http://cps-static.rovicorp.com/3/JPG_500/MI0001/732/MI0001732931.jpg"));
        audioSamples.add(new Sample("Radiohead Karma Police", "http://132.248.192.201/Mi_musica/Rock_80s/Varias/radiohead%20-%20o.k.%20computer%20-%2006%20-%20karma%20police.mp3", "https://upload.wikimedia.org/wikipedia/en/a/a1/Radiohead.okcomputer.albumart.jpg"));
        audioSamples.add(new Sample("Oasis Wonderwall","http://132.248.192.201/Mi_musica/Rock_80s/Varias/oasis%20-%20wonderwall.mp3", "https://upload.wikimedia.org/wikipedia/en/1/17/Wonderwall_cover.jpg"));

        return audioSamples;
    }

    @NonNull
    public static List<Sample> getVideoSamples() {
        return videoSamples;
    }

    /**
     * A container for the information associated with a
     * sample media item.
     */
    public static class Sample implements Parcelable {
        @NonNull
        private String title;
        @NonNull
        private String mediaUrl;
        @Nullable
        private String artworkUrl;

        public Sample(@NonNull String title, @NonNull String mediaUrl) {
            this(title, mediaUrl, null);
        }

        public Sample(@NonNull String title, @NonNull String mediaUrl, @Nullable String artworkUrl) {
            this.title = title;
            this.mediaUrl = mediaUrl;
            this.artworkUrl = artworkUrl;
        }

        @NonNull
        public String getTitle() {
            return title;
        }

        @NonNull
        public String getMediaUrl() {
            return mediaUrl;
        }

        @Nullable
        public String getArtworkUrl() {
            return artworkUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.mediaUrl);
            dest.writeString(this.artworkUrl);
        }

        protected Sample(Parcel in) {
            this.title = in.readString();
            this.mediaUrl = in.readString();
            this.artworkUrl = in.readString();
        }

        public static final Parcelable.Creator<Sample> CREATOR = new Parcelable.Creator<Sample>() {
            @Override
            public Sample createFromParcel(Parcel source) {
                return new Sample(source);
            }

            @Override
            public Sample[] newArray(int size) {
                return new Sample[size];
            }
        };
    }
}
