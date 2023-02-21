package com.ict;

import com.ict.mapper.VideoMapper;
import com.ict.util.FileNameUtil;
import com.ict.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ByteDanceYouthTrainingApplicationTests {

    @Autowired
    VideoMapper videoMapper;

    @Test
    void contextLoads() {
        System.out.println(SecurityUtil.encryptPassword("123456"));
    }


    @Test
    void test1(){
        System.out.println(videoMapper.getFeedList(1));
    }
}
