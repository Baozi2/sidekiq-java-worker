package com.zouyyu.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.zouyyu.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * @author YuZou
 * @date 30/03/2018
 */
public class RedisConfigFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfigFactory.class);

    private static final String SIDEKIQ_YML = "sidekiq.yml";
    private static  URI redisURI;
    private static  final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());

    public static URI getRedisURI(){
        if(redisURI == null){
            RedisConfig redisConfig;
            try {

                File sidekiqConfigFile = new File(RedisConfigFactory.class.getClassLoader().getResource(SIDEKIQ_YML).getFile());
                if(sidekiqConfigFile.exists()){
                    redisConfig =  MAPPER.readValue(sidekiqConfigFile, RedisConfig.class);
                }else {
                    LOGGER.info("~~~~~未提供默认的sidekiq配置文件，使用默认配置链接redis~~~~~~");
                    redisConfig = new RedisConfig();
                }

               redisURI = redisConfig.getRedisURI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return redisURI;
    }
}
