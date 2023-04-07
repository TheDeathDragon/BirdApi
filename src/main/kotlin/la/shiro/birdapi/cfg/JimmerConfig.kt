package la.shiro.birdapi.cfg

import la.shiro.birdapi.model.entity.ENTITY_MANAGER
import org.babyfish.jimmer.sql.runtime.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JimmerConfig {

    @Bean
    fun entityManager(): EntityManager = ENTITY_MANAGER
}