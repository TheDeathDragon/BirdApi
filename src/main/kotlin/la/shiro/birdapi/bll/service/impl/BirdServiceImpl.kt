package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.BirdService
import la.shiro.birdapi.dal.BirdImageRepository
import la.shiro.birdapi.dal.BirdRepository
import la.shiro.birdapi.model.entity.Bird
import la.shiro.birdapi.model.input.BirdInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 9:28
 *  Description :
 */
@Transactional
@Service
class BirdServiceImpl(
    private val birdRepository: BirdRepository,
    private val birdImageRepository: BirdImageRepository
) : BirdService {
    override fun getBirdById(id: Long): Bird? {
        val bird = birdRepository.findById(id).orElse(null)
        bird?.let {
            birdRepository.updateViewCountById(it.id)
        }
        return bird
    }

    override fun getBirdByIds(ids: List<Long>?): List<Bird>? {
        return ids?.let { birdRepository.findByIds(it) }
    }

    override fun getBirdBySpecies(species: Long, pageable: Pageable): Page<Bird>? {
        return birdRepository.findBySpeciesOrderByIdDesc(species, pageable)
    }

    override fun getBirds(pageable: Pageable): Page<Bird> {
        return birdRepository.findAll(pageable)
    }

    override fun getBirdsCondition(pageable: Pageable, birdInput: BirdInput?): Page<Bird>? {
        return birdRepository.findBirdsCondition(pageable, birdInput)
    }

    override fun getBirdsByName(name: String?, pageable: Pageable): Page<Bird>? {
        return name?.let { birdRepository.findByNameContainingOrderByIdDesc(it, pageable) }
    }

    override fun getBirdsByEnName(enName: String?, pageable: Pageable): Page<Bird>? {
        return enName?.let { birdRepository.findByEnNameContainingOrderByIdDesc(it, pageable) }
    }

    override fun getHotBirds(): List<Bird>? {
        return birdRepository.findTop10ByOrderByViewCountDesc()
    }

    override fun addBird(birdInput: BirdInput?): Bird? {
        return birdInput?.let { birdRepository.save(it.toEntity()) }
    }

    override fun updateBirdById(id: Long, birdInput: BirdInput?): Bird? {
        return birdInput?.let {
            birdInput.id = id
            birdRepository.update(birdInput)
        }
    }

    override fun updateBirdLike(id: Long): Boolean {
        return birdRepository.updateLikeCountById(id)
    }

    override fun deleteBirdById(id: Long): Boolean {
        return if (birdRepository.existsById(id)) {
            birdRepository.deleteById(id)
            birdImageRepository.deleteBirdImageByBirdId(id)
            return true
        } else false
    }

    override fun deleteBirdByIds(ids: List<Long>?): Int {
        return ids?.let {
            var count = 0
            ids.forEach { id ->
                if (birdRepository.existsById(id)) {
                    birdRepository.deleteById(id)
                    birdImageRepository.deleteBirdImageByBirdId(id)
                    count++
                }
            }
            count
        } ?: 0
    }

}