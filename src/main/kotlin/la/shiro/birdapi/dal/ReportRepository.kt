package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Report
import la.shiro.birdapi.model.entity.id
import la.shiro.birdapi.model.entity.status
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository
import org.babyfish.jimmer.sql.kt.ast.expression.eq

/**
 * <p>
 * ReportRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-13
 */
@Repository
interface ReportRepository : KRepository<Report, Long> {

    fun updateStatusById(id: Long, status: String): Boolean {
        return sql.createUpdate(Report::class) {
            set(table.status, status)
            where(table.id eq id)
        }.execute() == 1
    }

}

