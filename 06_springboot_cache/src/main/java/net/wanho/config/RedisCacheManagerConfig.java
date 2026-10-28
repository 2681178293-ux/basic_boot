package net.wanho.config;

import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

import java.time.Duration;

/**
 * 开启缓存，并定制 Redis 缓存默认序列化 / 分缓存 TTL。
 * RedisCacheManager 由 Spring Boot 自动装配；
 * application.yml 的 time-to-live 仅作为未单独配置的缓存默认过期时间。
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class RedisCacheManagerConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        GenericJacksonJsonRedisSerializer jacksonJsonRedisSerializer = GenericJacksonJsonRedisSerializer.builder()
                .enableDefaultTyping(BasicPolymorphicTypeValidator.builder()
                        .allowIfBaseType(Object.class)
                        .build())
                .build();

        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonJsonRedisSerializer));
    }

    /**
     * 按缓存名分别设置过期时间（与 @Cacheable(value = "xxx") 中的名字对应）。
     * 未列出的缓存仍使用 yml 里的 spring.cache.redis.time-to-live。
     */
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(
            RedisCacheConfiguration redisCacheConfiguration) {
        return builder -> builder
                // 菜单：变更少，可较长
                .withCacheConfiguration("menu", redisCacheConfiguration.entryTtl(Duration.ofHours(6)))
                // 数据字典：变更少，可更长
                .withCacheConfiguration("dict", redisCacheConfiguration.entryTtl(Duration.ofHours(12)))
                // 验证码：短过期
                .withCacheConfiguration("captcha", redisCacheConfiguration.entryTtl(Duration.ofMinutes(5)))
                // 学生：示例业务缓存
                .withCacheConfiguration("student", redisCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
    }
}
