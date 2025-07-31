package com.service.Nimbus.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {
    @Bean
    public Jedis jedisClient() {
        return new Jedis("localhost", 6379);
    }
}
