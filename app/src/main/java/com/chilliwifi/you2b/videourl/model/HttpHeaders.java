
package com.chilliwifi.you2b.videourl.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class HttpHeaders {

    private String Accept;
    private String AcceptCharset;
    private String AcceptEncoding;
    private String AcceptLanguage;
    private String UserAgent;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Accept
     */
    public String getAccept() {
        return Accept;
    }

    /**
     * 
     * @param Accept
     *     The Accept
     */
    public void setAccept(String Accept) {
        this.Accept = Accept;
    }

    /**
     * 
     * @return
     *     The AcceptCharset
     */
    public String getAcceptCharset() {
        return AcceptCharset;
    }

    /**
     * 
     * @param AcceptCharset
     *     The Accept-Charset
     */
    public void setAcceptCharset(String AcceptCharset) {
        this.AcceptCharset = AcceptCharset;
    }

    /**
     * 
     * @return
     *     The AcceptEncoding
     */
    public String getAcceptEncoding() {
        return AcceptEncoding;
    }

    /**
     * 
     * @param AcceptEncoding
     *     The Accept-Encoding
     */
    public void setAcceptEncoding(String AcceptEncoding) {
        this.AcceptEncoding = AcceptEncoding;
    }

    /**
     * 
     * @return
     *     The AcceptLanguage
     */
    public String getAcceptLanguage() {
        return AcceptLanguage;
    }

    /**
     * 
     * @param AcceptLanguage
     *     The Accept-Language
     */
    public void setAcceptLanguage(String AcceptLanguage) {
        this.AcceptLanguage = AcceptLanguage;
    }

    /**
     * 
     * @return
     *     The UserAgent
     */
    public String getUserAgent() {
        return UserAgent;
    }

    /**
     * 
     * @param UserAgent
     *     The User-Agent
     */
    public void setUserAgent(String UserAgent) {
        this.UserAgent = UserAgent;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
