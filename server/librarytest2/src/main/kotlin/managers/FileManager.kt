package managers

import com.google.common.base.Charsets
import com.google.common.io.FileWriteMode
import com.google.common.io.Files
import java.io.File
import java.io.IOException

class FileManager {
    fun addDataToFile(filePath: String, data: String) {
        try {
            Files.asCharSink(File(filePath), Charsets.UTF_8, FileWriteMode.APPEND).write(data)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}