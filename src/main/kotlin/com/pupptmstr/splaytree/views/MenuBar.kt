package com.pupptmstr.splaytree.views

import com.pupptmstr.splaytree.controllers.MenuController
import com.pupptmstr.splaytree.controllers.SplayTreeController
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import tornadofx.*
import java.util.*

class MenuBar : View() {
    private val controller: MenuController by inject()
    private val treeController: SplayTreeController by inject()

    override val root = menubar {
        menu("File") {
            item("Open").action {
                val content = controller.openFile()
                val parsedData = controller.parser.parseData(content)
                if (treeController.isNotEmpty()) {
                    val alert = Alert(Alert.AlertType.CONFIRMATION)
                    alert.title = "Сохранить?"
                    alert.headerText = "У вас есть несохраненное дерево"
                    alert.contentText = "Сохранить?"
                    alert.buttonTypes.clear()
                    alert.buttonTypes.addAll(ButtonType.OK, ButtonType.NO, ButtonType.CANCEL)
                    val option: Optional<ButtonType> = alert.showAndWait()
                    when (option.get()) {
                        ButtonType.OK -> {
                            controller.saveTreeToFile(treeController.getTree())
                            treeController.clearTree()
                            parsedData.forEach { treeController.addDataToTree(it) }
                        }

                        ButtonType.NO -> {
                            treeController.clearTree()
                            parsedData.forEach { treeController.addDataToTree(it) }
                        }
                    }
                } else {
                    parsedData.forEach { treeController.addDataToTree(it) }
                }
            }

            item("Save").action {
                controller.saveTreeToFile(treeController.getTree())
            }

            item("//Export as picture")
        }

        menu("Settings") {
            item("//For Project")

            item("//For Program")

            item("About").action {
                controller.showAboutWindow()
            }
        }


    }
}