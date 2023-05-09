package la.shiro.birdapi.model.common

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 0:42
 *  Description :
 */

// 默认分页起始页
const val DEFAULT_PAGE_INDEX = "1"

// 默认分页大小
const val DEFAULT_PAGE_SIZE = "10"

// 默认父级ID
const val DEFAULT_PID: Long = 0

// 默认热门文章数量
const val DEFAULT_ARTICLE_HOT_COUNT = 10

// 默认文章缩略图
const val DEFAULT_ARTICLE_IMG = "/images/default_thumbnail.jpg"

// 默认文章图片上传最大大小
const val DEFAULT_ARTICLE_IMG_UPLOAD_MAX_RESOLUTION = 1920

// 默认鸟类图片上传最大大小
const val DEFAULT_BIRD_IMG_UPLOAD_MAX_RESOLUTION = 2160

// 默认头像上传最大大小
const val DEFAULT_AVATAR_UPLOAD_MAX_RESOLUTION = 300

// 默认上传路径前缀 Windows
var DEFAULT_UPLOAD_PATH_PREFIX_WINDOWS = ""

// 默认上传路径前缀 Linux
var DEFAULT_UPLOAD_PATH_PREFIX_LINUX = ""

// 默认文章图片上传路径
const val DEFAULT_ARTICLE_IMG_UPLOAD_PATH = "/upload/article/"

// 默认鸟类图片上传路径
const val DEFAULT_BIRD_IMG_UPLOAD_PATH = "/upload/bird/"

// 默认头像上传路径
const val DEFAULT_AVATAR_UPLOAD_PATH = "/upload/avatar/"

// 默认图片压缩比
const val DEFAULT_IMG_COMPRESS_RATIO = 0.8f

// 默认文章浏览量
const val DEFAULT_ARTICLE_VIEW_COUNT: Long = 0

// 默认文章点赞量
const val DEFAULT_ARTICLE_LIKE_COUNT: Long = 0

// 默认文章分类
const val DEFAULT_ARTICLE_CATEGORY: Long = 0

// 评论状态-待审核
const val COMMENT_STATUS_PENDING_REVIEW = 0

// 评论状态-发布
const val COMMENT_STATUS_PUBLISH = 1

// 默认分页排序规则
const val DEFAULT_PAGE_SORT_RULE = "id desc"