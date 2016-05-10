
package com.chilliwifi.you2b.videourl.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Format {

    private Integer abr;
    private String acodec;
    private String ext;
    private Integer filesize;
    private String format;
    private String formatId;
    private String formatNote;
    private HttpHeaders httpHeaders;
    private String playerUrl;
    private Integer preference;
    private String protocol;
    private Double tbr;
    private String url;
    private String vcodec;
    private String container;
    private Integer fps;
    private Integer height;
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
     *     The filesize
     */
    public Integer getFilesize() {
        return filesize;
    }

    /**
     * 
     * @param filesize
     *     The filesize
     */
    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
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
     *     The preference
     */
    public Integer getPreference() {
        return preference;
    }

    /**
     * 
     * @param preference
     *     The preference
     */
    public void setPreference(Integer preference) {
        this.preference = preference;
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
     *     The tbr
     */
    public Double getTbr() {
        return tbr;
    }

    /**
     * 
     * @param tbr
     *     The tbr
     */
    public void setTbr(Double tbr) {
        this.tbr = tbr;
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
     *     The container
     */
    public String getContainer() {
        return container;
    }

    /**
     * 
     * @param container
     *     The container
     */
    public void setContainer(String container) {
        this.container = container;
    }

    /**
     * 
     * @return
     *     The fps
     */
    public Integer getFps() {
        return fps;
    }

    /**
     * 
     * @param fps
     *     The fps
     */
    public void setFps(Integer fps) {
        this.fps = fps;
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
