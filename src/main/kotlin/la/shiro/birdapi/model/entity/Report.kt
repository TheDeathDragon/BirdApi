package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  举报表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-13
 */
@Entity
@Table(name = "report")
interface Report  : BaseEntity {

    /**
     *  举报ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  被举报的ID */
    @Column(name = "reported_id")
    val reportedId: Long

    /**
     *  提交举报用户ID */
    @Column(name = "user_id")
    val userId: Long?

    /**
     *  举报类型(1鸟类/2文章/3用户/4评论) */
    val type: String

    /**
     *  举报状态(0待审核/1已处理/2无需处理) */
    val status: String

    /**
     *  举报内容 */
    val content: String
}
