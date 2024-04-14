package com.zch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

/**
 * @author Poison02
 * @date 2024/4/13
 */
@Service
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = "com.zch.platform.mapper")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class IMPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(IMPlatformApplication.class, args);
    }
}