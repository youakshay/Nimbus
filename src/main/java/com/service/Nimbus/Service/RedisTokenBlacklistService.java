package com.service.Nimbus.Service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RedisTokenBlacklistService {
    private final Jedis jedis;

    public RedisTokenBlacklistService(Jedis jedis){
        this.jedis=jedis;
    }

    public void blacklistToken(String token, long expirationInMillis) {
        int seconds = (int) (expirationInMillis / 1000);
        jedis.setex("blacklisted_tokens" + token, seconds, "true");
    }

    public boolean isTokenBlacklisted(String token) {
        String key = "blacklisted_tokens" + token;
        return jedis.exists(key);
    }
}
