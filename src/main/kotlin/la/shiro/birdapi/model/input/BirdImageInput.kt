package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.BirdImage
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 10:35
 *  Description :
 */
data class BirdImageInput(
    @Schema(hidden = true)
    var id: Long?,
    var title: String?,
    @Schema(hidden = true)
    var url: String?,
    var path: String?,
    var birdId: Long?
) : Input<BirdImage> {
    override fun toEntity(): BirdImage = CONVERTER.toBirdImage(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toBirdImage(birdImageInput: BirdImageInput): BirdImage
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}