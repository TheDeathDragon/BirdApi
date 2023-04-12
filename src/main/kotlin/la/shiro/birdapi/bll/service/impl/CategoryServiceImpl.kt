package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.CategoryService
import la.shiro.birdapi.dal.CategoryRepository
import la.shiro.birdapi.model.entity.Category
import la.shiro.birdapi.model.input.CategoryInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 9:46
 *  Description :
 */
@Service
@Transactional
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
) : CategoryService {
    override fun getCategoryById(id: Long): Category? {
        return categoryRepository.findById(id).orElse(null)
    }

    override fun addCategory(categoryInput: CategoryInput?): Category? {
        return categoryInput?.let { categoryRepository.insert(it) }
    }

    override fun updateCategoryById(id: Long, categoryInput: CategoryInput?): Category? {
        categoryInput?.id = id
        return categoryInput?.let { categoryRepository.update(categoryInput) }
    }

    override fun deleteCategoryById(id: Long): Boolean {
        return if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id)
            true
        } else false
    }

    override fun deleteCategoryByIds(ids: List<Long>?): Int {
        var count = 0
        ids?.forEach {
            if (categoryRepository.existsById(it)) {
                categoryRepository.deleteById(it)
                count++
            }
        }
        return count
    }

    override fun getCategories(pageable: Pageable): Page<Category>? {
        return categoryRepository.findAll(pageable)
    }

    override fun getCategoriesByPid(pid: Long): List<Category>? {
        return categoryRepository.getCategoriesByPid(pid)
    }

}