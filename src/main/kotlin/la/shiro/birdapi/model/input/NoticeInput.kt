package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.Notice
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 14:47
 *  Description :
 */
data class NoticeInput(
    @Schema(hidden = true)
    var id: Long?,
    var title: String?,
    var content: String?
) : Input<Notice> {

    override fun toEntity(): Notice = CONVERTER.toNotice(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toNotice(noticeInput: NoticeInput): Notice
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}