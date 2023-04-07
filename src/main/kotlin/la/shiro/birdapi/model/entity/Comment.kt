package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  评论表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "comment")
interface Comment : BaseEntity {

    /**
     *  评论ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  评论文章ID */
    @Column(name = "article_id")
    val articleId: Long

    /**
     *  评论用户ID */
    @Column(name = "user_id")
    val userId: Long

    /**
     *  评论父ID */
    val pid: Long

    /**
     *  评论点赞数量 */
    @Column(name = "like_count")
    val likeCount: Long

    /**
     *  评论内容 */
    val content: String

    /**
     *  评论是否发布(0待审核1发布) */
    @Column(name = "is_published")
    val isPublished: String
}
