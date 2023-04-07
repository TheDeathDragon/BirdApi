package la.shiro.birdapi.model.common

import org.babyfish.jimmer.sql.MappedSuperclass
import java.time.LocalDateTime

/**
 *  author: Rin Shiro
 *  Date: 23/4/7 23:24
 *  Description : 所有类的基类，包含创建时间和修改时间
 */
@MappedSuperclass
interface BaseEntity {

    val createdTime: LocalDateTime

    val modifiedTime: LocalDateTime
}