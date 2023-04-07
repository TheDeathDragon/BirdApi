package la.shiro.birdapi.model.entity

import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  用户头像表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "user_avatar")
interface UserAvatar : BaseEntity {

    /**
     *  用户头像ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  用户头像关联用户ID */
    @Column(name = "user_id")
    val userId: Long

    /**
     *  用户头像URL */
    val url: String?
}
