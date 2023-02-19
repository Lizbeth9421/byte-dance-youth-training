package com.ict;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.ict.mapper"})
public class ByteDanceYouthTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteDanceYouthTrainingApplication.class, args);
    }


}
