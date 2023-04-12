package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.TagService
import la.shiro.birdapi.dal.TagRepository
import la.shiro.birdapi.model.entity.Tag
import la.shiro.birdapi.model.input.TagInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 10:01
 *  Description :
 */
@Service
@Transactional
class TagServiceImpl(
    private val tagRepository: TagRepository
) : TagService {
    override fun getTags(pageable: Pageable): Page<Tag>? {
        return tagRepository.findAll(pageable)
    }

    override fun getTagById(id: Long): Tag? {
        return tagRepository.findById(id).orElse(null)
    }

    override fun addTag(tagInput: TagInput?): Tag? {
        return tagInput?.let { tagRepository.insert(it) }
    }

    override fun updateTagById(id: Long, tagInput: TagInput?): Tag? {
        id.let { tagInput?.id = it }
        return tagInput?.let { tagRepository.update(it) }
    }

    override fun deleteTagById(id: Long): Boolean {
        return if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id)
            return true
        } else false
    }

    override fun deleteTagByIds(ids: List<Long>?): Int {
        var count = 0
        ids?.forEach {
            if (tagRepository.existsById(it)) {
                tagRepository.deleteById(it)
                count++
            }
        }
        return count
    }

}