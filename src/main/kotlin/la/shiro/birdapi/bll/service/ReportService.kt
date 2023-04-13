package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.Report
import la.shiro.birdapi.model.input.ReportInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/13 9:28
 *  Description :
 */
interface ReportService {
    fun getReports(pageable: Pageable): Page<Report>

    fun getReportById(id: Long): Report?

    fun addReport(reportInput: ReportInput): Report

    fun updateReportById(id: Long, reportInput: ReportInput): Report

    fun updateReportStatusById(id: Long, status: String): Boolean

    fun deleteReportById(id: Long): Boolean

    fun deleteReportsByIds(ids: List<Long>?): Int




}