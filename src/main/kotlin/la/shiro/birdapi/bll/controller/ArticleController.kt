package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.ArticleService
import la.shiro.birdapi.model.entity.Article
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.springframework.data.domain.Page
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.input.ArticleInput
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
        @RequestParam title: String?,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<Article>> {
        return ResponseWrapper.success(
            articleService.getArticleByTitle(title, PageRequest.of(pageIndex, pageSize))
        )
    }

    @GetMapping
    fun getArticles(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<Article>> {
        return ResponseWrapper.success(
            articleService.getArticles(PageRequest.of(pageIndex, pageSize))
        )
    }

    @GetMapping("/category/{categoryId}")
    fun getArticleByCategoryId(
        @PathVariable categoryId: Long?,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<Article>> {
        return ResponseWrapper.success(
            articleService.getArticlesByCategoryId(
                categoryId,
                PageRequest.of(pageIndex, pageSize)
            )
        )
    }

    @GetMapping("/hot")
    fun getHotArticles(): ApiResponse<List<Article>> {
        return ResponseWrapper.success(articleService.getHotArticles())
    }

    @GetMapping("/like/{id}")
    fun updateArticleLike(@PathVariable id: Long?): ApiResponse<Boolean> {
        return ResponseWrapper.success(articleService.updateArticleLike(id))
    }

    @PostMapping("/add")
    fun addArticle(@RequestBody articleInput: ArticleInput?): ApiResponse<Article> {
        return ResponseWrapper.success(articleService.addArticle(articleInput))
    }

    @PutMapping("/{id}")
    fun updateArticleById(@PathVariable id: Long,
                          @RequestBody articleInput: ArticleInput?): ApiResponse<Article> {
        return ResponseWrapper.success(articleService.updateArticleById(id, articleInput))
    }

    @DeleteMapping("/{id}")
    fun deleteArticleById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(articleService.deleteArticleById(id))
    }

    @DeleteMapping("/ids")
    fun deleteArticleByIds(@RequestParam ids: List<Long>): ApiResponse<Int> {
        return ResponseWrapper.success(articleService.deleteArticleByIds(ids))
    }
}