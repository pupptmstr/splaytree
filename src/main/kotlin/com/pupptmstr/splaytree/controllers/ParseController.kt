package com.pupptmstr.splaytree.controllers

import javafx.scene.control.Alert
import tornadofx.Controller
import tornadofx.alert
import java.io.File
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class ParseController: Controller() {
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
                    res += this.parseDataTxt(file.readText())
                }

            } else {
                throw IllegalArgumentException()
            }
        }
        return res
    }

    //Метод, достающий данные и правильно парсящий файлы, с расширением ".tr"
    private fun parseDataTr(input: String): List<Int> {
        val res = mutableListOf<Int>()
        val splitted = input.split("//")
        for (i in splitted.indices) {
            try {
                val num = splitted[i].split("-")[0].toInt()
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

    //Метод, достающий данные и правильно парсящий файлы, с расширением ".txt"
    private fun parseDataTxt(input: String): List<Int> {
        val res = mutableListOf<Int>()
        val splitted = input.split(Regex("""[\t\n\r\s-]+"""))
        for (i in splitted.indices) {
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