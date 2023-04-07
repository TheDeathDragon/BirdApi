package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  鸟类图片表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "bird_image")
interface BirdImage : BaseEntity {

    /**
     *  鸟类图片ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  鸟类图片标题 */
    val title: String

    /**
     *  鸟类图片URL */
    val url: String
}
