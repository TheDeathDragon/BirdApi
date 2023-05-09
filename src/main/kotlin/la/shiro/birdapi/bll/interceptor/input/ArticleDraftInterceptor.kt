package la.shiro.birdapi.bll.interceptor.input

import la.shiro.birdapi.model.entity.Article
import la.shiro.birdapi.model.entity.ArticleDraft
import org.babyfish.jimmer.kt.isLoaded
import org.babyfish.jimmer.sql.DraftInterceptor
import org.springframework.stereotype.Component
import java.time.LocalDateTime
@Component
class ArticleDraftInterceptor: DraftInterceptor<ArticleDraft> {
    override fun beforeSave(draft: ArticleDraft, isNew: Boolean) {
        if (!isLoaded(draft, Article::auditTime)) {
            draft.auditTime = LocalDateTime.now()
        }
    }
}