package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.User
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers
import java.time.LocalDate

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 9:28
 *  Description :
 */
data class UserInput(
    @Schema(hidden = true)
    var id: Long?,
    var username: String?,
    var email: String?,
    var phone: String?,
    var password: String?,
    var sex: String?,
    var wechat: String?,
    var qq: String?,
    var avatar: String?,
    var birthday: LocalDate?
) : Input<User> {
    override fun toEntity(): User = CONVERTER.toUser(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toUser(userInput: UserInput): User
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }

}