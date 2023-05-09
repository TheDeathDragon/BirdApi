package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.NoticeService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.Notice
import la.shiro.birdapi.model.input.NoticeInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 15:37
 *  Description :
 */
@RestController
@RequestMapping("/notice")
class NoticeController(
    private val noticeService: NoticeService
) {

    @GetMapping
    fun getNotices(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Notice>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            noticeService.getNotices(
                PageRequest.of(
                    pageIndex - 1,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getNoticeById(@PathVariable id: Long): ApiResponse<Notice> {
        return ResponseWrapper.success(noticeService.getNoticeById(id))
    }

    @GetMapping("/latest")
    fun getNoticeLatest(): ApiResponse<Notice> {
        return ResponseWrapper.success(noticeService.getNoticeLatest())
    }

    @PostMapping("/add")
    fun addNotice(noticeInput: NoticeInput): ApiResponse<Notice> {
        return ResponseWrapper.success(noticeService.addNotice(noticeInput))
    }

    @PostMapping("/{id}")
    fun updateNotice(@PathVariable id: Long, noticeInput: NoticeInput): ApiResponse<Notice> {
        return ResponseWrapper.success(noticeService.updateNoticeById(id, noticeInput))
    }

    @DeleteMapping("/{id}")
    fun deleteNotice(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(noticeService.deleteNoticeById(id))
    }

    @DeleteMapping("/ids")
    fun deleteNotices(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(noticeService.deleteNoticesByIds(ids))
    }

}