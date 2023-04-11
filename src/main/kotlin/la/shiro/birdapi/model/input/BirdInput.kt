package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.Bird
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 9:23
 *  Description :
 */
data class BirdInput(
    @Schema(hidden = true)
    var id: Long?,
    var species: Long?,
    var viewCount: Long?,
    var likeCount: Long?,
    var name: String,
    var enName: String?,
    var introduction: String?,
    var feature: String?,
    var resident: String?,
    var habit: String?,
    var distribution: String?,
    var reproduction: String?,
    var culturalSignificance: String?
) : Input<Bird> {
    override fun toEntity(): Bird = CONVERTER.toBird(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toBird(birdInput: BirdInput): Bird
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }

}