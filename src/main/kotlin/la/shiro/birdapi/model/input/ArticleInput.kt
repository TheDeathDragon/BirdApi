package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.Article
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 0:57
 *  Description :
 */
data class ArticleInput(
    @Schema(hidden = true)
    var id: Long?,
    var userId: Long?,
    var auditorId: Long?,
    var categoryId: Long?,
    var viewCount: Long?,
    var likeCount: Long?,
    var title: String?,
    var thumbnail: String?,
    var summary: String?,
    var content: String?,
    @Schema(defaultValue = "1")
    var status: String?,
    @Schema(defaultValue = "1")
    var top: String?,
    @Schema(defaultValue = "1")
    var comment: String?
) : Input<Article> {
    override fun toEntity(): Article = CONVERTER.toArticle(this)


    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toArticle(articleInput: ArticleInput): Article
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}