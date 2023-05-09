package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.NoticeService
import la.shiro.birdapi.dal.NoticeRepository
import la.shiro.birdapi.model.entity.Notice
import la.shiro.birdapi.model.input.NoticeInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 14:55
 *  Description :
 */
@Service
@Transactional
class NoticeServiceImpl(
    private val noticeRepository: NoticeRepository
) : NoticeService {
    override fun getNotices(pageable: Pageable): Page<Notice> {
        return noticeRepository.findAll(pageable)
    }

    override fun getNoticesCondition(pageable: Pageable, noticeInput: NoticeInput?): Page<Notice> {
        return noticeRepository.findNoticesCondition(pageable, noticeInput)
    }

    override fun getNoticeById(id: Long): Notice {
        return noticeRepository.findById(id).get()
    }

    override fun getNoticeLatest(): Notice? {
        return noticeRepository.findTop1ByOrderByIdDesc()
    }

    override fun addNotice(noticeInput: NoticeInput): Notice? {
        return noticeRepository.insert(noticeInput)
    }

    override fun updateNoticeById(id: Long, noticeInput: NoticeInput): Notice? {
        noticeInput.id = id
        return noticeRepository.update(noticeInput)
    }

    override fun deleteNoticeById(id: Long): Boolean {
        return if (noticeRepository.existsById(id)) {
            noticeRepository.deleteById(id)
            true
        } else false
    }

    override fun deleteNoticesByIds(ids: List<Long>?): Int {
        var count = 0
        ids?.forEach {
            if (noticeRepository.existsById(it)) {
                noticeRepository.deleteById(it)
                count++
            }
        }
        return count
    }
}