package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.BirdImageService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.BirdImage
import la.shiro.birdapi.model.input.BirdImageInput
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
@RequestMapping("/bird-image")
class BirdImageController(
    private val birdImageService: BirdImageService
) {

    @GetMapping
    fun getBirdImages(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<BirdImage>> {
        if (pageIndex < 1) {
            return ResponseWrapper.error("当前页数不能小于1")
        }
        return ResponseWrapper.success(
            birdImageService.getBirdImages(
                PageRequest.of(
                    pageIndex - 1,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getBirdImage(@PathVariable id: Long): ApiResponse<BirdImage> {
        return ResponseWrapper.success(birdImageService.getBirdImageById(id))
    }

    @PostMapping(
        value = ["/upload"],
        consumes = ["multipart/form-data"],
        produces = ["application/json"]
    )
    fun addBirdImage(
        @RequestPart(value = "file") file: MultipartFile,
        @RequestParam title: String?,
        @RequestParam birdId: Long?
    ): ApiResponse<BirdImage> {
        if (file.isEmpty) {
            return ResponseWrapper.error("File is empty")
        }
        return ResponseWrapper.success(birdImageService.addBirdImage(title, birdId, file))
    }

    @PutMapping(
        value = ["/{id}"],
        consumes = ["multipart/form-data"],
        produces = ["application/json"]
    )
    fun updateBirdImage(
        @PathVariable id: Long,
        @RequestParam birdId: Long?,
        @RequestParam title: String?,
        @RequestPart(value = "file") file: MultipartFile
    ): ApiResponse<BirdImage> {
        if (file.isEmpty) {
            return ResponseWrapper.error("File is empty")
        }
        val birdImageInput = BirdImageInput(
            id = id,
            birdId = birdId,
            title = title,
            path = null,
            url = null
        )
        return ResponseWrapper.success(birdImageService.updateBirdImage(birdImageInput, file))
    }

    @DeleteMapping("/{id}")
    fun deleteBirdImage(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(birdImageService.deleteBirdImage(id))
    }

    @DeleteMapping("/bird/{birdId}")
    fun deleteBirdImageByBirdId(@PathVariable birdId: Long): ApiResponse<Int> {
        return ResponseWrapper.success(birdImageService.deleteBirdImageByBirdId(birdId))
    }

    @DeleteMapping("/ids")
    fun deleteBirdImageByIds(@RequestParam ids: List<Long>): ApiResponse<Int> {
        return ResponseWrapper.success(birdImageService.deleteBirdImageByIds(ids))
    }
}