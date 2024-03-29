package la.shiro.birdapi.bll.controller

import io.swagger.v3.oas.annotations.media.Schema
import la.shiro.birdapi.bll.service.ArticleService
import la.shiro.birdapi.model.entity.Article
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.springframework.data.domain.Page
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.input.ArticleInput
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 1:26
 *  Description :
 */
@RestController
@RequestMapping("/article")
class ArticleController(
    private val articleService: ArticleService
) {
    @GetMapping("/{id}")
    fun getArticleById(@PathVariable id: Long): ApiResponse<Article> {
        return ResponseWrapper.success(articleService.getArticleById(id))
    }

    @GetMapping("/ids")
    fun getArticleByIds(@RequestParam ids: List<Long>?): ApiResponse<List<Article>> {
        return ResponseWrapper.success(articleService.getArticleByIds(ids))
    }

    @GetMapping("/title")
    fun getArticleByTitle(
        @RequestParam title: String,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Article>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            articleService.getArticleByTitle(title, PageRequest.of(pageIndex - 1, pageSize, SortUtils.toSort(sortCode)))
        )
    }

    @GetMapping
    fun getArticles(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = "top desc, id desc") sortCode: String
    ): ApiResponse<Page<Article>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            articleService.getArticles(PageRequest.of(pageIndex - 1, pageSize, SortUtils.toSort(sortCode)))
        )
    }

    @PostMapping
    fun getArticlesCondition(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = "top desc, id desc") sortCode: String,
        @RequestBody articleInput: ArticleInput?
    ): ApiResponse<Page<Article>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            articleService.getArticlesCondition(
                PageRequest.of(pageIndex - 1, pageSize, SortUtils.toSort(sortCode)),
                articleInput
            )
        )
    }

    @GetMapping("/category/{categoryId}")
    fun getArticleByCategoryId(
        @PathVariable categoryId: Long,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Article>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            articleService.getArticlesByCategoryId(
                categoryId,
                PageRequest.of(pageIndex - 1, pageSize, SortUtils.toSort(sortCode))
            )
        )
    }

    @GetMapping("/hot")
    fun getHotArticles(): ApiResponse<List<Article>> {
        return ResponseWrapper.success(articleService.getHotArticles())
    }

    @GetMapping("/like/{id}")
    fun updateArticleLike(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(articleService.updateArticleLike(id))
    }

    @PostMapping("/add")
    fun addArticle(@RequestBody articleInput: ArticleInput): ApiResponse<Article> {
        return ResponseWrapper.success(articleService.addArticle(articleInput))
    }

    @PutMapping("/{id}")
    fun updateArticleById(
        @PathVariable id: Long,
        @RequestBody articleInput: ArticleInput
    ): ApiResponse<Article> {
        return ResponseWrapper.success(articleService.updateArticleById(id, articleInput))
    }

    @DeleteMapping("/{id}")
    fun deleteArticleById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(articleService.deleteArticleById(id))
    }

    @DeleteMapping("/ids")
    fun deleteArticleByIds(@RequestBody idsMap: Map<String, List<Long>>): ApiResponse<Int> {
        idsMap["ids"]?.let {
            return ResponseWrapper.success(articleService.deleteArticleByIds(it))
        }
        return ResponseWrapper.error("参数错误")
    }
}