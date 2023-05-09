package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.User
import la.shiro.birdapi.model.input.UserInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 9:22
 *  Description :
 */
interface UserService {
    fun getUserById(id: Long): User?

    fun getUserByIds(ids: List<Long>?): List<User>?

    fun getUserByUsername(username: String): User?

    fun getUserByEmail(email: String): User?

    fun getUserByPhone(phone: String): User?

    fun getUsers(pageable: Pageable): Page<User>?

    fun getUsersCondition(pageable: Pageable, userInput: UserInput?): Page<User>?

    fun addUser(userInput: UserInput?): Any?

    fun updateUserById(id: Long, userInput: UserInput?): Boolean

    fun updateUsernameById(id: Long, username: String): Boolean

    fun updatePasswordById(id: Long, password: String): Boolean

    fun updateEmailById(id: Long, email: String): Boolean

    fun updatePhoneById(id: Long, phone: String): Boolean

    fun updateSexById(id: Long, sex: String): Boolean
    fun updateWechatById(id: Long, wechat: String): Boolean

    fun updateQqById(id: Long, qq: String): Boolean

    fun updateAvatarById(id: Long, avatar: String): Boolean

    fun updateBirthdayById(id: Long, birthday: LocalDate): Boolean

    fun deleteUserById(id: Long): Boolean

    fun deleteUserByIds(ids: List<Long>?): Int


}