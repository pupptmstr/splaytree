package com.pupptmstr.splaytree.controllers

import com.pupptmstr.splaytree.model.SplayTree
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.layout.Region
import javafx.stage.FileChooser
import tornadofx.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class MenuController: Controller() {
    private val aboutText = "Создатель: Курняков Пётр Михайлович(pupptmstr)\n" +
            "студент группы 3530901/80003, а так же молодой и подающий надежды программист по совместительству\n" +
            "Это приложение создано в качестве курсовой работы по предмету `Алгоритмы и структуры данных`\n\n" +
            "Визуализация алгоритмов работы `splay` дерева\n\n" +
            "Курняков Пётр 2019 СПбПУ ©"

    fun openFile(): List<String> {
        val files: List<File> = chooseFile("Choose `.tr` or `.txt` file", emptyArray(), FileChooserMode.Single)
        val res = mutableListOf<String>()

        if (files.isNotEmpty()) {
            files.forEach {
                res.add(it.absolutePath)
            }
        }

        return res
    }


    @Throws(IllegalArgumentException::class)
    fun parseData(input: List<String>): List<Int> {
        val res = mutableListOf<Int>()
        input.forEach {
            val file = File(it)
            if (file.isFile) {
                println(file.extension)
                if (file.extension == "tr") {
                    res += this.parseDataTr(file.readText())
                } else if(file.extension == "txt") {
                    res += this.parseDataTr(file.readText())
                }

            } else {
                throw IllegalArgumentException()
            }
        }
        return res
    }

    private fun parseDataTr(input: String): List<Int> {
        val res = mutableListOf<Int>()
        val splitted = input.split(Regex("""[\t\n\r\s-]+"""))
        for (i in 0 until splitted.size) {
            try {
                val num = splitted[i].toInt()
                res.add(num)
            } catch (e: NumberFormatException) {
                alert(
                    Alert.AlertType.ERROR,
                    "${splitted[i]} is not a Number",
                    "It will not be added to the Tree")
            }
        }
        return res
    }

    @Throws(FileSystemException::class)
    fun saveTreeToFile(tree: SplayTree): Boolean {
        val fileChooser = FileChooser()
        fileChooser.title = "Save Document"
        val file = fileChooser.showSaveDialog(primaryStage)
        return if (file != null) {
            val writer = BufferedWriter(FileWriter(file, true))
            tree.getBody().forEach {
                writer.write("-- ${it.content}")
            }
            writer.close()
            true
        } else {
            false
        }
    }

    fun showAboutWindow() {
        val alert = Alert(Alert.AlertType.INFORMATION, aboutText, ButtonType.OK)
        alert.dialogPane.minHeight = Region.USE_PREF_SIZE
        alert.showAndWait()
    }
}