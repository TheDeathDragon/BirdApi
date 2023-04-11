package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.BirdService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.entity.Bird
import la.shiro.birdapi.model.input.BirdInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 10:07
 *  Description :
 */
@RestController
@RequestMapping("/bird")
class BirdController(
    private val birdService: BirdService
) {
    @GetMapping("/{id}")
    fun getBirdById(@PathVariable id: Long): ApiResponse<Bird> {
        return ResponseWrapper.success(birdService.getBirdById(id))
    }

    @GetMapping("/ids")
    fun getBirdByIds(@RequestParam ids: List<Long>?): ApiResponse<List<Bird>> {
        return ResponseWrapper.success(birdService.getBirdByIds(ids))
    }

    @GetMapping("/species/{species}")
    fun getBirdBySpecies(
        @PathVariable species: Long?,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<Bird>> {
        return ResponseWrapper.success(
            birdService.getBirdBySpecies(
                species,
                PageRequest.of(pageIndex, pageSize)
            )
        )
    }

    @GetMapping
    fun getBirds(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<Bird>> {
        return ResponseWrapper.success(
            birdService.getBirds(PageRequest.of(pageIndex, pageSize))
        )
    }

    @GetMapping("/name")
    fun getBirdByName(
        @RequestParam name: String?,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<Bird>> {
        return ResponseWrapper.success(
            birdService.getBirdsByName(name, PageRequest.of(pageIndex, pageSize))
        )
    }

    @GetMapping("/enName")
    fun getBirdByEnName(
        @RequestParam enName: String?,
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<Bird>> {
        return ResponseWrapper.success(
            birdService.getBirdsByEnName(enName, PageRequest.of(pageIndex, pageSize))
        )
    }

    @GetMapping("/hot")
    fun getHotBirds(
    ): ApiResponse<List<Bird>> {
        return ResponseWrapper.success(
            birdService.getHotBirds()
        )
    }

    @PostMapping("/add")
    fun addBird(@RequestBody birdInput: BirdInput): ApiResponse<Bird> {
        return ResponseWrapper.success(birdService.addBird(birdInput))
    }

    @PutMapping("/{id}")
    fun updateBirdById(@PathVariable id: Long, @RequestBody birdInput: BirdInput): ApiResponse<Bird> {
        return ResponseWrapper.success(birdService.updateBirdById(id, birdInput))
    }

    @PutMapping("/like/{id}")
    fun updateBirdLikeById(@PathVariable id: Long?): ApiResponse<Boolean> {
        return ResponseWrapper.success(birdService.updateBirdLike(id))
    }

    @DeleteMapping("/{id}")
    fun deleteBirdById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(birdService.deleteBirdById(id))
    }

    @DeleteMapping("/ids")
    fun deleteBirdsByIds(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(birdService.deleteBirdByIds(ids))
    }

}