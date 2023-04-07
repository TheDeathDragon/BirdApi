package la.shiro.birdapi.model.entity

import java.time.LocalDateTime
import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  鸟类文章表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "article")
public interface Article : BaseEntity {

    /**
     *  文章ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  文章用户ID */
    @Column(name = "user_id")
    val userId: Long

    /**
     *  文章审核管理员用户ID */
    @Column(name = "auditor_id")
    val auditorId: Long

    /**
     *  文章分类 */
    @Column(name = "category_id")
    val categoryId: Long

    /**
     *  文章访问量 */
    @Column(name = "view_count")
    val viewCount: Long

    /**
     *  文章点赞量 */
    @Column(name = "like_count")
    val likeCount: Long

    /**
     *  文章标题 */
    @Key
    val title: String

    /**
     *  文章封面 */
    val thumbnail: String?

    /**
     *  文章摘要 */
    val summary: String?

    /**
     *  文章正文 */
    val content: String?

    /**
     *  文章状态(0草稿/1发布/2待审核) */
    val status: String

    /**
     *  文章是否置顶(0否/1是) */
    @Column(name = "is_top")
    val isTop: String

    /**
     *  文章是否允许评论(0否/1是) */
    @Column(name = "is_comment")
    val isComment: String

    /**
     *  文章审核时间 */
    @Column(name = "audit_time")
    val auditTime: LocalDateTime?
}
