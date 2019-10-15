package com.pupptmstr.splaytree.views

import com.pupptmstr.splaytree.controllers.SplayTreeController
import com.pupptmstr.splaytree.model.SplayTree
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import tornadofx.*
import java.lang.Exception
import java.lang.NumberFormatException

class ActionView : View() {
    private val controller: SplayTreeController by inject()
    private val input = SimpleStringProperty()
    val splayTree: SplayTree by param()

    override val root = form {
        fieldset {
            field("Enter your integer") {
                textfield(input)
            }

            button("Add") {
                action {
                    try {
                        alert(Alert.AlertType.INFORMATION, controller.addDataToTree(input.value.toInt()))
                    } catch (e: Exception) {
                        alert(Alert.AlertType.ERROR, "Неверный формат ввода")
                    }
                }
            }
            button("Remove") {
                action {
                    try {
                        alert(Alert.AlertType.INFORMATION, controller.removeDataFromTree(input.value.toInt()))
                    } catch (e: NumberFormatException) {
                        alert(Alert.AlertType.ERROR, "Неверный формат ввода")
                    }

                }
            }
            button("Find") {
                action {
                    try {
                        alert(Alert.AlertType.INFORMATION, controller.searchDataInTree(input.value.toInt()))
                    } catch (e: NumberFormatException) {
                        alert(Alert.AlertType.ERROR, "Неверный формат ввода")
                    }
                }
            }
        }
    }
}