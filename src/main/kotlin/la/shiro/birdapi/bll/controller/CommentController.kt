package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.CommentService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.Comment
import la.shiro.birdapi.model.input.CommentInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 14:13
 *  Description :
 */
@RestController
@RequestMapping("/comment")
class CommentController(
    private val commentService: CommentService,
) {

    @GetMapping
    fun getComments(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Comment>> {
        return ResponseWrapper.success(
            commentService.getComments(
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): ApiResponse<Comment> {
        return ResponseWrapper.success(commentService.getCommentById(id))
    }

    @GetMapping("/article")
    fun getCommentsByArticleId(
        @RequestParam articleId: Long,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Comment>> {
        return ResponseWrapper.success(
            commentService.getCommentsByArticleId(
                articleId,
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/user")
    fun getCommentsByUserId(
        @RequestParam userId: Long,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Comment>> {
        return ResponseWrapper.success(
            commentService.getCommentsByUserId(
                userId,
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/pid")
    fun getCommentsByPid(
        @RequestParam pid: Long,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Comment>> {
        return ResponseWrapper.success(
            commentService.getCommentsByPid(
                pid,
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/published")
    fun getCommentsByPublished(
        @RequestParam published: String,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Comment>> {
        return ResponseWrapper.success(
            commentService.getCommentsByPublished(
                published,
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @PostMapping("/add")
    fun addComment(@RequestBody commentInput: CommentInput): ApiResponse<Comment> {
        return ResponseWrapper.success(commentService.addComment(commentInput))
    }

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody commentInput: CommentInput): ApiResponse<Comment> {
        return ResponseWrapper.success(commentService.updateCommentById(id, commentInput))
    }

    @PutMapping("/like")
    fun likeComment(@RequestParam id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(commentService.updateCommentLikeCountById(id))
    }

    @PutMapping("/published")
    fun updateCommentStatus(@RequestParam id: Long, @RequestParam published: String): ApiResponse<Boolean> {
        return ResponseWrapper.success(commentService.updateCommentPublishedById(id, published))
    }

    @DeleteMapping("/{id}")
    fun deleteCommentById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(commentService.deleteCommentById(id))
    }

    @DeleteMapping("/article")
    fun deleteCommentsByArticleId(@RequestParam articleId: Long): ApiResponse<Long> {
        return ResponseWrapper.success(commentService.deleteCommentsByArticleId(articleId))
    }

    @DeleteMapping("/user")
    fun deleteCommentsByUserId(@RequestParam userId: Long): ApiResponse<Long> {
        return ResponseWrapper.success(commentService.deleteCommentsByUserId(userId))
    }

    @DeleteMapping("/pid")
    fun deleteCommentsByPid(@RequestParam pid: Long): ApiResponse<Long> {
        return ResponseWrapper.success(commentService.deleteCommentsByPid(pid))
    }

    @DeleteMapping("/ids")
    fun deleteCommentsByIds(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(commentService.deleteCommentsByIds(ids))
    }

}