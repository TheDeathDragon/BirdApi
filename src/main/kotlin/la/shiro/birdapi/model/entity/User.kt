package la.shiro.birdapi.model.entity

import java.time.LocalDate
import la.shiro.birdapi.model.common.BaseEntity
import org.babyfish.jimmer.sql.*


/**
 * <p>
 *  用户表
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Entity
@Table(name = "user")
interface User : BaseEntity {

    /**
     *  用户ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long

    /**
     *  用户昵称 */
    val username: String

    /**
     *  用户邮箱 */
    val email: String

    /**
     *  用户手机号 */
    val phone: String

    /**
     *  用户密码 */
    val password: String

    /**
     *  用户性别 */
    val sex: String?

    /**
     *  用户微信号 */
    val wechat: String?

    /**
     *  用户QQ号 */
    val qq: String?

    /**
     *  用户头像图片地址 */
    val avatar: String?

    /**
     *  用户生日 */
    val birthday: LocalDate?
}
