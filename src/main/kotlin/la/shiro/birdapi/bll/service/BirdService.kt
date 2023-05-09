package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.Bird
import la.shiro.birdapi.model.input.BirdInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 9:28
 *  Description :
 */
interface BirdService {

    fun getBirdById(id: Long): Bird?

    fun getBirdByIds(ids: List<Long>?): List<Bird>?

    fun getBirdBySpecies(species: Long, pageable: Pageable): Page<Bird>?

    fun getBirds(pageable: Pageable): Page<Bird>

    fun getBirdsByName(name: String?, pageable: Pageable): Page<Bird>?

    fun getBirdsByEnName(enName: String?, pageable: Pageable): Page<Bird>?

    fun getHotBirds(): List<Bird>?

    fun addBird(birdInput: BirdInput?): Bird?

    fun updateBirdById(id: Long, birdInput: BirdInput?): Bird?

    fun updateBirdLike(id: Long): Boolean

    fun deleteBirdById(id: Long): Boolean

    fun deleteBirdByIds(ids: List<Long>?): Int
    fun getBirdsCondition(pageable: Pageable, birdInput: BirdInput?): Page<Bird>?

}