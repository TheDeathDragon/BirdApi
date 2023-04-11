package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Bird
import la.shiro.birdapi.model.entity.id
import la.shiro.birdapi.model.entity.likeCount
import la.shiro.birdapi.model.entity.viewCount
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.plus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

/**
 * <p>
 * BirdRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface BirdRepository : KRepository<Bird, Long> {

    fun findTop10ByOrderByViewCountDesc(): List<Bird>?

    fun findByNameContainingOrderByIdDesc(
        name: String?,
        pageable: Pageable
    ): Page<Bird>

    fun findByEnNameContainingOrderByIdDesc(
        enName: String?,
        pageable: Pageable
    ): Page<Bird>

    fun findBySpeciesOrderByIdDesc(
        species: Long,
        pageable: Pageable
    ): Page<Bird>

    fun updateViewCountById(id: Long): Boolean {
        return sql.createUpdate(Bird::class) {
            set(table.viewCount, table.viewCount.plus(1))
            where(table.id eq id)
        }.execute() == 1
    }

    fun updateLikeCountById(id: Long): Boolean {
        return sql.createUpdate(Bird::class) {
            set(table.likeCount, table.likeCount.plus(1))
            where(table.id eq id)
        }.execute() == 1
    }
}

