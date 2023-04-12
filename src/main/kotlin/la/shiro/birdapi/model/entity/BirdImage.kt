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
     *  鸟类ID */
    @Column(name = "bird_id")
    val birdId : Long?

    /**
     *  鸟类图片标题 */
    val title: String

    /**
     *  鸟类图片URL */
    val url: String

    /**
     *  鸟类图片路径 */
    val path: String

}
