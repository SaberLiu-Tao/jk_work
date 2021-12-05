package ltwork.redis.controller;


import lombok.extern.slf4j.Slf4j;
import ltwork.redis.config.RedisLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@Slf4j
public class RedisTestController {
    @Autowired
    private RedisLocker redisLocker;

    @Autowired
    private RedisTemplate redisTemplate;

    static int n=500;
    @GetMapping("testRedis/{username}")
    public String getMember(@PathVariable("username") final String username){
        //设置的过期时间就是60s
        log.info("开始");
        for (int i = 1; i < 11; i++) {
            String value = String.valueOf(System.currentTimeMillis());
            ThreadA thread = new ThreadA(username,value);
            thread.start();

        }
        return "11";
    }


    @GetMapping("testRedis1/{username}")
    public String getMember2(@PathVariable("username") String username){
        //设置的过期时间就是60s
        redisTemplate.opsForValue().set("11","22");
        return "1";
    }

    class ThreadA extends Thread{
        String username;
        String timeStamp2;
        public ThreadA(String username,String timeStamp2) {
            this.username = username;
            this.timeStamp2 = timeStamp2;
        }
        @Override
        public void run(){
            if (!redisLocker.lock(username,String.valueOf(timeStamp2))){
                return ;
            }
            n--;
            log.info("消费了"+n);
            redisLocker.unlock(username,String.valueOf(timeStamp2));
            return ;
        }
    }


}
