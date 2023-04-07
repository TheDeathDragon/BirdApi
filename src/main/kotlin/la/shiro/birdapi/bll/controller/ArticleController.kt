package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.impl.ArticleService
import la.shiro.birdapi.util.ResponseWrapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun getArticleById(@PathVariable id: Long?): ResponseWrapper {
        return ResponseWrapper.success(articleService.getArticleById(id))
    }
}