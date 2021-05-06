package com.example.lesson_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lesson_6.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_one -> {
                    val itemOneFragment = ItemOneFragment.newInstance()
                    openFragment(itemOneFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.item_two -> {
                    val itemTwoFragment = ItemTwoFragment.newInstance()
                    openFragment(itemTwoFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.item_three -> {
                    val itemThreeFragment = ItemThreeFragment.newInstance()
                    openFragment(itemThreeFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigation = binding.bottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(ItemOneFragment.newInstance())
    }

    fun onMenuItemClick(item: MenuItem) {
        val text = getString(R.string.toast, item.title)
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}