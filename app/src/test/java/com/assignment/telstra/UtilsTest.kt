@file:Suppress("DEPRECATION")

package com.assignment.telstra

import com.assignment.telstra.utils.Utils
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UtilsTest {

    lateinit var utils: Utils
    @Before
    fun setup(){
        utils = Mockito.mock(Utils::class.java)
    }


    @Test fun testNetworkAvailibility(){
      //  assertEquals(false, utils.isNetworkAvailable())
        assertEquals(true, utils.isNetworkAvailable())
    }


}