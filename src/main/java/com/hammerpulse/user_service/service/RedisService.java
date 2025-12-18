package com.hammerpulse.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public<T> T get(String key,Class<T> entityclass){
        try{
            Object o=redisTemplate.opsForValue().get(key);
            ObjectMapper mapper=new ObjectMapper();
            return mapper.readValue((String)o, entityclass);
        }
        catch(Exception e){
            System.out.println("Redis get error "+e);
            return null;
        }
    }

    public void set(String key,Object o,Long ttl){
        try{
            ObjectMapper mapper=new ObjectMapper();
            redisTemplate.opsForValue().set(key, mapper.writeValueAsString(o), ttl, TimeUnit.SECONDS);
        }
        catch(Exception e){
            System.out.println("Redis set error "+e);
        }
    }
}
