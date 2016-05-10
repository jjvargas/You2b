
package com.chilliwifi.you2b.videourl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Info {

    private Integer abr;
    private String acodec;
    private Integer ageLimit;
    private String altTitle;
    private Object annotations;
    private AutomaticCaptions automaticCaptions;
    private Double averageRating;
    private List<String> categories = new ArrayList<String>();
    private String creator;
    private String description;
    private Integer dislikeCount;
    private String displayId;
    private Integer duration;
    private Object endTime;
    private String ext;
    private String extractor;
    private String extractorKey;
    private String format;
    private String formatId;
    private String formatNote;
    private List<Format> formats = new ArrayList<Format>();
    private Integer height;
    private HttpHeaders httpHeaders;
    private String id;
    private Object isLive;
    private Integer likeCount;
    private String playerUrl;
    private Object playlist;
    private Object playlistIndex;
    private String protocol;
    private Object requestedSubtitles;
    private Object startTime;
    private Subtitles subtitles;
    private List<String> tags = new ArrayList<String>();
    private String thumbnail;
    private List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
    private String title;
    private String uploadDate;
    private String uploader;
    private String uploaderId;
    private String url;
    private String vcodec;
    private Integer viewCount;
    private String webpageUrl;
    private String webpageUrlBasename;
    private Integer width;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The abr
     */
    public Integer getAbr() {
        return abr;
    }

    /**
     * 
     * @param abr
     *     The abr
     */
    public void setAbr(Integer abr) {
        this.abr = abr;
    }

    /**
     * 
     * @return
     *     The acodec
     */
    public String getAcodec() {
        return acodec;
    }

    /**
     * 
     * @param acodec
     *     The acodec
     */
    public void setAcodec(String acodec) {
        this.acodec = acodec;
    }

    /**
     * 
     * @return
     *     The ageLimit
     */
    public Integer getAgeLimit() {
        return ageLimit;
    }

    /**
     * 
     * @param ageLimit
     *     The age_limit
     */
    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    /**
     * 
     * @return
     *     The altTitle
     */
    public String getAltTitle() {
        return altTitle;
    }

    /**
     * 
     * @param altTitle
     *     The alt_title
     */
    public void setAltTitle(String altTitle) {
        this.altTitle = altTitle;
    }

    /**
     * 
     * @return
     *     The annotations
     */
    public Object getAnnotations() {
        return annotations;
    }

    /**
     * 
     * @param annotations
     *     The annotations
     */
    public void setAnnotations(Object annotations) {
        this.annotations = annotations;
    }

    /**
     * 
     * @return
     *     The automaticCaptions
     */
    public AutomaticCaptions getAutomaticCaptions() {
        return automaticCaptions;
    }

    /**
     * 
     * @param automaticCaptions
     *     The automatic_captions
     */
    public void setAutomaticCaptions(AutomaticCaptions automaticCaptions) {
        this.automaticCaptions = automaticCaptions;
    }

    /**
     * 
     * @return
     *     The averageRating
     */
    public Double getAverageRating() {
        return averageRating;
    }

    /**
     * 
     * @param averageRating
     *     The average_rating
     */
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 
     * @param creator
     *     The creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The dislikeCount
     */
    public Integer getDislikeCount() {
        return dislikeCount;
    }

    /**
     * 
     * @param dislikeCount
     *     The dislike_count
     */
    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * 
     * @return
     *     The displayId
     */
    public String getDisplayId() {
        return displayId;
    }

    /**
     * 
     * @param displayId
     *     The display_id
     */
    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    /**
     * 
     * @return
     *     The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    public Object getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The end_time
     */
    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     *     The ext
     */
    public String getExt() {
        return ext;
    }

    /**
     * 
     * @param ext
     *     The ext
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * 
     * @return
     *     The extractor
     */
    public String getExtractor() {
        return extractor;
    }

    /**
     * 
     * @param extractor
     *     The extractor
     */
    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    /**
     * 
     * @return
     *     The extractorKey
     */
    public String getExtractorKey() {
        return extractorKey;
    }

    /**
     * 
     * @param extractorKey
     *     The extractor_key
     */
    public void setExtractorKey(String extractorKey) {
        this.extractorKey = extractorKey;
    }

    /**
     * 
     * @return
     *     The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * 
     * @param format
     *     The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 
     * @return
     *     The formatId
     */
    public String getFormatId() {
        return formatId;
    }

    /**
     * 
     * @param formatId
     *     The format_id
     */
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    /**
     * 
     * @return
     *     The formatNote
     */
    public String getFormatNote() {
        return formatNote;
    }

    /**
     * 
     * @param formatNote
     *     The format_note
     */
    public void setFormatNote(String formatNote) {
        this.formatNote = formatNote;
    }

    /**
     * 
     * @return
     *     The formats
     */
    public List<Format> getFormats() {
        return formats;
    }

    /**
     * 
     * @param formats
     *     The formats
     */
    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The httpHeaders
     */
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    /**
     * 
     * @param httpHeaders
     *     The http_headers
     */
    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The isLive
     */
    public Object getIsLive() {
        return isLive;
    }

    /**
     * 
     * @param isLive
     *     The is_live
     */
    public void setIsLive(Object isLive) {
        this.isLive = isLive;
    }

    /**
     * 
     * @return
     *     The likeCount
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 
     * @param likeCount
     *     The like_count
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 
     * @return
     *     The playerUrl
     */
    public String getPlayerUrl() {
        return playerUrl;
    }

    /**
     * 
     * @param playerUrl
     *     The player_url
     */
    public void setPlayerUrl(String playerUrl) {
        this.playerUrl = playerUrl;
    }

    /**
     * 
     * @return
     *     The playlist
     */
    public Object getPlaylist() {
        return playlist;
    }

    /**
     * 
     * @param playlist
     *     The playlist
     */
    public void setPlaylist(Object playlist) {
        this.playlist = playlist;
    }

    /**
     * 
     * @return
     *     The playlistIndex
     */
    public Object getPlaylistIndex() {
        return playlistIndex;
    }

    /**
     * 
     * @param playlistIndex
     *     The playlist_index
     */
    public void setPlaylistIndex(Object playlistIndex) {
        this.playlistIndex = playlistIndex;
    }

    /**
     * 
     * @return
     *     The protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * 
     * @param protocol
     *     The protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * 
     * @return
     *     The requestedSubtitles
     */
    public Object getRequestedSubtitles() {
        return requestedSubtitles;
    }

    /**
     * 
     * @param requestedSubtitles
     *     The requested_subtitles
     */
    public void setRequestedSubtitles(Object requestedSubtitles) {
        this.requestedSubtitles = requestedSubtitles;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    public Object getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The start_time
     */
    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The subtitles
     */
    public Subtitles getSubtitles() {
        return subtitles;
    }

    /**
     * 
     * @param subtitles
     *     The subtitles
     */
    public void setSubtitles(Subtitles subtitles) {
        this.subtitles = subtitles;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The thumbnails
     */
    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    /**
     * 
     * @param thumbnails
     *     The thumbnails
     */
    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The uploadDate
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * 
     * @param uploadDate
     *     The upload_date
     */
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * 
     * @return
     *     The uploader
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * 
     * @param uploader
     *     The uploader
     */
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    /**
     * 
     * @return
     *     The uploaderId
     */
    public String getUploaderId() {
        return uploaderId;
    }

    /**
     * 
     * @param uploaderId
     *     The uploader_id
     */
    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The vcodec
     */
    public String getVcodec() {
        return vcodec;
    }

    /**
     * 
     * @param vcodec
     *     The vcodec
     */
    public void setVcodec(String vcodec) {
        this.vcodec = vcodec;
    }

    /**
     * 
     * @return
     *     The viewCount
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 
     * @param viewCount
     *     The view_count
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 
     * @return
     *     The webpageUrl
     */
    public String getWebpageUrl() {
        return webpageUrl;
    }

    /**
     * 
     * @param webpageUrl
     *     The webpage_url
     */
    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }

    /**
     * 
     * @return
     *     The webpageUrlBasename
     */
    public String getWebpageUrlBasename() {
        return webpageUrlBasename;
    }

    /**
     * 
     * @param webpageUrlBasename
     *     The webpage_url_basename
     */
    public void setWebpageUrlBasename(String webpageUrlBasename) {
        this.webpageUrlBasename = webpageUrlBasename;
    }

    /**
     * 
     * @return
     *     The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
