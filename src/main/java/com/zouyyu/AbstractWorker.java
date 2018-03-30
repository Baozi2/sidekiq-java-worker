package com.zouyyu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zouyyu.job.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author YuZou
 * @date 30/03/2018
 */
public abstract class AbstractWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWorker.class);

    private static  JedisPool JEDIS_POOL ;//= new RedisConnection().pool();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public abstract void perform();

    static {
       JEDIS_POOL = new RedisConnection().pool();
    }
    public Job fetchOne() {
        List<String> jobJSON = fetchOriginJob();
        if(jobJSON.size() == 2){
            try {
                return MAPPER.readerFor(Job.class).readValue(jobJSON.get(1));
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("处理数据时出错，时间:{},data:{}", LocalDateTime.now(), String.join("-", jobJSON));
            }
        }
        return  null;
    }

    public List<String> fetchOriginJob() {
        try (Jedis jedis = JEDIS_POOL.getResource()) {
            String BASE_QUEUE_NAME = "queue:";
            String queueName = BASE_QUEUE_NAME + "example";
            return jedis.brpop(2, queueName);
        }
    }

    public void start() {
        LOGGER.info("开始处理任务，时间:" + LocalDateTime.now());
        while (true) {
            perform();
        }
    }
}
