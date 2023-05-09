package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.ArticleCollectionService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.ArticleCollection
import la.shiro.birdapi.model.input.ArticleCollectionInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 16:54
 *  Description :
 */
@RestController
@RequestMapping("/article-collection")
class ArticleCollectionController(
    private val articleCollectionService: ArticleCollectionService
) {

    @GetMapping
    fun getArticleCollections(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String,
    ): ApiResponse<Page<ArticleCollection>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            articleCollectionService.getArticleCollections(
                PageRequest.of(
                    pageIndex - 1,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getArticleCollectionById(@PathVariable id: Long): ApiResponse<ArticleCollection> {
        return ResponseWrapper.success(articleCollectionService.getArticleCollectionById(id))
    }

    @GetMapping("/ids")
    fun getArticleCollectionByIds(@RequestParam ids: List<Long>?): ApiResponse<List<ArticleCollection>> {
        return ResponseWrapper.success(articleCollectionService.getArticleCollectionByIds(ids))
    }

    @GetMapping("/user/{userId}")
    fun getArticleCollectionByUserId(@PathVariable userId: Long): ApiResponse<List<ArticleCollection>> {
        return ResponseWrapper.success(articleCollectionService.getArticleCollectionByUserId(userId))
    }

    @GetMapping("/article/{articleId}")
    fun getArticleCollectionByArticleId(@PathVariable articleId: Long): ApiResponse<List<ArticleCollection>> {
        return ResponseWrapper.success(articleCollectionService.getArticleCollectionByArticleId(articleId))
    }

    @PostMapping("/add")
    fun createArticleCollection(@RequestBody articleCollectionInput: ArticleCollectionInput): ApiResponse<ArticleCollection> {
        return ResponseWrapper.success(articleCollectionService.addArticleCollection(articleCollectionInput))
    }

    @PutMapping("/{id}")
    fun updateArticleCollectionById(
        @PathVariable id: Long,
        @RequestBody articleCollectionInput: ArticleCollectionInput
    ): ApiResponse<ArticleCollection> {
        return ResponseWrapper.success(articleCollectionService.updateArticleCollectionById(id, articleCollectionInput))
    }

    @DeleteMapping("/{id}")
    fun deleteArticleCollectionById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(articleCollectionService.deleteArticleCollectionById(id))
    }

    @DeleteMapping("/ids")
    fun deleteArticleCollectionByIds(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(articleCollectionService.deleteArticleCollectionByIds(ids))
    }


}