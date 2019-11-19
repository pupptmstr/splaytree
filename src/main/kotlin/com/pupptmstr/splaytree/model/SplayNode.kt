package com.pupptmstr.splaytree.model

data class SplayNode
@JvmOverloads constructor(
    var element: Int = 0,
    var left: SplayNode? = null,
    var right: SplayNode? = null,
    var parent: SplayNode? = null
)
