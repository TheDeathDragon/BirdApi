package la.shiro.birdapi.model.input

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.model.entity.Report
import org.babyfish.jimmer.Input
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 *  author: Rin Shiro
 *  Date: 23/4/13 9:26
 *  Description :
 */
data class ReportInput(
    @Schema(hidden = true)
    var id: Long,
    var reportedId: Long,
    var userId: Long?,
    var type: String,
    @Schema(defaultValue = "0")
    var status: String,
    var content: String
) : Input<Report> {

    override fun toEntity(): Report = CONVERTER.toReport(this)

    @Mapper
    internal interface Converter {
        @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
        fun toReport(reportInput: ReportInput): Report
    }

    companion object {
        @JvmStatic
        private val CONVERTER = Mappers.getMapper(Converter::class.java)
    }
}