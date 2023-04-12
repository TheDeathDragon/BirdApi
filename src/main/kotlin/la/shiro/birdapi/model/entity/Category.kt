package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  文章分类表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "category")
interface Category : BaseEntity {

    /**
     *  文章分类ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  文章分类父类ID */
    val pid: Long

    /**
     *  文章分类名称 */
    @Key
    val name: String

    /**
     *  文章分类描述 */
    val description: String?
}
