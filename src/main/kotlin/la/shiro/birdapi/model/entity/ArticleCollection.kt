package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  文章收藏表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "article_collection")
interface ArticleCollection : BaseEntity {

    /**
     *  文章收藏ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  文章收藏关联用户ID */
    @Column(name = "user_id")
    val userId: Long

    /**
     *  文章收藏关联文章ID */
    @Column(name = "article_id")
    val articleId: Long
}
