package com.itzpy;

import com.itzpy.controller.AccountController;
import com.itzpy.redis.RedisComponent;
import com.itzpy.redis.RedisUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Logger;

@Component("initRun")
public class InitRun implements ApplicationRunner {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化开始");

        try {
            dataSource.getConnection();
            redisUtils.get("test");
            log.info("数据库连接成功");
        } catch (SQLException  e){
            log.error("数据库连接失败");
        } catch (RedisConnectionFailureException e){
            log.error("redis连接失败");
        } catch (Exception e) {
            log.error("初始化失败");
        }
    }
}
