package ltwork.redis.config;


import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisLocker {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 加锁
     */
    public boolean lock(String requestId,String timeStamp){
        if(redisTemplate.opsForValue().setIfAbsent(requestId,timeStamp, 60,TimeUnit.SECONDS)){
            // setnx命令，key不存在  可以直接设置过期时间
            log.info("一"+Thread.currentThread().getName()+"拿到了锁");
            return true;
        }

        // 判断锁超时 没有解锁  防止死锁
        String currentLock = redisTemplate.opsForValue().get(requestId);
        // 如果锁过期 currentLock不为空且小于当前时间
        if(!StringUtil.isNullOrEmpty(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
            // 获取上一个锁的时间value 对应getset，如果lock存在
            String preLock =redisTemplate.opsForValue().getAndSet(requestId,timeStamp);

            // 假设两个线程同时进来这里，因为key被占用了，而且锁过期了。
            //并发下,getAndSet执行后,其中一个获取的key是旧的A 此时已经设置成新的B 另一个get到B ,第一个加锁成功
            if(!StringUtil.isNullOrEmpty(preLock) && preLock.equals(currentLock) ){
                log.info("二"+Thread.currentThread().getName()+"拿到了锁");
                return true;
            }
        }
        return false;
    }
    /**
     * 解锁
     * @param target
     * @param timeStamp
     */
    public void unlock(String target,String timeStamp){
        try {
            String currentValue = redisTemplate.opsForValue().get(target);
            if(!StringUtil.isNullOrEmpty(currentValue) && currentValue.equals(timeStamp) ){
                // 删除锁状态
                log.info(Thread.currentThread().getName()+"释放了锁");
                redisTemplate.opsForValue().getOperations().delete(target);
            }
        } catch (Exception e) {
            log.error("解锁异常!{}",e);
        }
    }

}
