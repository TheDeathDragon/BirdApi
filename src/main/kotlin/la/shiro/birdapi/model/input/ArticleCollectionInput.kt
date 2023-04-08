package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.ArticleCollection
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 16:38
 *  Description :
 */
data class ArticleCollectionInput(
    @Schema(hidden = true)
    var id: Long?,
    var userId: Long?,
    var articleId: Long?
) : Input<ArticleCollection> {
    override fun toEntity(): ArticleCollection = CONVERTER.toArticleCollection(this)


    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toArticleCollection(articleCollectionInput: ArticleCollectionInput): ArticleCollection
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }

}