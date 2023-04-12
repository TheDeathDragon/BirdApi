package la.shiro.birdapi.util

import la.shiro.birdapi.model.common.DEFAULT_IMG_COMPRESS_RATIO
import la.shiro.birdapi.model.common.DEFAULT_ARTICLE_IMG_UPLOAD_MAX_RESOLUTION
import net.coobird.thumbnailator.Thumbnails
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.file.Path

/**
 *  author: ChatGpt-3.5
 *  Date: 23/4/11 02:00
 *  Description : 图片工具类
 */

@Component
object ImageUtil {


    /**
     * 将图片转换为 JPG 格式并压缩
     *
     * @param destination 目标文件
     * @param maxResolution 最大分辨率
     * @param compressRatio 压缩比例
     * @throws IOException
     */
    @Throws(IOException::class)
    fun convertToJpg(
        destination: Path,
        maxResolution: Int = DEFAULT_ARTICLE_IMG_UPLOAD_MAX_RESOLUTION,
        compressRatio: Float = DEFAULT_IMG_COMPRESS_RATIO,
        isSquare: Boolean = false
    ) {
        Thumbnails.of(destination.toFile())
//            .scale(0.8)
            .size(maxResolution, maxResolution)
            .keepAspectRatio(!isSquare)
            .outputFormat("jpg")
            .outputQuality(compressRatio)
            .toFile(destination.toFile())
    }


    fun getImgUrl(path: String): String? {
        return if (path.isNotEmpty()) {
            "http://localhost:8080$path"
        } else {
            null
        }
    }

}

