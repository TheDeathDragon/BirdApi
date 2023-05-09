package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.Category
import la.shiro.birdapi.model.input.CategoryInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 9:46
 *  Description :
 */
interface CategoryService {
    fun getCategoryById(id: Long): Category?

    fun addCategory(categoryInput: CategoryInput?): Category?

    fun updateCategoryById(id: Long, categoryInput: CategoryInput?): Category?

    fun deleteCategoryById(id: Long): Boolean

    fun deleteCategoryByIds(ids: List<Long>?): Int

    fun getCategories(pageable: Pageable): Page<Category>?

    fun getCategoriesCondition(pageable: Pageable, categoryInput: CategoryInput?): Page<Category>?

    fun getCategoriesByPid(pid: Long): List<Category>?

}