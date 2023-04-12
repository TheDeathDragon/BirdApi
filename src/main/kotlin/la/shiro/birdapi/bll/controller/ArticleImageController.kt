package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.ArticleImageService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.ArticleImage
import la.shiro.birdapi.model.input.ArticleImageInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 1:38
 *  Description :
 */
@RestController
@RequestMapping("/article-image")
class ArticleImageController(
    private val articleImageService: ArticleImageService
) {

    @GetMapping
    fun getArticleImages(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<ArticleImage>> {
        return ResponseWrapper.success(
            articleImageService.getArticleImages(
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getArticleImage(@PathVariable id: Long): ApiResponse<ArticleImage> {
        return ResponseWrapper.success(articleImageService.getArticleImageById(id))
    }

    @PostMapping(
        value = ["/upload"],
        consumes = ["multipart/form-data"],
        produces = ["application/json"]
    )
    fun addArticleImage(
        @RequestPart(value = "file") file: MultipartFile,
        @RequestParam title: String?,
        @RequestParam articleId: Long?
    ): ApiResponse<ArticleImage> {
        if (file.isEmpty) {
            return ResponseWrapper.error("File is empty")
        }
        return ResponseWrapper.success(articleImageService.addArticleImage(title, articleId, file))
    }

    @PutMapping(
        value = ["/{id}"],
        consumes = ["multipart/form-data"],
        produces = ["application/json"]
    )
    fun updateArticleImage(
        @PathVariable id: Long,
        @RequestParam articleId: Long?,
        @RequestParam title: String?,
        @RequestPart(value = "file") file: MultipartFile
    ): ApiResponse<ArticleImage> {
        if (file.isEmpty) {
            return ResponseWrapper.error("File is empty")
        }
        val articleImageInput = ArticleImageInput(
            id = id,
            title = title,
            articleId = articleId,
            url = null,
            path = null
        )
        return ResponseWrapper.success(articleImageService.updateArticleImage(articleImageInput, file))
    }

    @DeleteMapping("/{id}")
    fun deleteArticleImage(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(articleImageService.deleteArticleImage(id))
    }

    @DeleteMapping("/article/{articleId}")
    fun deleteArticleImageByArticleId(@PathVariable articleId: Long): ApiResponse<Int> {
        return ResponseWrapper.success(articleImageService.deleteArticleImageByArticleId(articleId))
    }

    @DeleteMapping("/ids")
    fun deleteArticleImageByIds(@RequestParam ids: List<Long>): ApiResponse<Int> {
        return ResponseWrapper.success(articleImageService.deleteArticleImageByIds(ids))
    }
}