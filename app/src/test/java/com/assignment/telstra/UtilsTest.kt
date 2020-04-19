@file:Suppress("DEPRECATION")

package com.assignment.telstra

import android.content.Context
import com.assignment.telstra.ui.main.fact.FactListFragment
import com.assignment.telstra.ui.main.fact.FactListFragmentViewModel
import com.assignment.telstra.utils.Utils
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class UtilsTest {

    lateinit var utils: Utils
    lateinit var context : Context

    internal lateinit var viewModel: FactListFragmentViewModel
    internal lateinit var fragment: FactListFragment

    @Before
    fun setup() {
        utils = Mockito.mock(Utils::class.java)
        context = Mockito.mock(Context::class.java)
        viewModel=Mockito.mock(FactListFragmentViewModel::class.java)
        fragment=Mockito.mock(FactListFragment::class.java)

}

    @Test
    fun testNetworkAvailibility() {
        assertTrue( utils.isNetworkAvailable(context))
    }

    @Test
    fun testJson() {
        `when`(fragment.getFactsList(false)).thenReturn(null)
        assertNotNull(fragment.getFactsList(false))
      //  assertTrue(fragment.getFactsList(false).hasObservers())
    }

}