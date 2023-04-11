package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.BirdImage
import la.shiro.birdapi.model.input.BirdImageInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 10:35
 *  Description :
 */
interface BirdImageService {

    fun getBirdImages(pageable: Pageable): Page<BirdImage>?

    fun getBirdImageById(id: Long): BirdImage?

    fun addBirdImage(imageTitle: String?, birdId: Long?, file: MultipartFile): BirdImage?

    fun updateBirdImage(birdImageInput: BirdImageInput?): BirdImage?

    fun deleteBirdImage(id: Long): Boolean

    fun deleteBirdImageByBirdId(birdId: Long): Int

    fun deleteBirdImageByIds(ids: List<Long>): Int
}