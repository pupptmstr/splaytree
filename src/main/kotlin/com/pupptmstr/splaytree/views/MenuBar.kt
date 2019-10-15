package com.pupptmstr.splaytree.views

import com.pupptmstr.splaytree.controllers.MenuController
import com.pupptmstr.splaytree.controllers.SplayTreeController
import tornadofx.*

class MenuBar : View() {
    private val controller: MenuController by inject()
    private val treeController: SplayTreeController by inject()

    override val root = menubar {
        menu("File") {
            item("Open").action {
                val content = controller.openFile()
                val parsedData = controller.parseData(content)
                parsedData.forEach { treeController.addDataToTree(it) }
            }

            item("Save").action { println("Файл сохранен в своем формате(для чтения программой") }

            item("Export as picture")
        }

        menu("Settings") {
            item("For Project")

            item("For Program")

            item("About")
        }


    }
}