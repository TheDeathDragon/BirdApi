package la.shiro.birdapi.util

import la.shiro.birdapi.model.common.*
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

/**
 *  author: ChatGpt-3.5
 *  Date: 23/4/11 02:00
 *  Description : 文件上传工具类
 */

@Component
object FileUtils {

    /**
     * 上传文件到指定目录
     *
     * @param file 上传的文件
     * @param uploadPath 上传的目录
     * @return 上传后的文件路径
     * @throws IOException
     */
    @Throws(IOException::class)
    fun uploadFile(file: MultipartFile, uploadPath: String): String {
        val pathPrefix = when (getOS()) {
            "Windows" -> DEFAULT_UPLOAD_PATH_PREFIX_WINDOWS
            "Linux" -> DEFAULT_UPLOAD_PATH_PREFIX_LINUX
            else -> DEFAULT_UPLOAD_PATH_PREFIX_LINUX
        }
        val path = Paths.get(pathPrefix, uploadPath)
        if (!Files.exists(path)) {
            Files.createDirectories(path)
        }
        val filename = UUID.randomUUID().toString() + ".jpg"
        val destination = path.resolve(filename)
        Files.copy(file.inputStream, destination, StandardCopyOption.REPLACE_EXISTING)
        when (uploadPath) {
            DEFAULT_ARTICLE_IMG_UPLOAD_PATH -> ImageUtil.convertToJpg(destination, DEFAULT_ARTICLE_IMG_UPLOAD_MAX_RESOLUTION)
            DEFAULT_BIRD_IMG_UPLOAD_PATH -> ImageUtil.convertToJpg(destination, DEFAULT_BIRD_IMG_UPLOAD_MAX_RESOLUTION)
            DEFAULT_AVATAR_UPLOAD_PATH -> ImageUtil.convertToJpg(destination, DEFAULT_AVATAR_UPLOAD_MAX_RESOLUTION)
        }
        return uploadPath + filename
    }

    /**
     * 删除指定路径的文件
     *
     * @param filePath 文件路径
     * @throws IOException
     */
    @Throws(IOException::class)
    fun deleteFile(filePath: String) {
        val pathPrefix = when (getOS()) {
            "Windows" -> DEFAULT_UPLOAD_PATH_PREFIX_WINDOWS
            "Linux" -> DEFAULT_UPLOAD_PATH_PREFIX_LINUX
            else -> DEFAULT_UPLOAD_PATH_PREFIX_LINUX
        }
        val path = Paths.get(pathPrefix, filePath)
        Files.deleteIfExists(path)
    }

    /**
     * 获取操作系统名称
     * @return 操作系统名称
     *
     */
    private fun getOS(): String {
        val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
        return when {
            osName.contains("windows") -> "Windows"
            osName.contains("linux") -> "Linux"
            else -> ""
        }
    }
}