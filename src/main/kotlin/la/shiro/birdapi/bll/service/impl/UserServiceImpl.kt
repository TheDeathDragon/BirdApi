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
    override fun getUserById(id: Long?): User? {
        return id?.let { userRepository.findById(it).orElse(null) }
    }

    override fun getUserByIds(ids: List<Long>?): List<User>? {
        return ids?.let { userRepository.findByIds(it) }
    }

    override fun getUserByUsername(username: String?): User? {
        return username?.let { userRepository.findByUsername(it) }
    }

    override fun getUserByEmail(email: String?): User? {
        return email?.let { userRepository.findByEmail(it) }
    }

    override fun getUserByPhone(phone: String?): User? {
        return phone?.let { userRepository.findByPhone(it) }
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

    override fun updateUserById(id: Long?, userInput: UserInput?): Boolean {
        return id?.let { userInput?.let { it1 -> userRepository.updateById(it, it1) } } ?: false
    }

    override fun updateUsernameById(id: Long?, username: String?): Boolean {

        return id?.let {
            username?.let { it1 ->
                if (userRepository.findByUsername(it1) != null) return false
                userRepository.updateByIdAndUsername(it, it1)
            }
        } ?: false
    }

    override fun updatePasswordById(id: Long?, password: String?): Boolean {
        if (id == null || password == null) return false
        return userRepository.updateByIdAndPassword(id, PasswordUtil.encode(password))
    }

    override fun updateEmailById(id: Long?, email: String?): Boolean {
        return id?.let {
            email?.let { it1 ->
                if (userRepository.findByEmail(it1) != null) return false
                userRepository.updateByIdAndEmail(it, it1)
            }
        } ?: false
    }

    override fun updatePhoneById(id: Long?, phone: String?): Boolean {
        return id?.let {
            phone?.let { it1 ->
                if (userRepository.findByPhone(it1) != null) return false
                userRepository.updateByIdAndPhone(it, it1)
            }
        } ?: false
    }

    override fun updateSexById(id: Long?, sex: String?): Boolean {
        return id?.let { sex?.let { it1 -> userRepository.updateByIdAndSex(it, it1) } } ?: false
    }

    override fun updateWechatById(id: Long?, wechat: String?): Boolean {
        return id?.let { wechat?.let { it1 -> userRepository.updateByIdAndWechat(it, it1) } } ?: false
    }

    override fun updateQqById(id: Long?, qq: String?): Boolean {
        return id?.let { qq?.let { it1 -> userRepository.updateByIdAndQq(it, it1) } } ?: false
    }

    override fun updateAvatarById(id: Long?, avatar: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateBirthdayById(id: Long?, birthday: LocalDate?): Boolean {
        return id?.let { birthday?.let { it1 -> userRepository.updateByIdAndBirthday(it, it1) } } ?: false
    }

    override fun deleteUserById(id: Long?): Boolean {
        return id?.let {
            if (userRepository.existsById(it)) {
                userRepository.deleteById(it)
                true
            } else false
        } ?: false
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