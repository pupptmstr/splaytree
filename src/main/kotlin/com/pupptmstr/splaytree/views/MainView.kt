package com.pupptmstr.splaytree.views

import tornadofx.*

class MainView: View() {
    private val actionView = find<ActionView>()
    private val splayTreeView = find<SplayTreeView>()
    private val menuBar = find<MenuBar>()
 
    override val root = borderpane {
        top = menuBar.root
        center = actionView.root
        bottom = splayTreeView.root
    }
}
