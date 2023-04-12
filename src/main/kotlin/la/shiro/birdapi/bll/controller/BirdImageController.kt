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
        return ResponseWrapper.success(
            birdImageService.getBirdImages(
                PageRequest.of(
                    pageIndex,
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

    @PutMapping("/{id}")
    fun updateBirdImage(
        @RequestBody birdImageInput: BirdImageInput
    ): ApiResponse<BirdImage> {
        return ResponseWrapper.success(birdImageService.updateBirdImage(birdImageInput))
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
    fun deleteBirdImageByIds(@RequestBody ids: List<Long>): ApiResponse<Int> {
        return ResponseWrapper.success(birdImageService.deleteBirdImageByIds(ids))
    }
}