package com.ict;

import com.ict.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ByteDanceYouthTrainingApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(SecurityUtil.encryptPassword("123456"));
    }

}
