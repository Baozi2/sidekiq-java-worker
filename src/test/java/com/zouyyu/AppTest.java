package com.zouyyu;

import org.junit.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zouyy
 * @data 2018年03月30日16:52:22
 */
public class AppTest {

    @Test
    public void testSNWorker(){
      SNWorker worker =  new SNWorker();
      worker.start();
    }
}
