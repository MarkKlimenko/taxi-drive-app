package systems.vostok.taxi.drive.app.configuration.persistent

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
@ConfigurationProperties(prefix = 'redis')
class RedisConfiguration {
    String server
    Integer port

    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        new LettuceConnectionFactory(new RedisStandaloneConfiguration(server, port))
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        new RedisTemplate<>().with {
            setConnectionFactory(redisConnectionFactory())
            it
        }
    }
}
