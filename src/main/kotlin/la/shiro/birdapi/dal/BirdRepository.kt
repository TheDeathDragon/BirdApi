package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.*
import la.shiro.birdapi.model.input.BirdInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ilike
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

    fun findBirdsCondition(pageable: Pageable, birdInput: BirdInput?): Page<Bird>? {
        if (birdInput == null) {
            return findAll(pageable)
        }
        val name = birdInput.name
        val enName = birdInput.enName
        val species = birdInput.species

        return pager(pageable).execute(
            sql.createQuery(Bird::class) {
                name?.takeIf { it.isNotBlank() }?.let {
                    where(table.name ilike name)
                }
                enName?.takeIf { it.isNotBlank() }?.let {
                    where(table.enName ilike enName)
                }
                species?.takeIf { it > 0 }?.let {
                    where(table.species eq species)
                }
                orderBy(table.id.desc())
                select(table)
            }
        )
    }
}

