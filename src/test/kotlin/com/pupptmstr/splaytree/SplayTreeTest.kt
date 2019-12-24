package com.pupptmstr.splaytree

import com.pupptmstr.splaytree.model.SplayNode
import com.pupptmstr.splaytree.model.SplayTree
import junit.framework.Assert.assertEquals
import org.junit.Test

class SplayTreeTest {

     @Test
     fun testAdd() {
         val splayTree = SplayTree()
         splayTree.add(5)
         splayTree.add(18)
         splayTree.add(45)
         splayTree.add(20)
         assertEquals("5-2//18-1//20-0//45-1", splayTree.toString())
     }

    @Test
    fun testDelete() {
        val splayTree = SplayTree()
        splayTree.add(5)
        splayTree.add(18)
        splayTree.add(45)
        splayTree.add(20)
        splayTree.remove(18)
        assertEquals("5-0//20-1//45-2", splayTree.toString())
        val listOfElementsToDelete = mutableListOf<SplayNode>()
        listOfElementsToDelete.add(SplayNode(5))
        listOfElementsToDelete.add(SplayNode(45))
        listOfElementsToDelete.add(SplayNode(20))
        splayTree.removeAll(listOfElementsToDelete)
        assertEquals("", splayTree.toString())
    }

    @Test
    fun testFind() {
        val splayTree = SplayTree()
        splayTree.add(5)
        splayTree.add(18)
        splayTree.add(45)
        splayTree.add(20)
        val beforeFind = splayTree.toString()
        splayTree.contains(18)
        assertEquals("5-2//18-1//20-0//45-1 -- 5-1//18-0//20-1//45-2",
            "$beforeFind -- $splayTree"
        )
    }

 }