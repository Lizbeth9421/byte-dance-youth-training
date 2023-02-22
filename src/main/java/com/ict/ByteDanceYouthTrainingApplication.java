package com.ict;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan({"com.ict.mapper"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class ByteDanceYouthTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByteDanceYouthTrainingApplication.class, args);
    }


}
