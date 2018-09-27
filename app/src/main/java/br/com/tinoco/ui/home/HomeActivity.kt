package br.com.tinoco.ui.home

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import br.com.tinoco.R
import br.com.tinoco.util.replaceFragmentInActivity
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(){

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var presenter: HomePresenter
    var countBack: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(findViewById(R.id.nav_view))
        drawerLayout.openDrawer(GravityCompat.START)
        val homeFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as HomeFragment? ?: HomeFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }
        presenter = HomePresenter(homeFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            presenter.loadFeed(menuItem.toString())
            menuItem.isChecked = true
            countBack = 0
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawers()
        else {
            if(countBack == 0){
                countBack++
                Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show()
            }else
                super.onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

}