package com.rabbiter.market.common.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer()); //设置redis key 的序列化方式为String
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());//设置value的序列化方式为jackson方式,默认的序列化方式是jdk的序列化
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer() );//设置hash类型的value序列化方式
        /**
         * 为什么要序列化?
         *    为了把对象持久化保存起来,从内存保存到磁盘中
         *    对象可以被序列化,需要实现Serializable接口
         * */
        return template;
    }
}
