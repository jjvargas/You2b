
package com.chilliwifi.you2b.videourl.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class VideoUrl {

    private Info info;
    private String url;
    private String youtubeDlVersion;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The info
     */
    public Info getInfo() {
        return info;
    }

    /**
     * 
     * @param info
     *     The info
     */
    public void setInfo(Info info) {
        this.info = info;
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
     *     The youtubeDlVersion
     */
    public String getYoutubeDlVersion() {
        return youtubeDlVersion;
    }

    /**
     * 
     * @param youtubeDlVersion
     *     The youtube-dl.version
     */
    public void setYoutubeDlVersion(String youtubeDlVersion) {
        this.youtubeDlVersion = youtubeDlVersion;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
