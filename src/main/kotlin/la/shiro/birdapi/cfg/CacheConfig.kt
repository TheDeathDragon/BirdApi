package la.shiro.birdapi.cfg

import com.fasterxml.jackson.databind.ObjectMapper
import org.babyfish.jimmer.meta.ImmutableProp
import org.babyfish.jimmer.meta.ImmutableType
import org.babyfish.jimmer.spring.cache.CaffeineBinder
import org.babyfish.jimmer.spring.cache.RedisHashBinder
import org.babyfish.jimmer.spring.cache.RedisValueBinder
import org.babyfish.jimmer.sql.cache.Cache
import org.babyfish.jimmer.sql.cache.CacheFactory
import org.babyfish.jimmer.sql.cache.chain.*
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

// -----------------------------
// For non-cache mode, this class will never be used.
// -----------------------------
@ConditionalOnProperty("spring.data.redis")
@Configuration
class CacheConfig {

    @Bean
    fun rawDataRedisTemplate(
        connectionFactory: RedisConnectionFactory
    ): RedisTemplate<String, ByteArray> =
        RedisTemplate<String, ByteArray>().apply {

            setConnectionFactory(connectionFactory)

            val nopSerializer = object : RedisSerializer<ByteArray?> {
                override fun serialize(t: ByteArray?): ByteArray? = t
                override fun deserialize(bytes: ByteArray?): ByteArray? = bytes
            }
            keySerializer = StringRedisSerializer.UTF_8
            valueSerializer = nopSerializer
            hashKeySerializer = StringRedisSerializer.UTF_8
            hashValueSerializer = nopSerializer
        }

    @Bean
    fun cacheFactory(
        redisTemplate: RedisTemplate<String, ByteArray>,
        objectMapper: ObjectMapper
    ): CacheFactory =
        object : CacheFactory {

            // Id -> Object
            override fun createObjectCache(type: ImmutableType): Cache<*, *>? =
                ChainCacheBuilder<Any, Any>()
                    .add(CaffeineBinder(512, Duration.ofSeconds(1)))
                    .add(RedisValueBinder(redisTemplate, objectMapper, type, Duration.ofMinutes(10)))
                    .build()
        }

    companion object {

        @JvmStatic
        private fun <K, V> createPropCache(
            isMultiView: Boolean,
            prop: ImmutableProp,
            redisTemplate: RedisTemplate<String, ByteArray>,
            objectMapper: ObjectMapper,
            redisDuration: Duration
        ): Cache<K, V> {
            /*
             * If multi-view cache is required, only redis can be used, because redis support hash structure.
             * The value of redis hash is a nested map, so that different users can see different data.
             *
             * Other simple key value caches can be divided into two levels.
             * The first level is caffeine, the second level is redis.
             *
             * Note: Once the multi-view cache takes affect, it will consume
             * a lot of cache space, please only use it for important data.
             */
            if (isMultiView) {
                return ChainCacheBuilder<K, V>()
                    .add(RedisHashBinder(redisTemplate, objectMapper, prop, redisDuration))
                    .build()
            }

            return ChainCacheBuilder<K, V>()
                .add(CaffeineBinder(512, Duration.ofSeconds(1)))
                .add(RedisValueBinder(redisTemplate, objectMapper, prop, redisDuration))
                .build()
        }
    }
}