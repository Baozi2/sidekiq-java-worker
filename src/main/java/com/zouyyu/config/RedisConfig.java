package com.zouyyu.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author YuZou
 * @date 30/03/2018
 */
public class RedisConfig {

    private static final String DEFAULT_URL = "redis://localhost:6379/0";
    private URI    redisURI;
    private String host = "localhost";
    private Integer port = 6379;
    private Integer db = 0;

    public  RedisConfig(){
        try {
            redisURI = new URI(DEFAULT_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @JsonProperty("SIDEKIQ_REDIS_URI")
    public URI getRedisURI() {
        return redisURI;
    }

    public void setRedisURI(URI redisURI) {
        this.redisURI = redisURI;
    }

    public String getHost() {
        return redisURI.getHost();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return redisURI.getPort();
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDb() {
        String path = redisURI.getPath();
        return Integer.parseInt(path.split("/")[1]);
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    @Override
    public String toString() {
        return "RedisConfigFactory{" +
            "redisURI=" + redisURI +
            ", host='" + host + '\'' +
            ", port=" + port +
            ", db=" + db +
            '}';
    }
}
