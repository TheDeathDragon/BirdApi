package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  鸟类表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "bird")
interface Bird : BaseEntity {

    /**
     *  鸟类ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  鸟类种类ID */
    val species: Long

    /**
     *  鸟类名称 */
    val name: String

    /**
     *  鸟类英文名称 */
    @Column(name = "en_name")
    val enName: String?

    /**
     *  鸟类简介 */
    val introduction: String?

    /**
     *  鸟类形态特征 */
    val feature: String?

    /**
     *  鸟类栖息环境 */
    val resident: String?

    /**
     *  鸟类习性 */
    val habit: String?

    /**
     *  鸟类分布范围 */
    val distribution: String?

    /**
     *  鸟类繁殖方式 */
    val reproduction: String?

    /**
     *  鸟类文化意义 */
    @Column(name = "cultural_significance")
    val culturalSignificance: String?
}
