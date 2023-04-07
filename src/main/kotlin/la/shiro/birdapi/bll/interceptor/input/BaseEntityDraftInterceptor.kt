package la.shiro.birdapi.bll.interceptor.input

import la.shiro.birdapi.model.common.BaseEntity
import la.shiro.birdapi.model.common.BaseEntityDraft
import org.babyfish.jimmer.kt.isLoaded
import org.babyfish.jimmer.sql.DraftInterceptor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BaseEntityDraftInterceptor : DraftInterceptor<BaseEntityDraft> {

    override fun beforeSave(draft: BaseEntityDraft, isNew: Boolean) {
        if (!isLoaded(draft, BaseEntity::updateTime)) {
            draft.updateTime = LocalDateTime.now()
        }
        if (isNew && !isLoaded(draft, BaseEntity::createTime)) {
            draft.createTime = LocalDateTime.now()
        }
    }
}