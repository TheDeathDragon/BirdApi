package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.BirdImageService
import la.shiro.birdapi.dal.BirdImageRepository
import la.shiro.birdapi.model.entity.BirdImage
import la.shiro.birdapi.model.input.BirdImageInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 10:36
 *  Description :
 */
@Transactional
@Service
class BirdImageServiceImpl(
    private val birdImageRepository: BirdImageRepository
) : BirdImageService {
    override fun getBirdImages(pageable: Pageable): Page<BirdImage>? {
        TODO("Not yet implemented")
    }

    override fun getBirdImageById(id: Long): BirdImage? {
        TODO("Not yet implemented")
    }

    override fun addBirdImage(imageTitle: String?, articleId: Long?, file: MultipartFile): BirdImage? {
        TODO("Not yet implemented")
    }

    override fun updateBirdImage(birdImageInput: BirdImageInput?): BirdImage? {
        TODO("Not yet implemented")
    }

    override fun deleteBirdImage(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteBirdImageByArticleId(articleId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteBirdImageByIds(ids: List<Long>): Int {
        TODO("Not yet implemented")
    }
}
