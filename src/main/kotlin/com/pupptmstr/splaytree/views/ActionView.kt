package com.pupptmstr.splaytree.views

import com.pupptmstr.splaytree.controllers.SplayTreeController
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import tornadofx.*
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.*

class ActionView : View() {
    private val controller: SplayTreeController by inject()
    private val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Enter your integer") {
                maxWidth = 400.0
                textfield(input)
            }

            button("Add") {
                action {
                    try {
                        alert(Alert.AlertType.INFORMATION, controller.addDataToTree(input.value.toInt()))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        alert(Alert.AlertType.ERROR, "Неверный формат ввода")
                    }
                }
            }
            button("Remove") {
                action {
                    try {
                        alert(Alert.AlertType.INFORMATION, controller.removeDataFromTree(input.value.toInt()))
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                        alert(Alert.AlertType.ERROR, "Неверный формат ввода")
                    }

                }
            }
            button("Find") {
                action {
                    try {
                        alert(Alert.AlertType.INFORMATION, controller.searchDataInTree(input.value.toInt()))
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                        alert(Alert.AlertType.ERROR, "Неверный формат ввода")
                    }
                }
            }
        }
    }
}