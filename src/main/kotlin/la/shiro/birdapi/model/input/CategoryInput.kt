package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.Category
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 9:39
 *  Description :
 */
data class CategoryInput(
    @Schema(hidden = true)
    var id: Long,
    var pid: Long,
    var name: String,
    var description: String?
) : Input<Category> {

    override fun toEntity(): Category = CONVERTER.toCategory(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toCategory(categoryInput: CategoryInput): Category
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}