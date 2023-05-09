package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Notice
import la.shiro.birdapi.model.entity.id
import la.shiro.birdapi.model.entity.title
import la.shiro.birdapi.model.input.NoticeInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.ilike
import org.babyfish.jimmer.sql.kt.ast.expression.or
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

/**
 * <p>
 * NoticeRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface NoticeRepository : KRepository<Notice, Long> {

    fun findTop1ByOrderByIdDesc(): Notice?
    fun findNoticesCondition(pageable: Pageable, noticeInput: NoticeInput?): Page<Notice> {
        if (noticeInput == null) {
            return findAll(pageable)
        }

        val title = noticeInput.title

        return pager(pageable).execute(
            sql.createQuery(Notice::class) {
                title?.takeIf { it.isNotBlank() }?.let {
                    where(table.title ilike title)
                }
                orderBy(table.id.desc())
                select(table)
            }
        )
    }
}
