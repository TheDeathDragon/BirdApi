package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.UserService
import la.shiro.birdapi.dal.UserRepository
import la.shiro.birdapi.model.entity.User
import la.shiro.birdapi.model.input.UserInput
import la.shiro.birdapi.util.PasswordUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 9:22
 *  Description :
 */
@Service
@Transactional
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun getUserById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    override fun getUserByIds(ids: List<Long>?): List<User>? {
        return ids?.let { userRepository.findByIds(it) }
    }

    override fun getUserByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun getUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun getUserByPhone(phone: String): User? {
        return userRepository.findByPhone(phone)
    }

    override fun getUsers(pageable: Pageable): Page<User>? {
        return userRepository.findAll(pageable)
    }

    override fun addUser(userInput: UserInput?): Any? {
        userInput?.let {
            it.username?.let { it1 ->
                userRepository.findByUsername(it1)?.let { return "该用户名已经被使用" }
            }
            it.email?.let { it1 ->
                userRepository.findByEmail(it1)?.let { return "该邮箱已经被使用" }
            }
            it.phone?.let { it1 ->
                userRepository.findByPhone(it1)?.let { return "该手机号已经被使用" }
            }
        }
        return userInput?.let {
            it.password = it.password?.let { it1 -> PasswordUtil.encode(it1) }
            userRepository.insert(it)
        }
    }

    override fun updateUserById(id: Long, userInput: UserInput?): Boolean {
        return userInput?.let {
            userRepository.updateById(id, it)
        } ?: false
    }

    override fun updateUsernameById(id: Long, username: String): Boolean {
        return if (userRepository.findByUsername(username) != null) {
            userRepository.updateByIdAndUsername(id, username)
            true
        } else false
    }

    override fun updatePasswordById(id: Long, password: String): Boolean {
        return userRepository.updateByIdAndPassword(id, PasswordUtil.encode(password))
    }

    override fun updateEmailById(id: Long, email: String): Boolean {
        return if (userRepository.findByEmail(email) != null) {
            userRepository.updateByIdAndEmail(id, email)
            true
        } else false
    }

    override fun updatePhoneById(id: Long, phone: String): Boolean {
        return if (userRepository.findByPhone(phone) != null) {
            userRepository.updateByIdAndPhone(id, phone)
            true
        } else false
    }

    override fun updateSexById(id: Long, sex: String): Boolean {
        return userRepository.updateByIdAndSex(id, sex)
    }

    override fun updateWechatById(id: Long, wechat: String): Boolean {
        return userRepository.updateByIdAndWechat(id, wechat)
    }

    override fun updateQqById(id: Long, qq: String): Boolean {
        return userRepository.updateByIdAndQq(id, qq)
    }

    override fun updateAvatarById(id: Long, avatar: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateBirthdayById(id: Long, birthday: LocalDate): Boolean {
        return userRepository.updateByIdAndBirthday(id, birthday)
    }

    override fun deleteUserById(id: Long): Boolean {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else false
    }

    override fun deleteUserByIds(ids: List<Long>?): Int {
        return ids?.let {
            var count = 0
            it.forEach { id ->
                if (userRepository.existsById(id)) {
                    userRepository.deleteById(id)
                    count++
                }
            }
            count
        } ?: 0
    }
}