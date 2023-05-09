package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.Notice
import la.shiro.birdapi.model.input.NoticeInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 14:52
 *  Description :
 */
interface NoticeService {
    fun getNotices(pageable: Pageable): Page<Notice>
    fun getNoticesCondition(pageable: Pageable, noticeInput: NoticeInput?): Page<Notice>
    fun getNoticeById(id: Long): Notice?

    fun getNoticeLatest(): Notice?

    fun addNotice(noticeInput: NoticeInput): Notice?

    fun updateNoticeById(id: Long, noticeInput: NoticeInput): Notice?

    fun deleteNoticeById(id: Long): Boolean

    fun deleteNoticesByIds(ids: List<Long>?): Int
}