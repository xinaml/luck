package com.xinaml.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement //开启事务
@ComponentScan(basePackages = {"com.xinaml.storage"}) //spring 扫描
@EnableJpaRepositories(basePackages = {"com.xinaml.storage.rep"}) //持久化接口
@EntityScan(basePackages = {"com.xinaml.storage.entity"}, basePackageClasses = Jsr310JpaConverters.class)//扫描实体映射类，Jsr310JpaConverters：对日期的转换处理

public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }

}
