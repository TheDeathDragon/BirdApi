package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.ArticleImage
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 1:13
 *  Description :
 */
data class ArticleImageInput(
    @Schema(hidden = true)
    var id: Long?,
    var title: String?,
    @Schema(hidden = true)
    var url: String?,
    var articleId: Long?
) : Input<ArticleImage> {
    override fun toEntity(): ArticleImage = CONVERTER.toArticleImage(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toArticleImage(articleImageInput: ArticleImageInput): ArticleImage
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}