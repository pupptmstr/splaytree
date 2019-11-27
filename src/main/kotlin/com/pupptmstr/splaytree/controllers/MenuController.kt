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
    //вынести парсинг в отдельный контроллер
    private val aboutText = "Создатель: Курняков Пётр Михайлович(pupptmstr)\n" +
            "студент группы 3530901/80003, а так же молодой и подающий надежды программист по совместительству\n" +
            "Это приложение создано в качестве курсовой работы по предмету `Алгоритмы и структуры данных`\n\n" +
            "Визуализация алгоритмов работы `splay` дерева\n\n" +
            "Курняков Пётр 2019 СПбПУ ©"
    val parser: ParseController by inject()

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


    //Метод для общего определения(какого типа файл -> как его нужно парсить


    @Throws(FileSystemException::class)
    fun saveTreeToFile(tree: SplayTree): Boolean {
        val fileChooser = FileChooser()
        fileChooser.title = "Save Document"
        val file = fileChooser.showSaveDialog(primaryStage)
        return if (file != null) {
            val writer = BufferedWriter(FileWriter(file, true))
            writer.write(tree.toString())
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