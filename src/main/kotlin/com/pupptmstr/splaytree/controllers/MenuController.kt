package com.pupptmstr.splaytree.controllers

import javafx.scene.control.Alert
import tornadofx.*
import java.io.File
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class MenuController: Controller() {
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
}