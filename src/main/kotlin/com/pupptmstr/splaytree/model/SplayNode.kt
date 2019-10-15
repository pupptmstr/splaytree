package com.pupptmstr.splaytree.model

data class SplayNode(
    val parent: SplayNode?,
    val left: SplayNode?,
    val right: SplayNode?,
    val content: Int)