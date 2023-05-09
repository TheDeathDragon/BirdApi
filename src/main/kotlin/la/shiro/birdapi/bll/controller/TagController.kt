package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.TagService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.Tag
import la.shiro.birdapi.model.input.TagInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

/**
 * author: Rin Shiro
 * Date: 23/4/12 10:24
 * Description :
 */
@RestController
@RequestMapping("/tag")
class TagController(
    private val tagService: TagService
) {
    @GetMapping
    fun getTags(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Tag>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            tagService.getTags(
                PageRequest.of(
                    pageIndex - 1,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getTagById(@PathVariable id: Long): ApiResponse<Tag> {
        return ResponseWrapper.success(tagService.getTagById(id))
    }

    @PostMapping("/add")
    fun addTag(@RequestBody tagInput: TagInput?): ApiResponse<Tag> {
        return ResponseWrapper.success(tagService.addTag(tagInput))
    }

    @PutMapping("/{id}")
    fun updateTagById(@PathVariable id: Long, @RequestBody tagInput: TagInput?): ApiResponse<Tag> {
        return ResponseWrapper.success(tagService.updateTagById(id, tagInput))
    }

    @DeleteMapping("/{id}")
    fun deleteTagById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(tagService.deleteTagById(id))
    }

    @DeleteMapping("/ids")
    fun deleteTagByIds(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(tagService.deleteTagByIds(ids))
    }

}
