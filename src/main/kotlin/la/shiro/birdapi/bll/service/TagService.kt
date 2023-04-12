package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.Tag
import la.shiro.birdapi.model.input.TagInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 10:00
 *  Description :
 */
interface TagService {
    fun getTags(pageable: Pageable): Page<Tag>?

    fun getTagById(id: Long): Tag?

    fun addTag(tagInput: TagInput?): Tag?

    fun updateTagById(id: Long, tagInput: TagInput?): Tag?

    fun deleteTagById(id: Long): Boolean

    fun deleteTagByIds(ids: List<Long>?): Int

}