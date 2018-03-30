package com.zouyyu.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author YuZou
 * @date 30/03/2018
 */
public class RedisConfigFactoryTest {

    @Test
    public void testFilePath(){
       Assert.assertNotNull(RedisConfigFactory.getRedisURI());
    }
}
