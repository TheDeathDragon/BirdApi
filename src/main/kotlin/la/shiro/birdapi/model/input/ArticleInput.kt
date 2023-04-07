package la.shiro.birdapi.model.input

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
    val id: Long?,
    val userId: Long?,
    val auditorId: Long?,
    val categoryId: Long?,
    val viewCount: Long?,
    val likeCount: Long?,
    val title: String?,
    val thumbnail: String?,
    val summary: String?,
    val content: String?,
    val status: Int?,
    val isTop: Int?,
    val isComment: Int?
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