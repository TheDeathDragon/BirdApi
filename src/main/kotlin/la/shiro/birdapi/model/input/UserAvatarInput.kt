package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.UserAvatar
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 16:03
 *  Description :
 */
data class UserAvatarInput(
    @Schema(hidden = true)
    var id: Long?,
    var userId: Long?,
    @Schema(hidden = true)
    var url: String?,
    @Schema(hidden = true)
    var path: String?
) : Input<UserAvatar> {
    override fun toEntity(): UserAvatar = CONVERTER.toUserAvatar(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toUserAvatar(userAvatarInput: UserAvatarInput): UserAvatar
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}