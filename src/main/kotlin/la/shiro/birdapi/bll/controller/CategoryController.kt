package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.CategoryService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.Category
import la.shiro.birdapi.model.input.CategoryInput
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
@RequestMapping("/category")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    fun getCategories(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<Category>> {
        return ResponseWrapper.success(
            categoryService.getCategories(
                PageRequest.of(
                    pageIndex,
                    pageSize,
                    SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ApiResponse<Category> {
        return ResponseWrapper.success(categoryService.getCategoryById(id))
    }

    @GetMapping("/{pid}")
    fun getCategoriesByPid(@PathVariable pid: Long): ApiResponse<List<Category>> {
        return ResponseWrapper.success(categoryService.getCategoriesByPid(pid))
    }

    @PostMapping("/add")
    fun addCategory(@RequestBody categoryInput: CategoryInput): ApiResponse<Category> {
        return ResponseWrapper.success(categoryService.addCategory(categoryInput))
    }

    @PutMapping("/{id}")
    fun updateCategoryById(@PathVariable id: Long, @RequestBody categoryInput: CategoryInput): ApiResponse<Category> {
        return ResponseWrapper.success(categoryService.updateCategoryById(id, categoryInput))
    }

    @DeleteMapping("/{id}")
    fun deleteCategoryById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(categoryService.deleteCategoryById(id))
    }

    @DeleteMapping("/ids")
    fun deleteCategoriesByIds(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(categoryService.deleteCategoryByIds(ids))
    }
}
