package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  文章图片表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "article_image")
interface ArticleImage : BaseEntity {

    /**
     *  文章图片ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  文章图片的关联文章ID */
    @Column(name = "article_id")
    val articleId: Long?

    /**
     *  文章图片标题 */
    val title: String

    /**
     *  文章图片URL */
    val url: String

    /**
     *  文章图片路径 */
    val path: String

}
