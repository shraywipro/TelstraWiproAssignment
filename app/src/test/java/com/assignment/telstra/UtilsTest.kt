@file:Suppress("DEPRECATION")

package com.assignment.telstra

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.assignment.telstra.ui.main.fact.FactListFragment
import com.assignment.telstra.ui.main.fact.FactListFragmentViewModel
import com.assignment.telstra.utils.Utils
import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


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
        utils = Mockito.mock(Utils::class.java)
        context = Mockito.mock(Context::class.java)
       // viewModel=Mockito.mock(FactListFragmentViewModel::class.java)
       // fragment=Mockito.mock(FactListFragment::class.java)

        assertTrue(utils.isNetworkAvailable(context))
    }

    @Test
    fun testJson() {
//        utils = Mockito.mock(Utils::class.java)
//        context = Mockito.mock(Context::class.java)
        viewModel=Mockito.mock(FactListFragmentViewModel::class.java)
        fragment=Mockito.mock(FactListFragment::class.java)

        `when`(fragment.getFactsList(false)).thenReturn(null)
        assertNotNull(fragment.testFactsList())
      //  assertTrue(fragment.getFactsList(false).hasObservers())
    }

    @Test
    fun testNetwork(){
        val context =
            Mockito.mock(Context::class.java)
        val connManager = Mockito.mock(
            ConnectivityManager::class.java
        )
        val networkInfo = Mockito.mock(NetworkInfo::class.java)
        val connectivityListener = Mockito.mock(
            ConnectivityListener::class.java
        )
        val packageManager =
            Mockito.mock(PackageManager::class.java)

        `when`(context.packageManager).thenReturn(packageManager)
        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE))
            .thenReturn(connManager)
        `when`(connManager.activeNetworkInfo).thenReturn(networkInfo)
        `when`(networkInfo.isConnected).thenReturn(true)

        Mockito.verify(context)
            .getSystemService(Context.CONNECTIVITY_SERVICE)
        Mockito.verify(connManager).activeNetworkInfo
        Mockito.verify(networkInfo).isConnected
        Mockito.verify(connectivityListener, Mockito.never())
            .onConnectivityChanged(true)


        assertTrue(utils.isNetworkAvailable(context))

    }

    @Test
    fun testProgress(){
        val connectivityManager :  ConnectivityManager = Mockito.mock( ConnectivityManager::class.java)
        val networkInfo:NetworkInfo = Mockito.mock( NetworkInfo::class.java)
        `when`(connectivityManager.getNetworkInfo( ConnectivityManager.TYPE_WIFI )).thenReturn( networkInfo )
        `when`(networkInfo.isAvailable()).thenReturn( true )
        `when`( networkInfo.isConnected()).thenReturn( true )
// Exercise
        utils.isNetworkAvailable( context )
// Verify
        verify( connectivityManager).getNetworkInfo(true)
        verify( networkInfo ).isAvailable()
        verify( networkInfo ).isConnected()


    }
}