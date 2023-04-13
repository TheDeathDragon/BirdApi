package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.ReportService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.Report
import la.shiro.birdapi.model.input.ReportInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

/**
 *  author: Rin Shiro
 *  Date: 23/4/13 9:49
 *  Description :
 */
@RestController
@RequestMapping("/report")
class ReportController(
    private val reportService: ReportService
) {

    @GetMapping
    fun getReports(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Report>> {
        return ResponseWrapper.success(
            reportService.getReports(
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getReportById(@PathVariable id: Long): ApiResponse<Report> {
        return ResponseWrapper.success(reportService.getReportById(id))
    }

    @PostMapping("/add")
    fun addReport(@RequestBody reportInput: ReportInput): ApiResponse<Report> {
        return ResponseWrapper.success(reportService.addReport(reportInput))
    }

    @PutMapping("/{id}")
    fun updateReportById(
        @PathVariable id: Long,
        @RequestBody reportInput: ReportInput
    ): ApiResponse<Report> {
        return ResponseWrapper.success(reportService.updateReportById(id, reportInput))
    }

    @PutMapping("/{id}/status")
    fun updateReportStatusById(
        @PathVariable id: Long,
        @RequestParam status: String
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(reportService.updateReportStatusById(id, status))
    }

    @DeleteMapping("/{id}")
    fun deleteReportById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(reportService.deleteReportById(id))
    }

    @DeleteMapping
    fun deleteReportsByIds(@RequestParam ids: List<Long>): ApiResponse<Int> {
        return ResponseWrapper.success(reportService.deleteReportsByIds(ids))
    }
}