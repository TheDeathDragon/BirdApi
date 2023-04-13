package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.ReportService
import la.shiro.birdapi.dal.ReportRepository
import la.shiro.birdapi.model.entity.Report
import la.shiro.birdapi.model.input.ReportInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/13 9:32
 *  Description :
 */
@Service
@Transactional
class ReportServiceImpl(
    private val reportRepository: ReportRepository
) : ReportService {
    override fun getReports(pageable: Pageable): Page<Report> {
        return reportRepository.findAll(pageable)
    }

    override fun getReportById(id: Long): Report? {
        return reportRepository.findById(id).orElse(null)
    }

    override fun addReport(reportInput: ReportInput): Report {
        return reportRepository.insert(reportInput)
    }

    override fun updateReportById(id: Long, reportInput: ReportInput): Report {
        reportInput.id = id
        return reportRepository.update(reportInput)
    }

    override fun updateReportStatusById(id: Long, status: String): Boolean {
        return reportRepository.updateStatusById(id, status)
    }

    override fun deleteReportById(id: Long): Boolean {
        return if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id)
            true
        } else false
    }

    override fun deleteReportsByIds(ids: List<Long>?): Int {
        var count = 0
        ids?.forEach {
            if (reportRepository.existsById(it)) {
                reportRepository.deleteById(it)
                count++
            }
        }
        return count
    }

}