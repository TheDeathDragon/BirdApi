package la.shiro.birdapi.util

import la.shiro.birdapi.model.common.DEFAULT_IMG_COMPRESS_RATIO
import la.shiro.birdapi.model.common.DEFAULT_IMG_UPLOAD_MAX_RESOLUTION
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.resizers.Resizer
import org.springframework.stereotype.Component
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.nio.file.Path
import javax.imageio.IIOImage
import javax.imageio.ImageIO
import javax.imageio.ImageWriteParam
import javax.imageio.stream.FileImageOutputStream

/**
 *  author: ChatGpt-3.5
 *  Date: 23/4/11 02:00
 *  Description : 图片工具类
 */

@Component
class ImageUtil {


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
        maxResolution: Int = DEFAULT_IMG_UPLOAD_MAX_RESOLUTION,
        compressRatio: Float = DEFAULT_IMG_COMPRESS_RATIO
    ) {
        Thumbnails.of(destination.toFile())
//            .scale(0.8)
            .size(maxResolution, maxResolution)
            .keepAspectRatio(true)
            .outputFormat("jpg")
            .outputQuality(compressRatio)
            .toFile(destination.toFile())
    }

    companion object {
        fun getImgUrl(path: String): String? {
            return if (path.isNotEmpty()) {
                "http://localhost:8080$path"
            } else {
                null
            }
        }
    }
}

