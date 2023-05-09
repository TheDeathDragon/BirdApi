package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.*
import la.shiro.birdapi.model.input.UserInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate

/**
 * <p>
 * UserRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface UserRepository : KRepository<User, Long> {
    fun findByUsername(username: String): User?

    fun findByEmail(email: String): User?

    fun findByPhone(phone: String): User?

    fun updateById(userId: Long, userInput: UserInput): Boolean {
        val count = sql.createUpdate(User::class) {
            update(userInput)
            where(table.id eq userId)
        }.execute()
        return count == 1
    }

    fun updateByIdAndUsername(id: Long, username: String): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.username, username)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun updateByIdAndPassword(id: Long, password: String): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.password, password)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun updateByIdAndEmail(id: Long, email: String): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.email, email)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun updateByIdAndPhone(id: Long, phone: String): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.phone, phone)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun updateByIdAndSex(id: Long, sex: String): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.sex, sex)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun updateByIdAndWechat(id: Long, wechat: String): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.wechat, wechat)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun updateByIdAndQq(id: Long, qq: String): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.qq, qq)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun updateByIdAndBirthday(id: Long, birthday: LocalDate): Boolean {
        val count = sql.createUpdate(User::class) {
            set(table.birthday, birthday)
            where(table.id eq id)
        }.execute()
        return count == 1
    }

    fun findUsersCondition(pageable: Pageable, userInput: UserInput?): Page<User>? {
        if (userInput == null) {
            return findAll(pageable)
        }

        val username = userInput.username
        val email = userInput.email
        val phone = userInput.phone
        val wechat = userInput.wechat
        val qq = userInput.qq

        return pager(pageable).execute(
            sql.createQuery(User::class) {
                username?.takeIf { it.isNotBlank() }?.let {
                    where(table.username eq username)
                }
                email?.takeIf { it.isNotBlank() }?.let {
                    where(table.email eq email)
                }
                phone?.takeIf { it.isNotBlank() }?.let {
                    where(table.phone eq phone)
                }
                wechat?.takeIf { it.isNotBlank() }?.let {
                    where(table.wechat eq wechat)
                }
                qq?.takeIf { it.isNotBlank() }?.let {
                    where(table.qq eq qq)
                }
                orderBy(table.id.desc())
                select(table)
            }
        )
    }
}

