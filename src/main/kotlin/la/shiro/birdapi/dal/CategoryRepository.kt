package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Category
import la.shiro.birdapi.model.entity.id
import la.shiro.birdapi.model.entity.name
import la.shiro.birdapi.model.entity.pid
import la.shiro.birdapi.model.input.CategoryInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.ilike
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

/**
 * <p>
 * CategoryRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface CategoryRepository : KRepository<Category, Long> {
    fun getCategoriesByPid(pid: Long): List<Category>
    fun findCategoriesCondition(pageable: Pageable, categoryInput: CategoryInput?): Page<Category>? {
        if (categoryInput == null) {
            return findAll(pageable)
        }

        val name = categoryInput.name
        val pid = categoryInput.pid

        return pager(pageable).execute(
            sql.createQuery(Category::class) {
                name?.takeIf { it.isNotBlank() }?.let {
                    where(table.name ilike name)
                }
                pid?.takeIf { it > 0 }?.let {
                    where(table.pid eq pid)
                }
                orderBy(table.id.desc())
                select(table)
            }
        )
    }
}

