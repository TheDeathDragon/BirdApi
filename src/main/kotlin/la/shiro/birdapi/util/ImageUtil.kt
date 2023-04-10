package la.shiro.birdapi.util

import la.shiro.birdapi.model.common.DEFAULT_IMG_COMPRESS_RATIO
import la.shiro.birdapi.model.common.DEFAULT_IMG_UPLOAD_MAX_RESOLUTION
import org.springframework.stereotype.Component
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
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
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @throws IOException
     */
    @Throws(IOException::class)
    fun convertToJpg(
        srcImagePath: String,
        destImagePath: String,
        maxResolution: Int = DEFAULT_IMG_UPLOAD_MAX_RESOLUTION,
        compressRatio: Float = DEFAULT_IMG_COMPRESS_RATIO
    ) {
        // 读取源图片
        val srcImage = ImageIO.read(File(srcImagePath))

        // 判断图片分辨率是否超过最大限制
        var width = srcImage.width
        var height = srcImage.height
        val ratio = width.toFloat() / height
        if (width > maxResolution ||
            height > maxResolution ||
            ratio > 1.0f || ratio < 0.1f
        ) {
            // 如果分辨率超过最大限制，则先将图片缩小到最大分辨率
            if (width > height) {
                width = maxResolution
                height = (maxResolution / ratio).toInt()
            } else {
                height = maxResolution
                width = (maxResolution * ratio).toInt()
            }

            // 创建一个新的 BufferedImage 对象，并将源图片绘制到该对象上
            val scaledImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val g2d = scaledImage.createGraphics()
            g2d.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
            )
            g2d.drawImage(srcImage, 0, 0, width, height, null)
            g2d.dispose()

            // 保存 BufferedImage 对象到源图片文件
            ImageIO.write(scaledImage, "jpg", File(srcImagePath))
        }

        // 创建一个 BufferedImage 对象，并指定类型为 RGB
        val rgbImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        // 将源图片绘制到 BufferedImage 对象上
        val g2d = rgbImage.createGraphics()
        g2d.drawImage(srcImage, 0, 0, null)
        g2d.dispose()

        // 保存 BufferedImage 对象到目标图片文件
        ImageIO.write(rgbImage, "jpg", File(destImagePath))

        // 压缩目标图片
        val compressedImage = ImageIO.read(File(destImagePath))
        val compressedOutputStream = File(destImagePath)
        val jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next()
        val jpgWriteParams = jpgWriter.defaultWriteParam
        jpgWriteParams.compressionMode = ImageWriteParam.MODE_EXPLICIT
        jpgWriteParams.compressionQuality = compressRatio
        jpgWriter.output = FileImageOutputStream(compressedOutputStream)
        jpgWriter.write(
            null,
            IIOImage(compressedImage, null, null),
            jpgWriteParams
        )
    }
}

