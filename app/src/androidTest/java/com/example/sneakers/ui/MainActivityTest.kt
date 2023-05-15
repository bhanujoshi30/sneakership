package com.example.sneakers.ui

import androidx.fragment.app.FragmentManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sneakers.R
import com.example.sneakers.ui.cart.CartFragment
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var fragmentManager: FragmentManager

    @Before
    fun setup() {
        fragmentManager = ApplicationProvider.getApplicationContext<MainActivity>().supportFragmentManager
    }

    @Test
    fun replaceFragment() {
        // Create a new fragment to replace the existing one
        val newFragment = CartFragment()

        // Replace the existing fragment with the new one
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame, newFragment)
        transaction.commit()

        // Verify that the new fragment has been added to the container
        assertEquals(newFragment, fragmentManager.findFragmentById(R.id.frame))
    }
}