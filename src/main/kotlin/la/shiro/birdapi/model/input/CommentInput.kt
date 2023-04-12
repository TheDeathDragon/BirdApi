package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import org.babyfish.jimmer.Input
import la.shiro.birdapi.model.entity.Comment
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 13:44
 *  Description :
 */
data class CommentInput(
    @Schema(hidden = true)
    var id: Long?,
    @Schema(defaultValue = "0")
    var articleId: Long?,
    @Schema(defaultValue = "0")
    var userId: Long?,
    @Schema(defaultValue = "0")
    var pid: Long?,
    @Schema(defaultValue = "0")
    var likeCount: Long?,
    var content: String?,
    @Schema(defaultValue = "1")
    var published: String?,
) : Input<Comment> {

    override fun toEntity(): Comment = CONVERTER.toComment(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toComment(commentInput: CommentInput): Comment
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}