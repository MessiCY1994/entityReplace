package com.messiyang.modelreplace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.messiyang.modelreplace.dao")
public class ModelreplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelreplaceApplication.class, args);
    }

}
