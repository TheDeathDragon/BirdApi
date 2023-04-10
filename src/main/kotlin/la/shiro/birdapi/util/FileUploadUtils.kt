package la.shiro.birdapi.util

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
class FileUploadUtils {

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
            "Windows" -> "C:\\Users\\Rin-3900X\\Desktop\\Blog\\static"
            "Linux" -> "/home/www/static"
            else -> ""
        }
        val path = Paths.get(pathPrefix, uploadPath)
        if (!Files.exists(path)) {
            Files.createDirectories(path)
        }
        val filename = UUID.randomUUID().toString() + ".jpg"
        // val filename = file.originalFilename ?: throw IOException("Invalid file name")
        val destination = path.resolve(filename)
        Files.copy(file.inputStream, destination, StandardCopyOption.REPLACE_EXISTING)
        val imageUtil = ImageUtil()
        imageUtil.convertToJpg(destination.toString(), destination.toString())
        return destination.toString()
    }

    /**
     * 删除指定路径的文件
     *
     * @param filePath 文件路径
     * @throws IOException
     */
    @Throws(IOException::class)
    fun deleteFile(filePath: String) {
        Files.deleteIfExists(Paths.get(filePath))
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
            osName.contains("mac") -> "macOS"
            else -> "Unknown"
        }
    }
}